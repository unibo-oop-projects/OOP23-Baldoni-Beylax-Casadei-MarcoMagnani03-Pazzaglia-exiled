package unibo.exiled.model.character.player;

import java.util.Objects;
import java.util.Optional;

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
import unibo.exiled.model.item.ItemType;

/**
 * This class represent the implementation of the player in the game.
 */
public class PlayerImpl extends GameCharacterImpl implements Player {
    private final int levelInc;
    private int level;
    private double currentExp;
    private double expCap;
    private final Inventory inventory;
    private final MoveSet moveSet;
    private ElementalType playerClass;
    private final int movesLearningInterval;
    private int levelToLearnAMove;

    /**
     * Constructs a new player with specified attributes, experience cap, initial
     * experience, and learning intervals.
     *
     * @param experienceCap         The maximum experience points required for a
     *                              level up.
     * @param initialExperience     The starting experience points of the player.
     * @param levelIncrease         The increment value for each level up.
     * @param movesNumber           The initial number of magical moves the player
     *                              possesses.
     * @param movesLearningInterval The interval at which the player learns new
     *                              magical moves.
     */
    public PlayerImpl(final double experienceCap, final double initialExperience, final int levelIncrease,
            final int movesNumber, final int movesLearningInterval) {
        super("boy", new AttributeFactoryImpl().createPlayerAttributes());
        Constants.loadConfiguration(Constants.DEF_CONFIG_PATH);
        this.inventory = initializeInventory();
        this.moveSet = new MoveSetFactoryImpl().defaultNormalMoveSet(movesNumber);
        this.expCap = experienceCap;
        this.currentExp = initialExperience;
        this.levelInc = levelIncrease;
        this.movesLearningInterval = movesLearningInterval;
        this.levelToLearnAMove = 0;
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
        return moveSet;
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
    public double getExperience() {
        return this.currentExp;
    }

    /**
     * Gets the inventory of the player.
     * 
     * @return the inventory containing the player's items.
     */
    @Override
    public Inventory getInventory() {
        return this.inventory;
    }

    /**
     * Checks and triggers a level up if the player's experience points exceed the
     * experience cap.
     */
    private void levelUp() {
        if (this.currentExp >= this.expCap) {
            performLevelUp();
        }
    }

    /**
     * Calculates and returns the experience points required for the next level.
     *
     * @return The experience points needed for the next level.
     */
    private double calculateNextLevelExperience() {
        return this.expCap + this.expCap / Integer.parseInt(Constants.getConstantOf("EXPERIENCE_MULTIPLIER"));
    }

    /**
     * Handles the player's level up, updating attributes and learning a new
     * MagicMove.
     */
    private void performLevelUp() {
        this.level++;
        this.expCap = calculateNextLevelExperience();
        this.currentExp -= expCap;
        this.increaseAttributeModifier(AttributeIdentifier.ATTACK, levelInc / 10);
        learnNewMove();
        boostAttributes();
    }

    /**
     * Boosts various attributes of the player upon leveling up.
     */
    private void boostAttributes() {
        this.increaseAttributeModifier(AttributeIdentifier.ATTACK, levelInc / 10);
        this.increaseAttributeValue(AttributeIdentifier.HEALTH, levelInc);
        this.increaseAttributeValue(AttributeIdentifier.HEALTHCAP, levelInc);
        this.increaseAttributeValue(AttributeIdentifier.DEFENSE, levelInc);
    }

    /**
     * Learns a new magical move based on the player's level and learning interval.
     */
    private void learnNewMove() {
        if (levelToLearnAMove == movesLearningInterval) {
            levelToLearnAMove = 0;
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

            this.moveSet.addMagicMove(newMove.get());
        } else {
            levelToLearnAMove++;
        }
    }

    /**
     * Sets the elemental type chosen from the player.
     * 
     * @param playerClass the class choosen.
     */
    @Override
    public void setPlayerClass(final ElementalType playerClass) {
        if (!Objects.isNull(playerClass)) {
            this.playerClass = playerClass;
        }
    }

    /**
     * Gets the player class.
     * 
     * @return the player class.
     */
    @Override
    public ElementalType getPlayerClass() {
        return this.playerClass;
    }

    /**
     * Adds experience, if it exceeds the levelUp cap by increasing statistics.
     * 
     * @param exp experience provided to the user.
     */
    @Override
    public void addExperience(final double exp) {
        this.currentExp += exp;
        levelUp();
    }
}
