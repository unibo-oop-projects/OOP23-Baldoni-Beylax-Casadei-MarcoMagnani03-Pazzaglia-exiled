package unibo.exiled.model.character.player;

import java.util.Optional;
import java.util.Random;

import unibo.exiled.utilities.ConstantsAndResourceLoader;
import unibo.exiled.model.character.GameCharacterImpl;
import unibo.exiled.model.character.attributes.AttributeFactoryImpl;
import unibo.exiled.model.character.attributes.AttributeIdentifier;
import unibo.exiled.model.item.Inventory;
import unibo.exiled.model.item.InventoryImpl;
import unibo.exiled.model.item.Item;
import unibo.exiled.model.item.ItemContainer;
import unibo.exiled.model.move.MagicMove;
import unibo.exiled.model.move.MoveSet;
import unibo.exiled.model.move.MoveSetFactoryImpl;
import unibo.exiled.model.move.Moves;
import unibo.exiled.utilities.ElementalType;
import unibo.exiled.utilities.Inventories;
import unibo.exiled.utilities.MoveSets;
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
     * @param attributeIncreaseBound The max increment value for each level up.
     * @param movesLearningInterval  The interval at which the player learns new
     *                               magical moves.
     */
    public PlayerImpl(final int experienceCap, final int attributeIncreaseBound,
                      final int movesLearningInterval) {
        super(ConstantsAndResourceLoader.PLAYER_NAME, new AttributeFactoryImpl().createPlayerAttributes());
        this.inventory = initializeInventory();
        this.moveSet = new MoveSetFactoryImpl().defaultNormalMoveSet();
        this.expCap = experienceCap;
        this.currentExp = 0;
        this.level = ConstantsAndResourceLoader.PLAYER_STARTING_LEVEL;
        this.attributeIncBound = attributeIncreaseBound;
        this.movesLearningInterval = movesLearningInterval;
        this.playerClass = ElementalType.NORMAL;
        this.maxMovesNumber = ConstantsAndResourceLoader.PLAYER_MOVES_NUMBER;
    }

    // This method is used for testing purposes only.
    private Inventory initializeInventory() {
        final Inventory inventory = new InventoryImpl();
        final Item healingItem = ItemContainer.getRandomItemByType(ItemType.HEALTH).get();
        inventory.addItem(healingItem);
        final Item powerUpItem = ItemContainer.getRandomItemByType(ItemType.POWERUP).get();
        inventory.addItem(powerUpItem);
        return inventory;
    }

    //
    //  GETTER
    //

    @Override
    public MoveSet getMoveSet() {
        return MoveSets.copyOf(this.moveSet);
    }

    @Override
    public ElementalType getType() {
        return this.playerClass;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public int getExperience() {
        return this.currentExp;
    }

    @Override
    public int getCapExperience() {
        return this.expCap;
    }

    @Override
    public Inventory getInventory() {
        return Inventories.copyOf(this.inventory);
    }

    //
    //  SETTER
    //

    @Override
    public void setPlayerClass(final ElementalType playerClassChoice) {
        this.playerClass = playerClassChoice;
    }

    //
    // OTHERS
    //

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
    public void addExperience(final int exp) {
        this.currentExp += exp;
        levelUp();
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
                * ConstantsAndResourceLoader.LEVEL_MODIFIER);
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

    @Override
    public void useItem(final UsableItem item) {
        item.use(this);
        inventory.removeItem(item);
    }

    @Override
    public void addItemToInventory(final Item item) {
        this.inventory.addItem(item);
    }


}
