package unibo.exiled.view;

import unibo.exiled.controller.Controller;
import unibo.exiled.model.utilities.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

/**
 * The main view of the game.
 */
public class GameView {
    // Screen constants
    private static final int SIZE = 10;
    private final Dimension SCREEN = Toolkit.getDefaultToolkit().getScreenSize();
    private final double SCREEN_WIDTH = SCREEN.getWidth();
    private final double SCREEN_HEIGHT = SCREEN.getHeight();

    // MVC Components (MC)
    private final JFrame mainFrame;
    private final Controller controller;
    private InventoryView inventoryView; // Added

    // The cells of the grid.
    private final Map<JButton, Position> cells = new HashMap<>();

    public GameView() {
        this.controller = new Controller(SIZE);
        this.mainFrame = new JFrame();
        mainFrame.setSize((int) SCREEN_WIDTH / 3, (int) SCREEN_HEIGHT / 2);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setTitle("The Exiled");
        mainFrame.setLocationByPlatform(true);
        this.initializeGridComponents();
        this.initializeHud();
    }

    private void initializeHud() {
        JPanel flowButtonPanelSouth = new JPanel(new FlowLayout());
        JPanel flowButtonPanelNorth = new JPanel(new FlowLayout());
        this.mainFrame.getContentPane().add(flowButtonPanelSouth, BorderLayout.SOUTH);
        JButton buttonSouth = new JButton("Template");
        this.mainFrame.getContentPane().add(flowButtonPanelNorth, BorderLayout.NORTH);
        JButton buttonNorth = new JButton("Poppolo");
        flowButtonPanelSouth.add(buttonSouth);
        flowButtonPanelNorth.add(buttonNorth);

        // Inventory button
        JButton inventoryButton = new JButton("Inventory");
        inventoryButton.addActionListener(e -> showInventory());
        flowButtonPanelNorth.add(inventoryButton);
    }

    private void initializeGridComponents() {
        final JPanel externalPanel = new JPanel(new BorderLayout());
        this.mainFrame.setContentPane(externalPanel);
        final JPanel gridPanel = new JPanel(new GridLayout(controller.getMapWidth(), controller.getMapHeight()));

        // Listener initialization
        ActionListener al = e -> {
            final JButton button = (JButton) e.getSource();
            button.setText(String.valueOf(cells.get(button)));
        };

        // Grid initialization
        for (int i = 0; i < controller.getMapHeight(); i++) {
            for (int j = 0; j < controller.getMapWidth(); j++) {
                final JButton cell = new JButton();
                cell.addActionListener(al);
                gridPanel.add(cell);
                cells.put(cell, new Position(j, i));
            }
        }

        this.mainFrame.getContentPane().add(gridPanel, BorderLayout.CENTER);
    }

    private void showInventory() {
        if (inventoryView == null) {
            inventoryView = new InventoryView();
        }
        inventoryView.display();
    }

    public void display() {
        this.mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GameView().display();
        });
    }
}
