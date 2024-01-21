package unibo.exiled.view;

import unibo.exiled.controller.InventoryController;
import unibo.exiled.model.item.HealingItem;
import unibo.exiled.model.item.Item;
import unibo.exiled.model.item.PowerUpItem;
import unibo.exiled.model.item.UsableItem;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.util.Map;

public class InventoryView extends JFrame {
    private InventoryController inventoryController;
    private final Dimension SCREEN = Toolkit.getDefaultToolkit().getScreenSize();
    private final double SCREEN_WIDTH = SCREEN.getWidth();
    private final double SCREEN_HEIGHT = SCREEN.getHeight();
    private final static int SCALING=4;
    private final static int LIST_ITEM_HEIGHT=30;
    private static final Color HEALING_ITEM_COLOR = new Color(141, 254, 141);
    private static final Color POWER_UP_ITEM_COLOR = new Color(254, 141, 141);;

    private DefaultListModel<Item> listModel;
    private JList<Item> itemList;

    public InventoryView(InventoryController inventoryController) {
        this.inventoryController = inventoryController;
        setTitle("Inventory");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        itemList = new JList<>(listModel);
        itemList.addListSelectionListener(new ItemListSelectionListener());
        itemList.setCellRenderer(new ItemListRenderer());
        JScrollPane scrollPane = new JScrollPane(itemList);
        add(scrollPane, BorderLayout.CENTER);

        updateInventoryList();

        setSize((int) SCREEN_WIDTH / SCALING, (int) SCREEN_HEIGHT / SCALING);
        setLocationRelativeTo(null);
    }

    private class ItemListRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
    
            if (value instanceof Item) {
                Item item = (Item) value;
                int quantity = inventoryController.getItems().get(item);
                setText(" "+item.getName() + " - Quantity: " + quantity+" - Description: "+item.getDescription());
                
                setPreferredSize(new Dimension(getWidth(), LIST_ITEM_HEIGHT));

                if (item instanceof HealingItem) {
                    setBackground(HEALING_ITEM_COLOR);
                } else if (item instanceof PowerUpItem) {
                    setBackground(POWER_UP_ITEM_COLOR);
                }
            }
    
            return this;
        }
    }

    public void updateInventoryList() {
        listModel.clear();

        Map<Item, Integer> itemsList = inventoryController.getItems();
        for (Map.Entry<Item, Integer> entry : itemsList.entrySet()) {
            Item item = entry.getKey();
            listModel.addElement(item);
        }
    }

    private class ItemListSelectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                Item selectedItem = itemList.getSelectedValue();
                if (selectedItem instanceof UsableItem) {
                    UsableItem usableItem = (UsableItem) selectedItem;
                    int confirmation = JOptionPane.showConfirmDialog(
                            null,
                            "Are you sure you want to use " + usableItem.getName() + "?",
                            "Confirm Use",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (confirmation == JOptionPane.YES_OPTION) {
                        inventoryController.useItem(usableItem);
                        updateInventoryList();
                    }
                }
            }
        }
    }

    public void display() {
        setVisible(true);
    }
}
