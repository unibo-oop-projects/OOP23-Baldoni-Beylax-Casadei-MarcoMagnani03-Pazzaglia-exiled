package unibo.exiled.view;

import unibo.exiled.controller.Controller;
import unibo.exiled.controller.InventoryController;
import unibo.exiled.controller.MenuController;
import unibo.exiled.controller.PlayerController;
import unibo.exiled.model.utilities.Direction;
import unibo.exiled.model.utilities.Position;
import unibo.exiled.view.Menu.MenuView;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

/**
 * The main view of the game.
 */
public class GameView {
    // Screen constants
    private static final int SIZE = 20;

    // MVC Components (MC)
    private final JFrame mainFrame;
    private final Controller controller;
    private final PlayerController playerController;
    private final InventoryController inventoryController;
    private final MenuController menuController;
    private InventoryView inventoryView;
    private final PlayerView playerView;
    private GameOverView gameOverView;
    private MenuView menuView;

    // The cells of the grid.
    private final Map<Position, JPanel> cells = new HashMap<>();

    private JPanel gamePanel;
    private JPanel menuPanel;

    public GameView() {
        //Screen size initialization.
        Dimension SCREEN = Toolkit.getDefaultToolkit().getScreenSize();
        double SCREEN_WIDTH = SCREEN.getWidth();
        double SCREEN_HEIGHT = SCREEN.getHeight();

        this.controller = new Controller(SIZE);
        this.playerController = new PlayerController();
        this.inventoryController=new InventoryController(playerController.getInventory());
        this.menuController = new MenuController();
        this.playerView = new PlayerView();
        this.menuView = new MenuView(menuController, this);
        this.mainFrame = new JFrame();

        // mainFrame.setSize((int) SCREEN_WIDTH, (int) SCREEN_HEIGHT);
        this.mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        this.mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.mainFrame.setTitle("The Exiled");
        this.mainFrame.setLocationByPlatform(true);
        this.mainFrame.setFocusable(true);
        
        this.mainFrame.addComponentListener(new ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                playerView.updateSize(cells.get(playerController.getPlayerPosition()).getSize());
            }
        });

        this.menuPanel = new JPanel();
        this.gamePanel = new JPanel(new BorderLayout());

        Container contentPanel = this.mainFrame.getContentPane();
        
        GroupLayout mainLayout = new GroupLayout(contentPanel);
        contentPanel.setLayout(mainLayout);

        mainLayout.setHorizontalGroup(mainLayout.createSequentialGroup().addComponent(menuPanel).addComponent(gamePanel));
        mainLayout.setVerticalGroup(mainLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(menuPanel).addComponent(gamePanel));
        
        initializeGridComponents();
        initializeHud();

        this.showMenu();
    }

    private void initializeHud() {
        JPanel flowButtonPanelSouth = new JPanel(new FlowLayout());
        JPanel flowButtonPanelNorth = new JPanel(new FlowLayout());

        // Inventory button
        this.gamePanel.add(flowButtonPanelNorth, BorderLayout.NORTH);

        JButton inventoryButton = new JButton("Inventory");
        inventoryButton.addActionListener(e -> showInventory());
        JButton menuButton = new JButton("Menu");
        menuButton.addActionListener(e -> showMenu());
        flowButtonPanelNorth.add(inventoryButton);
        flowButtonPanelNorth.add(menuButton);
        
        //Player information
        Font labelFont = new Font("Arial", Font.PLAIN, 16);
        this.gamePanel.add(flowButtonPanelSouth, BorderLayout.SOUTH);
        JLabel lifeLabel = new JLabel("Health: " + playerController.getHealth()+" / " +playerController.getHealthCap());
        lifeLabel.setFont(labelFont);
        JLabel levelLabel = new JLabel("Level: " + playerController.getLevel());
        levelLabel.setFont(labelFont);

        JPanel statusPanel = new JPanel(new FlowLayout());
        statusPanel.setBorder(BorderFactory.createEtchedBorder());

        statusPanel.add(lifeLabel);
        statusPanel.add(levelLabel);

        flowButtonPanelSouth.add(statusPanel);

        this.mainFrame.revalidate();
        this.mainFrame.repaint();
    }

    /**
     * Colors the map areas based on the respective type.
     * @param cell The JButton cell to set its background.
     */
    private void setAreas(final Position cell){
        final JPanel pane = cells.get(cell);
        switch (controller.getCellType(cell)){
            case VOLCANO -> pane.setBackground(Color.ORANGE);
            case PLAINS -> pane.setBackground(Color.yellow);
            case FOREST -> pane.setBackground(Color.green);
            case STORM -> pane.setBackground(Color.darkGray);
            case SWAMP -> pane.setBackground(Color.blue);
            default -> pane.setBackground(Color.white);
        }
        pane.setBorder(new LineBorder(Color.black));
    }

    private void initializeGridComponents() {
        menuPanel.add(menuView);

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
                        playerView.changeImage(Direction.NORTH, cells.get(playerController.getPlayerPosition()).getSize());
                        redraw();
                        break;

                    case KeyEvent.VK_S:
                        playerController.move(Direction.SOUTH);
                        playerView.changeImage(Direction.SOUTH, cells.get(playerController.getPlayerPosition()).getSize());
                        redraw();
                        break;

                    case KeyEvent.VK_A:
                        playerController.move(Direction.WEST);
                        playerView.changeImage(Direction.WEST, cells.get(playerController.getPlayerPosition()).getSize());
                        redraw();
                        break;

                    case KeyEvent.VK_D:
                        playerController.move(Direction.EAST);
                        playerView.changeImage(Direction.EAST, cells.get(playerController.getPlayerPosition()).getSize());
                        redraw();
                        break;

                    default:
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        };

        this.mainFrame.addKeyListener(kl);

        // Grid initialization
        for (int i = 0; i < controller.getMapHeight(); i++) {
            for (int j = 0; j < controller.getMapWidth(); j++) {
                final JPanel cell = new JPanel(new FlowLayout());
                final Position pos = new Position(j,i);
                gridPanel.add(cell);
                cells.put(pos,cell);
                this.setAreas(pos);
                if(pos.equals(playerController.getPlayerPosition())){
                    cell.add(playerView);
                }
            }
        }

        this.gamePanel.add(gridPanel, BorderLayout.CENTER);

        this.mainFrame.revalidate();
        this.mainFrame.repaint();
    }

    private void redraw(){
        for (var cell: cells.entrySet()) {
            if(cell.getKey().equals(playerController.getPlayerPosition())){
                cell.getValue().add(playerView);
            }else{
                cell.getValue().remove(playerView);
            }
            cell.getValue().repaint();
        }
    }

    private void showInventory() {
        if (inventoryView == null) {
            inventoryView = new InventoryView(inventoryController);
        }
        inventoryView.display();
    }

    public void showMenu() {
        this.gamePanel.setVisible(false);
        this.menuPanel.setVisible(true);

        this.mainFrame.revalidate();
        this.mainFrame.repaint();
    }

    public void hideMenu() {
        this.gamePanel.setVisible(true);
        this.menuPanel.setVisible(false);

        this.mainFrame.revalidate();
        this.mainFrame.repaint();
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
        SwingUtilities.invokeLater(() -> new GameView().display());
    }
}
