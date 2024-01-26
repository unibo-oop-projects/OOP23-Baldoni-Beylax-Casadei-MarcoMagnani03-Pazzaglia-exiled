package unibo.exiled.view;

import unibo.exiled.controller.InventoryController;
import unibo.exiled.model.item.HealingItem;
import unibo.exiled.model.item.Item;
import unibo.exiled.model.item.PowerUpItem;
import unibo.exiled.model.item.UsableItem;
import unibo.exiled.view.items.GameLabel;
import unibo.exiled.view.items.TitleGameLabel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.util.Map;

public class InventoryView extends JPanel {
    private final InventoryController inventoryController;
    private final  DefaultListModel<Item> listModel;
    private final  JList<Item> itemList;
    private final JLabel emptyInventoryLabel;
    private final JScrollPane scrollPane;
    
    private static final Color HEALING_ITEM_COLOR = new Color(141, 254, 141);
    private static final Color POWER_UP_ITEM_COLOR = new Color(254, 141, 141);
    private final static int LIST_ITEM_HEIGHT=30;

    public InventoryView(InventoryController inventoryController) {
        this.inventoryController = inventoryController;
        setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        itemList = new JList<>(listModel);
        itemList.addListSelectionListener(new ItemListSelectionListener());
        itemList.setCellRenderer(new ItemListRenderer());

        Dimension listItemSize = new Dimension(1000, LIST_ITEM_HEIGHT);
        itemList.setFixedCellHeight(listItemSize.height);
        itemList.setFixedCellWidth(listItemSize.width);
        scrollPane = new JScrollPane(itemList);
        
        scrollPane.setSize(listItemSize);

        JLabel titleLabel = new TitleGameLabel("Inventory");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        emptyInventoryLabel = new GameLabel("The inventory is empty");
        emptyInventoryLabel.setHorizontalAlignment(JLabel.CENTER);

        JPanel centralPanel = new JPanel(new BorderLayout());
        centralPanel.add(scrollPane, BorderLayout.CENTER);
        centralPanel.add(emptyInventoryLabel, BorderLayout.SOUTH);

        centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.PAGE_AXIS));

        add(centralPanel, BorderLayout.CENTER);

        updateInventoryList();
    }
    
    

    private class ItemListRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            if (value instanceof Item) {
                Item item = (Item) value;
                int quantity = inventoryController.getItems().get(item);
                setText(" " + item.getName() + " - Quantity: " + quantity + " - Description: " + item.getDescription());

                setPreferredSize(new Dimension(getWidth(), InventoryView.LIST_ITEM_HEIGHT));

                if (item instanceof HealingItem) {
                    setBackground(HEALING_ITEM_COLOR);
                } else if (item instanceof PowerUpItem) {
                    setBackground(POWER_UP_ITEM_COLOR);
                }
            }
            setHorizontalAlignment(SwingConstants.CENTER);
            
            return this;
        }
    }

    public void updateInventoryList() {
        listModel.clear();
    
        Map<Item, Integer> itemsList = inventoryController.getItems();
    
        if (itemsList.isEmpty()) {
            emptyInventoryLabel.setVisible(true);
            scrollPane.setVisible(false);
        } else {
            for (Map.Entry<Item, Integer> entry : itemsList.entrySet()) {
                Item item = entry.getKey();
                listModel.addElement(item);
            }
            emptyInventoryLabel.setVisible(false);
        }
    
        revalidate();
        repaint();
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
                        boolean useResult = inventoryController.useItem(usableItem);
                        if (useResult) {
                            JOptionPane.showMessageDialog(null, "Used " + usableItem.getName());
                        } else {
                            JOptionPane.showMessageDialog(null, "Item not found in the inventory");
                        }
                        updateInventoryList();
                    }
                }
            }
        }
    }
}
