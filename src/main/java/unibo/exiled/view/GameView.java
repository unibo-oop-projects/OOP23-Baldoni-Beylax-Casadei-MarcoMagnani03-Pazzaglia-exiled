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
import unibo.exiled.view.character.CharacterView;
import unibo.exiled.view.items.GameButton;
import unibo.exiled.view.items.GameLabel;
import unibo.exiled.view.items.GameProgressBar;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GameView{
    // Screen constants
    private final int SIZE;

    // Views
    private CharacterView playerView;
    private InventoryView inventoryView;
    private MenuView menuView;

    // MVC Components(MC)
    private final JFrame mainFrame; 
    private final JPanel gamePanel;
    private final JPanel menuPanel;
    private final JPanel inventoryPanel;
    private JPanel gridPanel;
    private final GameController gameController;

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
        this.inventoryPanel = new JPanel();
        this.gamePanel = new JPanel(new BorderLayout());

        this.menuView = new MenuView(gameController.getInGameMenuController(), this);
        this.inventoryView = new InventoryView(gameController.getInventoryController());

        this.playerView = new CharacterView(List.of("player",
                "boy_up",
                "boy_down",
                "boy_right",
                "boy_left"));

        this.menuPanel.add(menuView);
        this.inventoryPanel.add(inventoryView);
        
        Container contentPanel = this.mainFrame.getContentPane();

        GroupLayout mainLayout = new GroupLayout(contentPanel);
        contentPanel.setLayout(mainLayout);

        mainLayout.setHorizontalGroup(mainLayout.createSequentialGroup().addComponent(menuPanel).addComponent(gamePanel).addComponent(inventoryPanel));
        mainLayout.setVerticalGroup(mainLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(menuPanel).addComponent(gamePanel).addComponent(inventoryPanel));

        this.hideMenu();
        this.hideInventory();

        this.initializeGridComponents();
        this.initializeHUD();
        this.initializeKeyListeners();
        
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
        GameLabel levelLabel = new GameLabel("Level: " + gameController.getPlayerController().player().getLevel());
        GameLabel classLabel = new GameLabel("Class: " +gameController.getPlayerController().getPlayerClass());
        levelLabel.setFont(labelFont);

        JPanel statusPanel = new JPanel(new FlowLayout());
        statusPanel.setBorder(BorderFactory.createEtchedBorder());

        statusPanel.add(healthBar);
        //statusPanel.add(lifeLabel);
        statusPanel.add(levelLabel);
        statusPanel.add(classLabel);

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
        final JLabel label;
        if (position.equals(this.gameController.getPlayerController().player().getPosition())){
            label = this.playerView;
        }
        else if(this.gameController.isEnemyInCell(position)){
            label = new CharacterView(this.gameController.getImagePathOfCharacter(this.gameController.getCharacterInPosition(position)));
        }
        else{
            label = new JLabel();
        }
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
        this.gridPanel.add(label);
    }

    private void showInventory(){
        this.gamePanel.setVisible(false);
        this.inventoryPanel.setVisible(true);
    }

    private void hideInventory(){
        this.gamePanel.setVisible(true);
        this.inventoryPanel.setVisible(false);
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
