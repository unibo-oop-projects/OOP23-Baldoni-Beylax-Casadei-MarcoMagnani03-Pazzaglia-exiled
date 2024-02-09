package unibo.exiled.model.character.player;

import java.util.Optional;
import java.util.Random;

import unibo.exiled.config.Constants;
import unibo.exiled.model.character.GameCharacterImpl;
import unibo.exiled.model.character.attributes.AttributeFactoryImpl;
import unibo.exiled.model.character.attributes.AttributeIdentifier;
import unibo.exiled.model.item.Inventory;
import unibo.exiled.model.item.InventoryImpl;
import unibo.exiled.model.item.Item;
import unibo.exiled.model.item.ItemContainer;
import unibo.exiled.model.item.ItemNames;
import unibo.exiled.model.move.MagicMove;
import unibo.exiled.model.move.MoveSet;
import unibo.exiled.model.move.MoveSetFactoryImpl;
import unibo.exiled.model.move.Moves;
import unibo.exiled.model.utilities.ElementalType;
import unibo.exiled.model.utilities.Inventories;
import unibo.exiled.model.utilities.MoveSets;
import unibo.exiled.model.item.ItemType;
import unibo.exiled.model.item.UsableItem;

/**
 * This class represent the implementation of the player in the game.
 */
public final class PlayerImpl extends GameCharacterImpl implements Player {
    private static final Random RANDOM = new Random();
    private static final float ATTRIBUTE_INCREMENT_MODULATOR = 20.0f;
    private final int attributeIncBound;
    private final Inventory inventory;
    private final MoveSet moveSet;
    private final int movesLearningInterval;
    private final int maxMovesNumber;
    private int level;
    private int currentExp;
    private int expCap;
    private ElementalType playerClass;

    /**
     * Constructs a new player with specified attributes, experience cap, initial
     * experience, and learning intervals.
     *
     * @param experienceCap          The maximum experience points required for a
     *                               level up.
     * @param initialExperience      The starting experience points of the player.
     * @param attributeIncreaseBound The max increment value for each level up.
     * @param movesLearningInterval  The interval at which the player learns new
     *                               magical moves.
     */
    public PlayerImpl(final int experienceCap, final int initialExperience, final int attributeIncreaseBound,
                      final int movesLearningInterval) {
        super(Constants.PLAYER_NAME, new AttributeFactoryImpl().createPlayerAttributes());
        Constants.loadConfiguration(Constants.DEF_CONFIG_PATH);
        this.inventory = initializeInventory();
        this.moveSet = new MoveSetFactoryImpl().defaultNormalMoveSet();
        this.expCap = experienceCap;
        this.currentExp = initialExperience;
        this.attributeIncBound = attributeIncreaseBound;
        this.movesLearningInterval = movesLearningInterval;
        this.playerClass = ElementalType.NORMAL;
        this.maxMovesNumber = Integer.parseInt(Constants.getConstantOf("MOVES_NUMBER"));
    }

    // This method is used for testing purposes only.
    private Inventory initializeInventory() {
        final Inventory inventory = new InventoryImpl();
        final Item healingItem = ItemContainer.getItemByName(ItemNames.HEALTH_POTION.getName()).get();
        inventory.addItem(healingItem);
        final Item healingItem1 = ItemContainer.getItemByName(ItemNames.HEALTH_POTION.getName()).get();
        inventory.addItem(healingItem1);
        final Item powerUpItem = ItemContainer.getRandomItemByType(ItemType.HEALTH).get();
        inventory.addItem(powerUpItem);
        final Item powerUpItem1 = ItemContainer.getRandomItemByType(ItemType.POWERUP).get();
        inventory.addItem(powerUpItem1);
        final Item powerUpItemDefence = ItemContainer.getRandomItemByType(ItemType.RESOURCE).get();
        inventory.addItem(powerUpItemDefence);
        return inventory;
    }

    /**
     * Gets the MoveSet of the character to be used in a battle.
     *
     * @return the MoveSet of the current character.
     */
    @Override
    public MoveSet getMoveSet() {
        return MoveSets.copyOf(this.moveSet);
    }

    /**
     * Gets the level of the player.
     *
     * @return the level of the player.
     */
    @Override
    public int getLevel() {
        return this.level;
    }

    /**
     * Gets the experience of the player.
     *
     * @return the experience points of the player.
     */
    @Override
    public int getExperience() {
        return this.currentExp;
    }

    /**
     * Gets the inventory of the player.
     *
     * @return the inventory containing the player's items.
     */
    @Override
    public Inventory getInventory() {
        return Inventories.copyOf(this.inventory);
    }

    @Override
    public void addItemToInventory(final Item item) {
        this.inventory.addItem(item);
    }

    /**
     * Checks and triggers a level up if the player's experience points exceed the
     * experience cap.
     */
    private void levelUp() {
        while (this.currentExp >= this.expCap) {
            performLevelUp();
        }
    }

    /**
     * Calculates and returns the experience points required for the next level.
     *
     * @return The experience points needed for the next level.
     */
    private int calculateNextLevelExperience() {
        return (int) (this.expCap + this.expCap
                * Double.parseDouble(Constants.getConstantOf("EXPERIENCE_MULTIPLIER")));
    }

    /**
     * Handles the player's level up, updating attributes and learning a new
     * MagicMove.
     */
    private void performLevelUp() {
        this.level++;
        this.currentExp -= expCap;
        this.expCap = calculateNextLevelExperience();
        learnNewMove();
        boostAttributes();
    }

    /**
     * Boosts various attributes of the player upon leveling up.
     */
    private void boostAttributes() {
        this.increaseAttributeModifier(AttributeIdentifier.ATTACK,
                RANDOM.nextInt(attributeIncBound) / ATTRIBUTE_INCREMENT_MODULATOR);
        this.increaseAttributeModifier(AttributeIdentifier.DEFENSE,
                RANDOM.nextInt(attributeIncBound) / ATTRIBUTE_INCREMENT_MODULATOR);
        this.increaseAttributeValue(AttributeIdentifier.HEALTHCAP, RANDOM.nextInt(attributeIncBound));
    }

    /**
     * Learns a new magical move based on the player's level and learning interval.
     */
    private void learnNewMove() {
        if (level % movesLearningInterval == 0) {
            Optional<MagicMove> newMove;

            // If the player knows all moves of their type, learn a move of a different type
            if (this.moveSet.getMagicMoves().containsAll(Moves.getAllMagicMovesOfType(playerClass))) {
                newMove = Optional.of(Moves.getTotallyRandomMove());
            } else {
                do {
                    newMove = Moves.getRandomMagicMoveByType(playerClass);
                    // If there are no moves of the specified type, choose a completely random move
                    newMove = newMove.isEmpty() ? Optional.of(Moves.getTotallyRandomMove()) : newMove;
                    // Continue searching until finding a move the player doesn't already know
                } while (this.moveSet.getMagicMoves().contains(newMove.get()));
            }
            if (this.moveSet.getMagicMoves().size() == this.maxMovesNumber) {
                //TODO: Manage the MoveSet change.
                //this.moveSet.changeMoves(null, newMove);
                throw new UnsupportedOperationException("Unimplemented");
            } else {
                this.moveSet.addMagicMove(newMove.get());
            }
        }
    }

    @Override
    public ElementalType getPlayerClass() {
        return this.playerClass;
    }

    @Override
    public void setPlayerClass(final ElementalType playerClassChoice) {
        this.playerClass = playerClassChoice;
    }

    @Override
    public void addExperience(final int exp) {
        this.currentExp += exp;
        levelUp();
    }

    @Override
    public void useItem(final UsableItem item) {
        item.use(this);
        inventory.removeItem(item);
    }

    @Override
    public int getCapExperience() {
        return this.expCap;
    }
}
