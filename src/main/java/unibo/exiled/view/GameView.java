package unibo.exiled.view;

import javax.swing.*;
import javax.swing.border.LineBorder;

import unibo.exiled.config.Constants;
import unibo.exiled.controller.GameController;
import unibo.exiled.controller.GameControllerImpl;
import unibo.exiled.model.character.attributes.AttributeType;
import unibo.exiled.model.utilities.Direction;
import unibo.exiled.model.utilities.Position;
import unibo.exiled.view.Menu.MenuView;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;


public class GameView extends JFrame{
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
    private final Map<Position, JPanel> cells = new HashMap<>();

    public GameView(){
        // Screen size initialization.
        Dimension SCREEN = Toolkit.getDefaultToolkit().getScreenSize();
        double SCREEN_WIDTH = SCREEN.getWidth();
        double SCREEN_HEIGHT = SCREEN.getHeight();

        Constants.loadConfiguration(Constants.DEF_CONFIG_PATH);
        SIZE = Integer.parseInt(Constants.getConstantOf("MAP_SIZE"));

        this.gameController = new GameControllerImpl(SIZE);
        
        this.mainFrame = new JFrame();
        this.mainFrame.setSize((int) SCREEN_WIDTH, (int) SCREEN_HEIGHT);
        this.mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.mainFrame.setTitle("The Exiled");
        this.mainFrame.setLocationByPlatform(true);
        this.mainFrame.setFocusable(true);
        
        this.menuPanel = new JPanel();
        this.gamePanel = new JPanel(new BorderLayout());

        Container contentPanel = this.mainFrame.getContentPane();

        GroupLayout mainLayout = new GroupLayout(contentPanel);
        contentPanel.setLayout(mainLayout);

        setTitle("The Exiled");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        mainLayout.setHorizontalGroup(mainLayout.createSequentialGroup().addComponent(menuPanel).addComponent(gamePanel));
        mainLayout.setVerticalGroup(mainLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(menuPanel).addComponent(gamePanel));
        
        this.showMenu();
        this.showPlayer();
        
        initializeGridComponents();
        initializeHUD();
    }

    private void initializeHUD(){
        JPanel flowButtonPanelSouth = new JPanel(new FlowLayout());
        JPanel flowButtonPanelNorth = new JPanel(new FlowLayout());
        this.gamePanel.add(flowButtonPanelNorth, BorderLayout.NORTH);
        this.gamePanel.add(flowButtonPanelSouth, BorderLayout.SOUTH);

        // Inventory button
        JButton inventoryButton = new JButton("Inventory");
        inventoryButton.addActionListener(e -> showInventory());
        
        // Menu Button
        JButton menuButton = new JButton("Menu");
        menuButton.addActionListener(e -> showMenu());

        flowButtonPanelNorth.add(inventoryButton);
        flowButtonPanelNorth.add(menuButton);

        // Player information 
        Font labelFont = new Font("Arial", Font.PLAIN, 16);
        JLabel lifeLabel = new JLabel("Health: " + gameController.getPlayerController().getPlayer().getAttributes().getAttributeOfType(AttributeType.HEALTH)); 
        lifeLabel.setFont(labelFont);
        JLabel levelLabel = new JLabel("Level: " + gameController.getPlayerController().getPlayer().getLevel());
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
     * @param cell is the JButton to set its background.
     */
    private void setAreas(final Position cell){
        final JPanel pane = cells.get(cell);
        switch (gameController.getMapController().getMap().getCellType(cell)) {
            case VOLCANO -> pane.setBackground(Color.ORANGE);
            case PLAINS -> pane.setBackground(Color.YELLOW);
            case FOREST -> pane.setBackground(Color.GREEN);
            case STORM -> pane.setBackground(Color.DARK_GRAY);
            case SWAMP -> pane.setBackground(Color.BLUE);
        
            default -> pane.setBackground(Color.WHITE);
        }
        pane.setBorder(new LineBorder(Color.BLACK));
    }

    private void initializeGridComponents(){
        menuPanel.add(menuView);

        gridPanel = new JPanel(new GridLayout(gameController.getMapController().getMap().getWidth(), gameController.getMapController().getMap().getHeight()));
    
        // Listener initialization
        KeyListener keyListener = new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_D){
                    Direction directionPressed = null;
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_W -> directionPressed = Direction.NORTH;
                        case KeyEvent.VK_A -> directionPressed = Direction.WEST;
                        case KeyEvent.VK_S -> directionPressed = Direction.SOUTH;
                        case KeyEvent.VK_D -> directionPressed = Direction.EAST;
    
                        default -> {}
                    }
                    gameController.movePlayer(directionPressed);
                    playerView.changeImage(directionPressed, cells.get(gameController.getPlayerController().getPlayer().getPosition()).getSize());
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        };

        this.mainFrame.addKeyListener(keyListener);

        draw();

        this.gamePanel.add(gridPanel, BorderLayout.CENTER);

        this.mainFrame.revalidate();
        this.mainFrame.repaint();
    }
     
    private void draw(){
        gridPanel.removeAll();
        for (int i = 0; i < gameController.getMapController().getMap().getHeight(); i++) {
            for (int j = 0; j < gameController.getMapController().getMap().getWidth(); j++) {
                final JPanel cell;
                final Position pos = new Position(j, i);

                if(pos.equals(gameController.getPlayerController().getPlayer().getPosition())){
                    cell = playerView;
                }else{
                    cell = new JPanel(new FlowLayout());
                }
                cells.put(pos, cell);
                this.setAreas(pos);
                gridPanel.add(cell);
            }
        }
    }

    private void showPlayer() {
        if(playerView == null){
            playerView = new PlayerView();
        }
    }

    private void showInventory(){
        if(inventoryView == null){
            inventoryView = new InventoryView(gameController.getInventoryController());
        }
        inventoryView.display();
    }

    public void showMenu(){
        if(menuView == null){
            menuView = new MenuView(gameController.getMenuController(), this);
        }
        this.gamePanel.setVisible(false);
        this.menuPanel.setVisible(true);

        this.mainFrame.revalidate();
        this.mainFrame.repaint();
    }

    public void hideMenu(){
        this.gamePanel.setVisible(true);
        this.menuPanel.setVisible(false);
    
        this.mainFrame.revalidate();
        this.mainFrame.repaint();
    }

    private void showGameOver(){
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
