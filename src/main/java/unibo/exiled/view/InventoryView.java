package unibo.exiled.view;

import unibo.exiled.controller.InventoryController;
import unibo.exiled.model.item.Item;
import unibo.exiled.model.item.UsableItem;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class InventoryView extends JFrame {
    private InventoryController inventoryController;

    public InventoryView(InventoryController inventoryController) {
        this.inventoryController = inventoryController;
        setTitle("Inventory");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());

        updateInventoryButtons();

        setLocationRelativeTo(null);
    }

    public void updateInventoryButtons() {
        getContentPane().removeAll();

        Map<Item, Integer> itemsList = inventoryController.getItems();

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
                inventoryController.useItem(usableItem);
                updateInventoryButtons();
            }
        });
    }

    public void display() {
        setVisible(true);
    }
}
