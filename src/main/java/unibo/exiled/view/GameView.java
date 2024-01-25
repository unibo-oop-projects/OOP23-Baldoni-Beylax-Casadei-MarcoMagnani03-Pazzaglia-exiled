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
import unibo.exiled.view.character.CharacterViewImpl;
import unibo.exiled.view.items.GameButton;
import unibo.exiled.view.items.GameLabel;
import unibo.exiled.view.items.GameProgressBar;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;


public class GameView{
    // Screen constants
    private final int SIZE;

    // Views
    private CharacterViewImpl playerView;
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

        this.gameController = new GameControllerImpl();
        
        this.mainFrame = new JFrame();
        this.mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.mainFrame.setTitle("The Exiled");
        this.mainFrame.setLocationByPlatform(true);
        this.mainFrame.setFocusable(true);

        this.menuPanel = new JPanel();
        this.gamePanel = new JPanel(new BorderLayout());

        this.menuView = new MenuView(gameController.getInGameMenuController(), this);

        this.playerView = new CharacterViewImpl("player",
                "boy_up",
                "boy_down",
                "boy_right",
                "boy_left");

        this.menuPanel.add(menuView);
        
        Container contentPanel = this.mainFrame.getContentPane();

        GroupLayout mainLayout = new GroupLayout(contentPanel);
        contentPanel.setLayout(mainLayout);

        mainLayout.setHorizontalGroup(mainLayout.createSequentialGroup().addComponent(menuPanel).addComponent(gamePanel));
        mainLayout.setVerticalGroup(mainLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(menuPanel).addComponent(gamePanel));

        this.initializeGridComponents();
        this.initializeHUD();
        this.initializeKeyListeners();
        
        this.hideMenu();
    }

    private void initializeHUD(){
        JPanel flowButtonPanelNorth = new JPanel(new FlowLayout());
        JPanel flowButtonPanelSouth = new JPanel(new FlowLayout());
        this.gamePanel.add(flowButtonPanelNorth, BorderLayout.NORTH);
        this.gamePanel.add(flowButtonPanelSouth, BorderLayout.SOUTH);

        // Inventory button
        GameButton inventoryButton = new GameButton("Inventory");
        inventoryButton.addActionListener(e -> showInventory());

        // Menu button
        GameButton menuButton = new GameButton("Menu");
        menuButton.addActionListener(e -> showMenu());
    

        flowButtonPanelNorth.add(inventoryButton);
        flowButtonPanelNorth.add(menuButton);

        // Player information 
        Font labelFont = new Font("Arial", Font.PLAIN, 16);
        GameProgressBar healthBar = new GameProgressBar();
        healthBar.updateProgress(gameController.getPlayerController().player().getAttributes().get(AttributeIdentifier.HEALTH).getValue().get());
        /*JLabel lifeLabel = new JLabel("Health: " + gameController.getPlayerController().getPlayer().getAttributes().get(AttributeIdentifier.HEALTH).getValue().get());
        lifeLabel.setFont(labelFont);*/
        GameLabel levelLabel = new GameLabel("Level: " + gameController.getPlayerController().player().getLevel());
        levelLabel.setFont(labelFont);

        JPanel statusPanel = new JPanel(new FlowLayout());
        statusPanel.setBorder(BorderFactory.createEtchedBorder());

        statusPanel.add(healthBar);
        //statusPanel.add(lifeLabel);
        statusPanel.add(levelLabel);

        flowButtonPanelSouth.add(statusPanel);
    }

    private void initializeGridComponents() {
        this.gridPanel = new JPanel(
            new GridLayout(
                this.gameController.getMapController().map().getWidth(),
                this.gameController.getMapController().map().getHeight()
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
                    playerView.changeImage(directionPressed);
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
        for (int i = 0; i < this.gameController.getMapController().map().getHeight(); i++) {
            for (int j = 0; j < this.gameController.getMapController().map().getWidth(); j++) {
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
        final JLabel label = position.equals(this.gameController.getPlayerController().player().getPosition())
                ? this.playerView
                : new JLabel();
        label.setOpaque(true);
        switch (this.gameController.getMapController().map().getCellType(position)) {
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
            this.inventoryView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    mainFrame.requestFocus();
                }
            });
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
}
