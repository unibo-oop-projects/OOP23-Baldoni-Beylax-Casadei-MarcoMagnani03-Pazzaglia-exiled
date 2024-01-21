package unibo.exiled.view;

import unibo.exiled.controller.Controller;
import unibo.exiled.controller.PlayerController;
import unibo.exiled.model.utilities.Direction;
import unibo.exiled.model.utilities.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * The main view of the game.
 */
public class GameView {
    // Screen constants
    private static final int SIZE = 20;
    private final Dimension SCREEN = Toolkit.getDefaultToolkit().getScreenSize();
    private final double SCREEN_WIDTH = SCREEN.getWidth();
    private final double SCREEN_HEIGHT = SCREEN.getHeight();

    // MVC Components (MC)
    private final JFrame mainFrame;
    private final Controller controller;
    private final PlayerController playerController;
    private InventoryView inventoryView;
    private PlayerView playerView;
    private GameOverView gameOverView;

    // The cells of the grid.
    private final Map<JButton, Position> cells = new HashMap<>();

    public GameView() {
        this.controller = new Controller(SIZE);
        this.playerController = new PlayerController();
        this.playerView = new PlayerView();
        this.mainFrame = new JFrame();
        mainFrame.setSize((int) SCREEN_WIDTH / 3, (int) SCREEN_HEIGHT / 2);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setTitle("The Exiled");
        mainFrame.setLocationByPlatform(true);
        mainFrame.setFocusable(true);
        mainFrame.addComponentListener(new ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                playerView.updateSize(cells.keySet().stream().toList().get(0).getSize());
            }
        });
        this.initializeGridComponents();
        this.initializeHud();
    }

    private void initializeHud() {
        JPanel flowButtonPanelSouth = new JPanel(new FlowLayout());
        JPanel flowButtonPanelNorth = new JPanel(new FlowLayout());
        this.mainFrame.getContentPane().add(flowButtonPanelSouth, BorderLayout.SOUTH);
        JButton buttonSouth = new JButton("Template");
        flowButtonPanelSouth.add(buttonSouth);

        // Inventory button
        this.mainFrame.getContentPane().add(flowButtonPanelNorth, BorderLayout.NORTH);
        JButton inventoryButton = new JButton("Inventory");
        inventoryButton.addActionListener(e -> showInventory());
        flowButtonPanelNorth.add(inventoryButton);

    }

    /**
     * Colors the map areas based on the respective type.
     * @param cell The JButton cell to set its background.
     */
    private void setAreas(final JButton cell){
        switch (controller.getCellType(cells.get(cell))){
            case VOLCANO -> {
                cell.setBackground(Color.ORANGE);
                break;
            }
            case PLAINS -> {
                cell.setBackground(Color.yellow);
                break;
            }
            case FOREST -> {
                cell.setBackground(Color.green);
                break;
            }
            case STORM -> {
                cell.setBackground(Color.darkGray);
                break;
            }
            case SWAMP -> {
                cell.setBackground(Color.blue);
                break;
            }
            default -> {
                cell.setBackground(Color.white);
                break;
            }
        }
    }

    private void initializeGridComponents() {
        final JPanel externalPanel = new JPanel(new BorderLayout());
        this.mainFrame.setContentPane(externalPanel);
        final JPanel gridPanel = new JPanel(new GridLayout(controller.getMapWidth(), controller.getMapHeight()));

        // Listener initialization
        KeyListener kl = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W:
                        playerController.move(Direction.NORTH);
                        break;
                    case KeyEvent.VK_S:
                        playerController.move(Direction.SOUTH);
                        break;
                    case KeyEvent.VK_A:
                        playerController.move(Direction.WEST);
                    break;
                    case KeyEvent.VK_D:
                        playerController.move(Direction.EAST);
                    break;
                    default:
                        break;
                }
                redraw();
            }
            
            @Override
            public void keyReleased(KeyEvent e) {
            }
        };

        this.mainFrame.addKeyListener(kl);

        // Grid initialization
        for (int i = 0; i < controller.getMapHeight(); i++) {
            for (int j = 0; j < controller.getMapWidth(); j++) {
                final JButton cell = new JButton();
                if(j == playerController.getPlayerPosition().x() && i == playerController.getPlayerPosition().y()){
                    cell.setIcon(playerView);
                }
                gridPanel.add(cell);
                cells.put(cell, new Position(j, i));
                this.setAreas(cell);
            }
        }

        this.mainFrame.getContentPane().add(gridPanel, BorderLayout.CENTER);
    }

    private void redraw(){
        for (Entry<JButton, Position> cell : cells.entrySet()) {
            if(cell.getValue().equals(playerController.getPlayerPosition())){
                cell.getKey().setIcon(playerView);
            }else{
                cell.getKey().setIcon(null);
            }
        }
    }

    private void showInventory() {
        if (inventoryView == null) {
            inventoryView = new InventoryView(controller.getInventoryController());
        }
        inventoryView.display();
    }

    private void gameOver(){
        if(this.gameOverView == null){
            this.gameOverView = new GameOverView();
        }
        gameOverView.display();
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
