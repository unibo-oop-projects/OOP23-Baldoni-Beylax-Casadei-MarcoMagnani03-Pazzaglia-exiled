package unibo.exiled.view;

import unibo.exiled.controller.InventoryController;
import unibo.exiled.model.item.HealingItem;
import unibo.exiled.model.item.Item;
import unibo.exiled.model.item.PowerUpItem;
import unibo.exiled.model.item.UsableItem;
import unibo.exiled.view.items.GameButton;
import unibo.exiled.view.items.GameLabel;
import unibo.exiled.view.items.TitleGameLabel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.Map;

public class InventoryView extends JPanel {
    private static final Color HEALING_ITEM_COLOR = new Color(141, 254, 141);
    private static final Color POWER_UP_ITEM_COLOR = new Color(254, 141, 141);
    private static final Border LIST_ITEM_BORDER = new LineBorder(Color.BLACK, 1);
    private final static int LIST_ITEM_HEIGHT = 30;
    private final static int LEFT_RIGHT_MARGIN = 100;
    private final static int TOP_BOTTOM_MARGIN = 15;
    private final InventoryController inventoryController;
    private final DefaultListModel<Item> listModel;
    private final JList<Item> itemList;
    private final JLabel emptyInventoryLabel;
    private final JScrollPane scrollPane;
    private final GameButton exitButton;
    private final int listItemWidth;

    public InventoryView(InventoryController inventoryController, GameView game) {
        this.inventoryController = inventoryController;
        setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        itemList = new JList<>(listModel);
        itemList.addListSelectionListener(new ItemListSelectionListener());
        itemList.setCellRenderer(new ItemListRenderer());

        Dimension listItemSize = new Dimension(100, LIST_ITEM_HEIGHT);
        listItemWidth = getScreenWidth();

        itemList.setFixedCellHeight(LIST_ITEM_HEIGHT);
        itemList.setFixedCellWidth(listItemWidth - LEFT_RIGHT_MARGIN);
        scrollPane = new JScrollPane(itemList);

        scrollPane.setSize(listItemSize);

        emptyInventoryLabel = new GameLabel("The inventory is empty");
        emptyInventoryLabel.setHorizontalAlignment(JLabel.CENTER);

        // Center
        JPanel centralPanel = new JPanel(new BorderLayout());
        centralPanel.add(scrollPane, BorderLayout.CENTER);
        centralPanel.add(emptyInventoryLabel, BorderLayout.SOUTH);
        centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.PAGE_AXIS));
        centralPanel.setBorder(BorderFactory.createEmptyBorder(TOP_BOTTOM_MARGIN, 0, TOP_BOTTOM_MARGIN, 0));
        add(centralPanel, BorderLayout.CENTER);

        // North
        JLabel titleLabel = new TitleGameLabel("Inventory");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        exitButton = new GameButton("Exit");
        exitButton.addActionListener(e -> game.hideInventory());

        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(exitButton, BorderLayout.WEST);
        northPanel.add(titleLabel, BorderLayout.CENTER);
        northPanel.setBorder(BorderFactory.createEmptyBorder(TOP_BOTTOM_MARGIN, 0, TOP_BOTTOM_MARGIN, 0));
        add(northPanel, BorderLayout.NORTH);

        updateInventoryList();
    }

    private int getScreenWidth() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        return toolkit.getScreenSize().width;
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

    private class ItemListRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            if (value instanceof Item item) {
                int quantity = inventoryController.getItems().get(item);

                if (item instanceof HealingItem healItem) {
                    setBackground(HEALING_ITEM_COLOR);
                    setText(" " + item.getName() + " - Quantity: " + quantity + " - Description: " + item.getDescription() + " - Heal: " + healItem.getAmount());
                } else if (item instanceof PowerUpItem powerUpItem) {
                    setBackground(POWER_UP_ITEM_COLOR);
                    setText(" " + item.getName() + " - Quantity: " + quantity + " - Description: " + item.getDescription() + " - PowerUp: " + powerUpItem.getAmount() + " - Attribute: " + powerUpItem.getBoostedAttribute().getName() + " - Duration: " + powerUpItem.getDuration());
                } else {
                    setText(" " + item.getName() + " - Quantity: " + quantity + " - Description: " + item.getDescription());
                }
                setBorder(LIST_ITEM_BORDER);
            }
            setHorizontalAlignment(SwingConstants.CENTER);

            return this;
        }
    }

    private class ItemListSelectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                Item selectedItem = itemList.getSelectedValue();
                if (selectedItem instanceof UsableItem usableItem) {
                    int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to use " + usableItem.getName() + "?", "Confirm Use", JOptionPane.YES_NO_OPTION);

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
