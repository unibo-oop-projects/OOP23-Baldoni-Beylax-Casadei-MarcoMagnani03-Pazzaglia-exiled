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
import unibo.exiled.model.item.utilities.ItemsContainer;
import unibo.exiled.model.move.MagicMove;
import unibo.exiled.model.move.factory.MoveSetFactoryImpl;
import unibo.exiled.model.move.Moves;
import unibo.exiled.utilities.ElementalType;
import unibo.exiled.utilities.Inventories;
import unibo.exiled.model.item.utilities.ItemType;
import unibo.exiled.model.item.UsableItem;

/**
 * This class represent the implementation of the player in the game.
 */
public final class PlayerImpl extends GameCharacterImpl implements Player {
    private static final Random RANDOM = new Random();
    private final int attributeIncBound;
    private final Inventory inventory;
    private final int movesLearningInterval;
    private final int maxMovesNumber;
    private int level;
    private int currentExp;
    private int expCap;

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
        super(ConstantsAndResourceLoader.PLAYER_NAME,
                new MoveSetFactoryImpl().defaultNormalMoveSet(),
                ElementalType.NORMAL,
                new AttributeFactoryImpl().createPlayerAttributes());
        this.inventory = initializeInventory();
        this.expCap = experienceCap;
        this.currentExp = 0;
        this.level = ConstantsAndResourceLoader.PLAYER_STARTING_LEVEL;
        this.attributeIncBound = attributeIncreaseBound;
        this.movesLearningInterval = movesLearningInterval;
        this.maxMovesNumber = ConstantsAndResourceLoader.PLAYER_MOVES_NUMBER;
    }

    // This method is used for testing purposes only.
    private Inventory initializeInventory() {
        final Inventory inventory = new InventoryImpl();
        final Item healingItem = ItemsContainer.getRandomItemByType(ItemType.HEALTH).get();
        inventory.addItem(healingItem);
        final Item powerUpItem = ItemsContainer.getRandomItemByType(ItemType.POWERUP).get();
        inventory.addItem(powerUpItem);
        return inventory;
    }

    //
    //  GETTER
    //

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
        this.setType(playerClassChoice);
    }

    //
    // OTHERS
    //

    /**
     * Learns a new magical move based on the player's level and learning interval.
     */
    private void learnNewMove() {
        if (level % movesLearningInterval == 0 && this.getMoveSet().getMagicMoves().size() <= this.maxMovesNumber) {
            Optional<MagicMove> newMove;

            // If the player knows all moves of their type, learn a move of a different type
            if (this.getMoveSet().getMagicMoves().containsAll(Moves.getAllMagicMovesOfType(getType(), this.level))) {
                newMove = Optional.of(Moves.getTotallyRandomMove(this.level));
            } else {
                newMove = getNewMove();
            }

            this.addMagicMove(newMove.get());
        }
    }


    @Override
    public Optional<MagicMove> getNewMove() {
        Optional<MagicMove> newMove;
        do {
            newMove = Moves.getRandomMagicMoveByType(getType(), this.level);
            // If there are no moves of the specified type, choose a completely random move that the player can learn
            newMove = newMove.isEmpty() ? Optional.of(Moves.getTotallyRandomMove(this.level)) : newMove;
            // Continue searching until finding a move the player doesn't already know
        } while (this.getMoveSet().getMagicMoves().contains(newMove.get()));
        return newMove;
    }

    @Override
    public void changeMove(final MagicMove oldMove, final MagicMove newMove) {
        this.getMoveSet().changeMove(oldMove, newMove);
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
                RANDOM.nextInt(attributeIncBound) / ConstantsAndResourceLoader.ATTRIBUTE_INCREMENT_MODULATOR);
        this.increaseAttributeModifier(AttributeIdentifier.DEFENSE,
                RANDOM.nextInt(attributeIncBound) / ConstantsAndResourceLoader.ATTRIBUTE_INCREMENT_MODULATOR);
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
