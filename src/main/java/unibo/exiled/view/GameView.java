package unibo.exiled.view;

import javax.swing.*;
import javax.swing.border.LineBorder;

import unibo.exiled.config.Constants;
import unibo.exiled.controller.GameController;
import unibo.exiled.controller.GameControllerImpl;
import unibo.exiled.model.character.attributes.AttributeIdentifier;
import unibo.exiled.model.utilities.Direction;
import unibo.exiled.model.utilities.Position;
import unibo.exiled.view.Menu.MenuView;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;


public class GameView{
    // Screen constants
    private final int SIZE;

    // Views
    private PlayerView playerView;
    private InventoryView inventoryView;
    private MenuView menuView;
    private GameOverView gameOverView;

    // MVC Components(MC)
    private final JFrame mainFrame; 
    private final JPanel gamePanel;
    private final JPanel menuPanel;
    private JPanel gridPanel;
    private final GameController gameController;

    // The cells of the grid
    private final Map<Position, JLabel> cells = new HashMap<>();

    public GameView(){
        Constants.loadConfiguration(Constants.DEF_CONFIG_PATH);
        SIZE = Integer.parseInt(Constants.getConstantOf("MAP_SIZE"));

        this.gameController = new GameControllerImpl(SIZE);
        
        this.mainFrame = new JFrame();
        this.mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.mainFrame.setTitle("The Exiled");
        this.mainFrame.setLocationByPlatform(true);
        this.mainFrame.setFocusable(true);
        
        this.menuPanel = new JPanel();
        this.gamePanel = new JPanel(new BorderLayout());

        this.menuView = new MenuView(gameController.getMenuController(), this);
        this.playerView = new PlayerView();

        this.menuPanel.add(menuView);
        
        Container contentPanel = this.mainFrame.getContentPane();

        GroupLayout mainLayout = new GroupLayout(contentPanel);
        contentPanel.setLayout(mainLayout);

        mainLayout.setHorizontalGroup(mainLayout.createSequentialGroup().addComponent(menuPanel).addComponent(gamePanel));
        mainLayout.setVerticalGroup(mainLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(menuPanel).addComponent(gamePanel));

        this.initializeGridComponents();
        this.initializeHUD();
        this.initializeKeyListeners();
        
        this.showMenu();
    }

    private void initializeHUD(){
        JPanel flowButtonPanelNorth = new JPanel(new FlowLayout());
        JPanel flowButtonPanelSouth = new JPanel(new FlowLayout());
        this.gamePanel.add(flowButtonPanelNorth, BorderLayout.NORTH);
        this.gamePanel.add(flowButtonPanelSouth, BorderLayout.SOUTH);

        // Inventory button
        JButton inventoryButton = new JButton("Inventory");
        inventoryButton.addActionListener(e -> showInventory());

        // Menu button
        JButton menuButton = new JButton("Menu");
        menuButton.addActionListener(e -> showMenu());
    

        flowButtonPanelNorth.add(inventoryButton);
        flowButtonPanelNorth.add(menuButton);

        // Player information 
        Font labelFont = new Font("Arial", Font.PLAIN, 16);
        JLabel lifeLabel = new JLabel("Health: " + gameController.getPlayerController().getPlayer().getAttributes().get(AttributeIdentifier.HEALTH).getValue().get());
        lifeLabel.setFont(labelFont);
        JLabel levelLabel = new JLabel("Level: " + gameController.getPlayerController().getPlayer().getLevel());
        levelLabel.setFont(labelFont);

        JPanel statusPanel = new JPanel(new FlowLayout());
        statusPanel.setBorder(BorderFactory.createEtchedBorder());

        statusPanel.add(lifeLabel);
        statusPanel.add(levelLabel);

        flowButtonPanelSouth.add(statusPanel);
    }

    private void initializeGridComponents() {
        this.gridPanel = new JPanel(
            new GridLayout(
                this.gameController.getMapController().getMap().getWidth(),
                this.gameController.getMapController().getMap().getHeight()
            )
        );
        draw();
        this.gamePanel.add(this.gridPanel, BorderLayout.CENTER);
    }

    private void initializeKeyListeners(){
        // Listener initialization
        KeyListener keyListener = new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (
                    e.getKeyCode() == KeyEvent.VK_W ||
                    e.getKeyCode() == KeyEvent.VK_A ||
                    e.getKeyCode() == KeyEvent.VK_S ||
                    e.getKeyCode() == KeyEvent.VK_D
                ) {
                    Direction directionPressed;
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_W -> directionPressed = Direction.NORTH;
                        case KeyEvent.VK_A -> directionPressed = Direction.WEST;
                        case KeyEvent.VK_S -> directionPressed = Direction.SOUTH;
                        case KeyEvent.VK_D -> directionPressed = Direction.EAST;
                        default -> throw new IllegalStateException("Illegal pressed key.");
                    }
                    gameController.movePlayer(directionPressed);
                    playerView.changeImage(
                        directionPressed,
                        cells.get(gameController.getPlayerController().getPlayer().getPosition()).getSize()                        
                    );
                    draw();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        };

        this.mainFrame.addKeyListener(keyListener);
    }

    private void draw() {
        this.cells.clear();
        this.gridPanel.removeAll();
        for (int i = 0; i < this.gameController.getMapController().getMap().getHeight(); i++) {
            for (int j = 0; j < this.gameController.getMapController().getMap().getWidth(); j++) {
                setArea(new Position(j, i));
            }
        }

        this.mainFrame.revalidate();
        this.mainFrame.repaint();
    }

    /**
     * Colors the map areas based on the respective type.
     * @param position is the position of the label.
     */
    private void setArea(final Position position) {
        final JLabel label = position.equals(this.gameController.getPlayerController().getPlayer().getPosition())
                ? this.playerView
                : new JLabel();
        label.setOpaque(true);
        switch (this.gameController.getMapController().getMap().getCellType(position)) {
            case VOLCANO -> label.setBackground(Color.ORANGE);
            case PLAINS -> label.setBackground(Color.YELLOW);
            case FOREST -> label.setBackground(Color.GREEN);
            case STORM -> label.setBackground(Color.DARK_GRAY);
            case SWAMP -> label.setBackground(Color.BLUE);

            default -> label.setBackground(Color.WHITE);
        }
        label.setBorder(new LineBorder(Color.BLACK));
        cells.put(position, label);
        this.gridPanel.add(label);
    }

    private void showInventory(){
        if(this.inventoryView == null){
            this.inventoryView = new InventoryView(this.gameController.getInventoryController());
        }
        this.inventoryView.display();
    }

    public void showMenu(){
        this.gamePanel.setVisible(false);
        this.menuPanel.setVisible(true);
    }

    public void hideMenu(){
        this.gamePanel.setVisible(true);
        this.menuPanel.setVisible(false);
    }

    public void display() {
        this.mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new GameView().display();
    }
    
}
