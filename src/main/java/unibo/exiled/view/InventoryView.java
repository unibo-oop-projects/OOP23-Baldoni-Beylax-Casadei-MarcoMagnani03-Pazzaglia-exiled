package unibo.exiled.view;

import unibo.exiled.controller.InventoryController;
import unibo.exiled.model.item.Inventory;
import unibo.exiled.model.item.InventoryImpl;
import unibo.exiled.model.item.Item;
import unibo.exiled.model.item.ItemFactoryImpl;
import unibo.exiled.model.item.UsableItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class InventoryView extends JFrame {
    private InventoryController controller;

    public InventoryView() {
        Inventory inventory = new InventoryImpl();
        ItemFactoryImpl itemFactory = new ItemFactoryImpl();
        inventory.addItem(itemFactory.createHealingItem("Healing Potion", "A potion that gives healing to the player", 20));
        inventory.addItem(itemFactory.createPowerUpItem("Power up potion", "A potion that gives a power up to the player", 20,2));
        inventory.addItem(itemFactory.createUnUsableItem("Redemption crystal", "A crystal"));
        controller = new InventoryController(inventory);
        setTitle("Inventory");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());

        updateInventoryButtons();

        setLocationRelativeTo(null);
    }

    public void updateInventoryButtons() {
        getContentPane().removeAll();

        Map<Item, Integer> itemsList = controller.getItems();

        if (itemsList.isEmpty()) {
            add(new JLabel("Inventory is empty."));
        } else {
            for (Map.Entry<Item, Integer> entry : itemsList.entrySet()) {
                Item item = entry.getKey();
                int quantity = entry.getValue();
                JButton itemButton = new JButton(item.getName() + " - Quantity: " + quantity);
                if (item instanceof UsableItem) {
                    addUsableItemActionListener(itemButton, (UsableItem) item);
                }
                add(itemButton);
            }
        }
        revalidate();
    }

    private void addUsableItemActionListener(JButton itemButton, UsableItem usableItem) {
        itemButton.addActionListener(e -> {
            int confirmation = JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure you want to use " + usableItem.getName() + "?",
                    "Confirm Use",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirmation == JOptionPane.YES_OPTION) {
                controller.useItem(usableItem);
                updateInventoryButtons();
            }
        });
    }

    public void display() {
        setVisible(true);
    }
}
