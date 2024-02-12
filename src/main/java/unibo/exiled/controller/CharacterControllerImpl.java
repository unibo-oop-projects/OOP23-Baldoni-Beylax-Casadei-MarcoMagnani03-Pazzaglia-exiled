package unibo.exiled.controller;

import unibo.exiled.model.character.CharacterModel;
import unibo.exiled.model.character.GameCharacter;
import unibo.exiled.model.character.attributes.AttributeIdentifier;
import unibo.exiled.model.character.attributes.MultiplierAttribute;
import unibo.exiled.model.character.enemy.boss.BossEnemy;
import unibo.exiled.model.character.enemy.Enemy;
import unibo.exiled.model.character.player.Player;
import unibo.exiled.model.item.Item;
import unibo.exiled.model.move.MagicMove;
import unibo.exiled.model.move.Moves;
import unibo.exiled.utilities.ConstantsAndResourceLoader;
import unibo.exiled.utilities.Direction;
import unibo.exiled.utilities.ElementalType;
import unibo.exiled.utilities.Position;

import javax.annotation.concurrent.Immutable;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

/**
 * Implementation of the CharacterController interface.
 */
@Immutable
public final class CharacterControllerImpl implements CharacterController {

    private static final String EXCEPTION_POSITION_MISSING_MESSAGE = "The position doesn't contain a character.";
    private static final Random RANDOM = new Random();
    private final CharacterModel model;

    /**
     * Constructor for the CharacterControllerImpl.
     *
     * @param model The game model to manage the game.
     */
    public CharacterControllerImpl(final CharacterModel model) {
        this.model = model;
    }

    @Override
    public List<String> getImagePathOfCharacter(final String folderPath, final String name) {
        final String loweredName = name.toLowerCase(Locale.ROOT);
        return List.of(
                folderPath,
                "/" + loweredName + "_up",
                "/" + loweredName + "_down",
                "/" + loweredName + "_left",
                "/" + loweredName + "_right");
    }

    @Override
    public double getPlayerHealth() {
        return this.model.getPlayerAttributeOf(AttributeIdentifier.HEALTH);
    }

    @Override
    public double getPlayerHealthCap() {
        return this.model.getPlayerAttributeOf(AttributeIdentifier.HEALTHCAP);
    }

    @Override
    public int getPlayerLevel() {
        return this.model.getPlayerLevel();
    }

    @Override
    public int getPlayerCurrentExperience() {
        return this.model.getPlayerCurrentExperience();
    }

    @Override
    public void addPlayerExperience(final int amount) {
        this.model.addPlayerExperience(amount);
    }

    @Override
    public int getPlayerExperienceCap() {
        return this.model.getPlayerExperienceCap();
    }

    @Override
    public String getPlayerClassName() {
        return this.model.getPlayerClass().getName();
    }

    @Override
    public void movePlayer(final Direction direction) {
        this.model.movePlayer(direction);
    }

    @Override
    public void moveEnemies() {
        this.model.moveEnemies();
    }

    @Override
    public Position getPlayerPosition() {
        return this.model.getPlayerPosition();
    }

    @Override
    public List<String> getMagicMoveNames() {
        return this.model.getMagicMoves().stream().map(MagicMove::name).toList();
    }

    @Override
    public String getMagicMoveDescription(final String moveName) {
        final Optional<MagicMove> move = this.model.getMagicMoves().stream()
                .filter(m -> m.name().equals(moveName))
                .findFirst();
        if (move.isPresent()) {
            return move.get().description();
        } else {
            return "";
        }
    }

    @Override
    public double getMagicMoveDamage(final String moveName) {
        final Optional<MagicMove> move = this.model.getMagicMoves().stream()
                .filter(m -> m.name().equals(moveName))
                .findFirst();
        if (move.isPresent()) {
            return move.get().power();
        } else {
            throw new IllegalArgumentException("The move doesn't exists");
        }
    }

    @Override
    public ElementalType getMagicMoveElementalType(final String moveName) {
        final Optional<MagicMove> move = this.model.getMagicMoves().stream()
                .filter(m -> m.name().equals(moveName))
                .findFirst();
        if (move.isPresent()) {
            return move.get().type();
        } else {
            throw new IllegalArgumentException("The move doesn't exists");
        }
    }

    @Override
    public List<String> getPlayerMoveSet() {
        return this.model.getPlayerMoveSet().getMagicMoves().stream().map(MagicMove::name).toList();
    }

    /**
     * Returns the multiplier for the attack based on the move type and the defender
     * type.
     * 
     * @param move     the move performed by the attacker.
     * @param defender the defender.
     * @return the multiplier for the attack based on the move type and the defender
     *         type.
     */
    private double getAttackModifierBasedOnType(final MagicMove move, final GameCharacter defender) {
        final ElementalType moveType = move.type();
        final ElementalType defenderType = defender.getType();
        if (moveType.isStrongAgainst(defenderType)) {
            return ConstantsAndResourceLoader.ATTACK_MODIFIER_EFFECTIVE;
        } else if (defenderType.isStrongAgainst(moveType)) {
            return ConstantsAndResourceLoader.ATTACK_MODIFIER_INEFFECTIVE;
        } else {
            return ConstantsAndResourceLoader.NEUTRAL_MODIFIER;
        }
    }

    /**
     * Performes the attack routine.
     * 
     * @param isPlayerAttacking if the player is attacking.
     * @param moveName the name of the move performed.
     * @param combatPosition the combat position.
     * @return if the defender is dead.
     */
    @Override
    public boolean attack(final boolean isPlayerAttacking, final String moveName, final Position combatPosition) {
        if (this.model.getCharacterFromPosition(combatPosition).isEmpty()) {
            throw new IllegalArgumentException("The position doesn't contain a character.");
        }

        final GameCharacter attacker = isPlayerAttacking ? this.model.getPlayer().get()
                : this.model.getCharacterFromPosition(combatPosition).get();
        final GameCharacter defender = !isPlayerAttacking ? this.model.getPlayer().get()
                : this.model.getCharacterFromPosition(combatPosition).get();

        final MagicMove move = attacker.getMoveSet()
                .getMagicMoves()
                .stream()
                .filter(m -> m.name().equals(moveName))
                .findFirst()
                .get();
        final double baseDamage = move.power();
        final double defenseModifier = ((MultiplierAttribute) defender.getAttributes()
                .get(AttributeIdentifier.DEFENSE)).modifier();

        final double moveTypeModifier = attacker.getType().equals(move.type())
                ? ConstantsAndResourceLoader.ATTACK_SAME_TYPE_OF_CLASS_MODIFIER
                : 1;
        final double attackModifier = ((MultiplierAttribute) attacker.getAttributes().get(AttributeIdentifier.ATTACK))
                .modifier();
        final double damage = baseDamage * attackModifier * moveTypeModifier
                * getAttackModifierBasedOnType(move, defender) / defenseModifier;
        defender.decreaseAttributeValue(AttributeIdentifier.HEALTH, damage);

        final boolean hasAttackerWon = defender.getHealth() <= 0;

        if (isPlayerAttacking && hasAttackerWon) {
            // The player killed the enemy

            // Add experience drop from the enemy to the player
            final int experienceDropped = getEnemyExperienceDropFromPosition(combatPosition);
            addPlayerExperience(experienceDropped);
            removeEnemyFromPosition(combatPosition);

            // Add the item dropped from the enemy
            final Optional<Item> itemDropped = ((Enemy) defender).getHeldItem();
            itemDropped.ifPresent(item -> ((Player) attacker).addItemToInventory(item));

        }
        return hasAttackerWon;
    }

    @Override
    public void assignPlayerClass(final ElementalType playerClass) {
        this.model.assignPlayerClass(playerClass);
    }

    @Override
    public boolean getIfCharacterInPositionIsMoving(final Position position) {
        final Optional<GameCharacter> gottenCharacter = this.model
                .getCharacterFromPosition(position);
        if (gottenCharacter.isPresent()) {
            return gottenCharacter.get().spriteIsMoving();
        } else {
            throw new IllegalArgumentException(EXCEPTION_POSITION_MISSING_MESSAGE);
        }
    }

    @Override
    public double getCharacterHealthFromPosition(final Position position) {
        final Optional<GameCharacter> gottenCharacter = this.model
                .getCharacterFromPosition(position);
        if (gottenCharacter.isPresent()) {
            return gottenCharacter.get().getHealth();
        } else {
            throw new IllegalArgumentException(EXCEPTION_POSITION_MISSING_MESSAGE);
        }
    }

    @Override
    public double getCharacterHealthCapFromPosition(final Position position) {
        final Optional<GameCharacter> gottenCharacter = this.model
                .getCharacterFromPosition(position);
        if (gottenCharacter.isPresent()) {
            return gottenCharacter.get().getHealthCap();
        } else {
            throw new IllegalArgumentException(EXCEPTION_POSITION_MISSING_MESSAGE);
        }
    }

    @Override
    public String getCharacterClassNameFromPosition(final Position position) {
        final Optional<GameCharacter> gottenCharacter = this.model
                .getCharacterFromPosition(position);
        if (gottenCharacter.isPresent()) {
            return gottenCharacter.get().getType().getName();
        } else {
            throw new IllegalArgumentException(EXCEPTION_POSITION_MISSING_MESSAGE);
        }
    }

    @Override
    public String getCharacterRandomMoveNameFromPosition(final Position position) {
        final Set<MagicMove> moves = this.model.getCharacterFromPosition(position).get()
                .getMoveSet().getMagicMoves();
        final int randomIndex = RANDOM.nextInt(moves.size());
        int i = 0;
        for (final MagicMove magicMove : moves) {
            if (i == randomIndex) {
                return magicMove.name();
            }
            i++;
        }
        return moves.stream().findFirst().get().name();
    }

    @Override
    public void removeEnemyFromPosition(final Position position) {
        this.model.removeEnemyFromPosition(position);
    }

    @Override
    public int getEnemyExperienceDropFromPosition(final Position position) {
        final Optional<GameCharacter> enemy = this.model.getCharacterFromPosition(position);

        if (enemy.isPresent()) {
            return ((Enemy) enemy.get()).getDroppedExperience();
        } else {
            throw new IllegalArgumentException(EXCEPTION_POSITION_MISSING_MESSAGE);
        }
    }

    @Override
    public boolean checkWin() {
        if (model.getEnemies().isPresent()) {
            return model.getEnemies().get().getEnemies().stream().noneMatch(e -> e instanceof BossEnemy);
        }
        return true;
    }

    @Override
    public String getNewMove() {
        return this.model.getPlayer().get().getNewMove().get().name();
    }

    @Override
    public void changeMove(final String oldMove, final String newMove) {
        this.model.getPlayer().get().changeMove(Moves.getMoveByName(oldMove).get(), Moves.getMoveByName(newMove).get());
    }
}
