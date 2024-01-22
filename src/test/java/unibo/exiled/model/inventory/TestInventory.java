package unibo.exiled.model.inventory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import unibo.exiled.model.item.Inventory;
import unibo.exiled.model.item.InventoryImpl;
import unibo.exiled.model.item.Item;
import unibo.exiled.model.item.ItemFactory;
import unibo.exiled.model.item.ItemFactoryImpl;

public class TestInventory {
    @Test
    void testAddItem() {
        Inventory inventory = new InventoryImpl();
        ItemFactory factory = new ItemFactoryImpl();
        Item crystal = factory.createUnUsableItem("Crystal", "A crystal");
        
        inventory.addItem(crystal);
        assertEquals(1, inventory.getItemQuantity(crystal));

        inventory.addItem(crystal);
        assertEquals(2, inventory.getItemQuantity(crystal));
    }

    @Test
    void testRemoveItem() {
        Inventory inventory = new InventoryImpl();
        ItemFactory factory = new ItemFactoryImpl();
        Item potion = factory.createHealingItem("Health Potion","A healing potion", 5);

        // Remove an item that is not in the inventory
        inventory.removeItem(potion);
        assertNull(inventory.getItemQuantity(potion));

        // Add an item and then remove it
        inventory.addItem(potion);
        inventory.removeItem(potion);
        assertNull(inventory.getItemQuantity(potion));

        // Add multiple items and remove one
        Item maxPotion = factory.createHealingItem("Max Health Potion","A max healing potion", 50);
        inventory.addItem(potion);
        inventory.addItem(maxPotion);
        inventory.removeItem(potion);
        assertEquals(1, inventory.getItemQuantity(maxPotion));
    }

    @Test
    void testGetItemQuantity() {
        Inventory inventory = new InventoryImpl();
        ItemFactory factory = new ItemFactoryImpl();
        Item potion = factory.createHealingItem("Health Potion","A healing potion", 5);

        assertNull(inventory.getItemQuantity(potion));

        inventory.addItem(potion);
        assertEquals(1, inventory.getItemQuantity(potion));

        inventory.addItem(potion);
        assertEquals(2, inventory.getItemQuantity(potion));
    }

    @Test
    void testContainsItem() {
        Inventory inventory = new InventoryImpl();
        ItemFactory factory = new ItemFactoryImpl();
        Item potion = factory.createHealingItem("Health Potion","A healing potion", 5);
        Item crystal = factory.createUnUsableItem("Crystal", "A crystal");

        assertFalse(inventory.containsItem(potion));

        inventory.addItem(potion);
        assertTrue(inventory.containsItem(potion));

        inventory.addItem(crystal);
        assertTrue(inventory.containsItem(crystal));
    }

    @Test
    void testGetItems() {
        Inventory inventory = new InventoryImpl();
        ItemFactory factory = new ItemFactoryImpl();
        Item potion = factory.createHealingItem("Health Potion","A healing potion", 5);
        Item crystal = factory.createUnUsableItem("Crystal", "A crystal");

        assertTrue(inventory.getItems().isEmpty(), "Newly created inventory should have no items");

        inventory.addItem(potion);
        inventory.addItem(crystal);

        assertEquals(2, inventory.getItems().size(), "Inventory should have 2 items");
    }
}
