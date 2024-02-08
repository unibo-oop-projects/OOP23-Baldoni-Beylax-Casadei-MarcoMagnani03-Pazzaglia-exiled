package unibo.exiled.controller;

import unibo.exiled.model.character.GameCharacter;
import unibo.exiled.model.character.attributes.AttributeIdentifier;
import unibo.exiled.model.character.enemy.Enemy;
import unibo.exiled.model.character.enemy.EnemyImpl;
import unibo.exiled.model.character.player.Player;
import unibo.exiled.model.character.player.PlayerClass;
import unibo.exiled.model.game.GameModel;
import unibo.exiled.model.move.MagicMove;
import unibo.exiled.model.utilities.Direction;
import unibo.exiled.model.utilities.Position;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

/**
 * Implementation of the CharacterController interface.
 */
public final class CharacterControllerImpl implements CharacterController {

    private static final String EXCEPTION_POSITION_MISSING_MESSAGE = "The position doesn't contain a character.";
    private static final Random RANDOM = new Random();
    private final GameModel model;

    /**
     * Constructor for the CharacterControllerImpl.
     *
     * @param model The game model to manage the game.
     */
    public CharacterControllerImpl(final GameModel model) {
        this.model = model;
    }

    @Override
    public List<String> getImagePathOfCharacter(final String folderPath, final String name) {
        final String loweredName = name.toLowerCase(Locale.ROOT);
        return List.of(
                folderPath,
                loweredName + "_up",
                loweredName + "_down",
                loweredName + "_left",
                loweredName + "_right");
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
    public void addPlayerExperience(double amount) {
        this.model.addPlayerExperience(amount);
    }

    @Override
    public int getPlayerExperienceCap() {
        return this.model.getPlayerExperienceCap();
    }

    @Override
    public String getPlayerClassName() {
        return this.model.getPlayerClass().elementalType().getName();
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
        final Optional<MagicMove> move = this.model.getMagicMoves().stream().filter(m -> m.name().equals(moveName))
                .findFirst();
        if (move.isPresent()) {
            return move.get().description();
        } else {
            return "";
        }
    }

    @Override
    public double getMagicMoveDamage(final String moveName) {
        final Optional<MagicMove> move = this.model.getMagicMoves().stream().filter(m -> m.name().equals(moveName))
                .findFirst();
        if (move.isPresent()) {
            return move.get().power();
        } else {
            throw new IllegalArgumentException("The move doesn't exists");
        }
    }

    @Override
    public List<String> getPlayerMoveSet() {
        return this.model.getPlayerMoveSet().getMagicMoves().stream().map(MagicMove::name).toList();
    }

    @Override
    public boolean attack(final boolean isPlayerAttacking, final String moveName, final Position combatPosition) {
        if (this.model.getCharacterFromPosition(combatPosition).isEmpty()) {
            throw new IllegalArgumentException("The position doesn't contain a character.");
        }

        final GameCharacter attacker = isPlayerAttacking ? this.model.getPlayer().get()
                : this.model.getCharacterFromPosition(combatPosition).get();
        final GameCharacter defender = !isPlayerAttacking ? this.model.getPlayer().get()
                : this.model.getCharacterFromPosition(combatPosition).get();

        final double damage = attacker.getMoveSet()
                .getMagicMoves()
                .stream()
                .filter(m -> m.name().equals(moveName))
                .findFirst()
                .get()
                .power();
        defender.decreaseAttributeValue(AttributeIdentifier.HEALTH, damage);
        return defender.getHealth() <= 0;
    }

    @Override
    public void assignPlayerClass(final PlayerClass playerClass) {
        this.model.assignPlayerClass(playerClass);
    }

    @Override
    public boolean getIfCharacterInPositionIsMoving(final Position position) {
        final Optional<GameCharacter> gottenCharacter = this.model.getCharacterFromPosition(position);
        if (gottenCharacter.isPresent()) {
            return gottenCharacter.get().spriteIsMoving();
        } else {
            throw new IllegalArgumentException(EXCEPTION_POSITION_MISSING_MESSAGE);
        }
    }

    @Override
    public double getCharacterHealthFromPosition(final Position position) {
        final Optional<GameCharacter> gottenCharacter = this.model.getCharacterFromPosition(position);
        if (gottenCharacter.isPresent()) {
            return gottenCharacter.get().getHealth();
        } else {
            throw new IllegalArgumentException(EXCEPTION_POSITION_MISSING_MESSAGE);
        }
    }

    @Override
    public double getCharacterHealthCapFromPosition(final Position position) {
        final Optional<GameCharacter> gottenCharacter = this.model.getCharacterFromPosition(position);
        if (gottenCharacter.isPresent()) {
            return gottenCharacter.get().getHealthCap();
        } else {
            throw new IllegalArgumentException(EXCEPTION_POSITION_MISSING_MESSAGE);
        }
    }

    @Override
    public String getCharacterClassNameFromPosition(final Position position) {
        final Optional<GameCharacter> gottenCharacter = this.model.getCharacterFromPosition(position);
        if (gottenCharacter.isPresent()) {
            return ((EnemyImpl) gottenCharacter.get()).getType().getName();
        } else {
            throw new IllegalArgumentException(EXCEPTION_POSITION_MISSING_MESSAGE);
        }
    }

    @Override
    public String getCharacterRandomMoveNameFromPosition(final Position position) {
        final Set<MagicMove> moves = this.model.getCharacterFromPosition(position).get().getMoveSet().getMagicMoves();
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
    public double getEnemyExperienceDropFromPosition(final Position position) {
        final Optional<GameCharacter> enemy = this.model.getCharacterFromPosition(position);

        if (enemy.isPresent()) {
            return ((Enemy) enemy.get()).getDroppedExperience();
        } else {
            throw new IllegalArgumentException(EXCEPTION_POSITION_MISSING_MESSAGE);
        }
    }
}
