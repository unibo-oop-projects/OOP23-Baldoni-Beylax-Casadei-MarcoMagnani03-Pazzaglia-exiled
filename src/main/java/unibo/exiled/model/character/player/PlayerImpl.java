package unibo.exiled.model.character.player;

import unibo.exiled.model.character.CharacterImpl;
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
import unibo.exiled.model.utilities.ItemType;

import java.util.*;

/**
 * This class represent the implementation of the player.
 */
public class PlayerImpl extends CharacterImpl implements Player {

    private final int levelInc;
    private int level;
    private double currentExp;
    private double expCap;
    private final Inventory inventory;
    private final MoveSet moveSet;
    private ElementalType playerClass;
    private final int movesLearningInterval;
    private int levelToLearnAMove;

    public PlayerImpl(final double experienceCap, final double initialExperience, final int levelIncrease,final int movesNumber, final int movesLearningInterval) {
        super(new AttributeFactoryImpl().createPlayerAttributes(), List.of("player", "boy_up", "boy_down", "boy_left", "boy_right"));
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
        Inventory inventory = new InventoryImpl();
        Item healingItem = ItemContainer.getItemByName(ItemNames.HEALTH_POTION.getName()).get();
        inventory.addItem(healingItem);
        Item healingItem1 = ItemContainer.getItemByName(ItemNames.HEALTH_POTION.getName()).get();
        inventory.addItem(healingItem1);
        Item powerUpItem = ItemContainer.getRandomItemByType(ItemType.HEALTH).get();
        inventory.addItem(powerUpItem);
        Item powerUpItem1 = ItemContainer.getRandomItemByType(ItemType.POWERUP).get();
        inventory.addItem(powerUpItem1);
        Item powerUpItemDefence = ItemContainer.getRandomItemByType(ItemType.RESOURCE).get();
        inventory.addItem(powerUpItemDefence);
        return inventory;
    }

    @Override
    public MoveSet getMoveSet() {
        return moveSet;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public double getExperience() {
        return this.currentExp;
    }

    @Override
    public Inventory getInventory() {
        return this.inventory;
    }
    
    @Override
    public void addExperience(double exp) {
        this.currentExp += exp;
        levelUp();
    }

    private void levelUp() {
        if (this.currentExp >= this.expCap) {
            performLevelUp();
        }
    }
    
    private double calculateNextLevelExperience() {
        return this.expCap + this.expCap / 20 * 100;
    }
    
    private void performLevelUp() {
        this.level++;
        this.expCap = calculateNextLevelExperience();
        this.currentExp -= expCap;
        this.increaseAttributeModifier(AttributeIdentifier.ATTACK, levelInc / 10);
        learnNewMove();
        boostAttributes();
    }

    private void boostAttributes() {
        this.increaseAttributeModifier(AttributeIdentifier.ATTACK, levelInc / 10);
        this.increaseAttributeValue(AttributeIdentifier.HEALTH, levelInc);
        this.increaseAttributeValue(AttributeIdentifier.HEALTHCAP, levelInc);
        this.increaseAttributeValue(AttributeIdentifier.DEFENSE, levelInc);
    }

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
    

    @Override
    public void setPlayerClass(ElementalType playerClass) {
        if (!Objects.isNull(playerClass)) {
            this.playerClass = playerClass;
        }
    }

    @Override
    public ElementalType getPlayerClass() {
        return this.playerClass;
    }

}

