package unibo.exiled.view;

import java.awt.event.KeyListener;
import java.io.File;
import java.util.List;

import unibo.exiled.config.Constants;
import unibo.exiled.controller.GameController;
import unibo.exiled.controller.GameControllerImpl;
import unibo.exiled.model.game.GameModelImpl;
import unibo.exiled.model.map.CellType;
import unibo.exiled.model.utilities.Direction;
import unibo.exiled.model.utilities.Position;
import unibo.exiled.view.character.CharacterView;
import unibo.exiled.view.items.GameButton;
import unibo.exiled.view.items.GameLabel;
import unibo.exiled.view.items.GameProgressBar;
import unibo.exiled.view.menu.MenuView;

import javax.swing.JLabel;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.GroupLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.Container;
import java.awt.BorderLayout;
import javax.swing.border.LineBorder;
import java.awt.event.KeyEvent;

/**
 * The main view of the game, everything starts here.
 */
public final class GameView {
    // Views
    private final CharacterView playerView;
    private final GameOverView gameOverView;

    // MVC Components(MC)
    private final JFrame mainFrame;
    private final JPanel gamePanel;
    private final JPanel menuPanel;
    private final JPanel inventoryPanel;
    private final JPanel combatPanel;
    /**
     * The game controller that manages interaction between the model and the view.
     */
    private final GameController gameController;
    private JPanel gridPanel;

    /**
     * Constructor of the main view.
     */
    public GameView() {
        Constants.loadConfiguration(Constants.DEF_CONFIG_PATH);
        this.gameController = new GameControllerImpl(new GameModelImpl());

        this.mainFrame = new JFrame();
        this.mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.mainFrame.setTitle("The Exiled");
        this.mainFrame.setLocationByPlatform(true);
        this.mainFrame.setFocusable(true);

        this.menuPanel = new JPanel();
        this.inventoryPanel = new JPanel();
        this.combatPanel = new JPanel(new BorderLayout());
        this.gamePanel = new JPanel(new BorderLayout());
        final MenuView menuView = new MenuView(this, null);
        final InventoryView inventoryView = new InventoryView(gameController, this);
        this.gameOverView = new GameOverView();
        this.playerView = new CharacterView(gameController.getImagePathOfCharacter("player", "boy"));

        this.combatPanel.add(new CombatView(this.gameController, this), BorderLayout.CENTER);
        this.menuPanel.add(menuView);
        this.inventoryPanel.add(inventoryView);

        final Container contentPanel = this.mainFrame.getContentPane();

        final GroupLayout mainLayout = new GroupLayout(contentPanel);
        contentPanel.setLayout(mainLayout);

        mainLayout.setHorizontalGroup(mainLayout.createSequentialGroup().addComponent(menuPanel).addComponent(gamePanel)
                .addComponent(combatPanel).addComponent(inventoryPanel));
        mainLayout.setVerticalGroup(mainLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(menuPanel)
                .addComponent(gamePanel)
                .addComponent(inventoryPanel)
                .addComponent(combatPanel));

        this.hideMenu();
        this.hideInventory();
        this.hideCombat();

        this.initializeGridComponents();
        this.initializeHUD();
        this.initializeKeyListeners();

    }

    private void initializeHUD() {
        final JPanel flowButtonPanelNorth = new JPanel(new FlowLayout());
        final JPanel flowButtonPanelSouth = new JPanel(new FlowLayout());
        this.gamePanel.add(flowButtonPanelNorth, BorderLayout.NORTH);
        this.gamePanel.add(flowButtonPanelSouth, BorderLayout.SOUTH);

        // Inventory button
        final GameButton inventoryButton = new GameButton("Inventory");
        inventoryButton.addActionListener(e -> showInventory());

        // Menu button
        final GameButton menuButton = new GameButton("Menu");
        menuButton.addActionListener(e -> showMenu());

        flowButtonPanelNorth.add(inventoryButton);
        flowButtonPanelNorth.add(menuButton);

        // Player information
        final GameProgressBar healthBar = new GameProgressBar();
        healthBar.updateProgress(gameController.getPlayerHealth());
        final GameLabel levelLabel = new GameLabel("Level: " + gameController.getPlayerLevel());
        final GameLabel classLabel = new GameLabel("Class: " + gameController.getPlayerClassName());

        final JPanel statusPanel = new JPanel(new FlowLayout());
        statusPanel.setBorder(BorderFactory.createEtchedBorder());

        statusPanel.add(healthBar);
        statusPanel.add(levelLabel);
        statusPanel.add(classLabel);

        flowButtonPanelSouth.add(statusPanel);
    }

    private void initializeGridComponents() {
        this.gridPanel = new JPanel(
                new GridLayout(this.gameController.getMapSize(),
                        this.gameController.getMapSize()));
        draw();
        this.gamePanel.add(this.gridPanel, BorderLayout.CENTER);
    }

    private void initializeKeyListeners() {
        // Listener initialization
        final KeyListener keyListener = new KeyListener() {

            private static Direction getDirection(final KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W -> {
                        return Direction.NORTH;
                    }
                    case KeyEvent.VK_A -> {
                        return Direction.WEST;
                    }
                    case KeyEvent.VK_S -> {
                        return Direction.SOUTH;
                    }
                    case KeyEvent.VK_D -> {
                        return Direction.EAST;
                    }
                    default -> throw new IllegalStateException("Illegal pressed key.");
                }
            }

            @Override
            public void keyTyped(final KeyEvent e) {
            }

            @Override
            public void keyPressed(final KeyEvent e) {
                if ((e.getKeyCode() == KeyEvent.VK_W
                        || e.getKeyCode() == KeyEvent.VK_A
                        || e.getKeyCode() == KeyEvent.VK_S
                        || e.getKeyCode() == KeyEvent.VK_D) && !combatPanel.isVisible()) {
                    final Direction directionPressed = getDirection(e);
                    gameController.movePlayer(directionPressed);
                    gameController.moveEnemies();

                    if (gameController.isOver()) {
                        gameOverView.display();
                        mainFrame.dispose();
                    } else if (gameController.isEnemyInCell(gameController.getPlayerPosition())) {
                        showCombat();
                        draw();
                    } else {
                        playerView.changeImage(directionPressed);
                        playerView.changeImage(directionPressed);
                        draw();
                    }
                }
            }

            @Override
            public void keyReleased(final KeyEvent e) {
            }
        };

        this.mainFrame.addKeyListener(keyListener);
    }

    private void draw() {
        this.gridPanel.removeAll();
        for (int i = 0; i < this.gameController.getMapSize(); i++) {
            for (int j = 0; j < this.gameController.getMapSize(); j++) {
                setArea(new Position(j, i));
            }
        }

        this.mainFrame.revalidate();
        this.mainFrame.repaint();
    }

    /**
     * Colors the map areas based on the respective type.
     *
     * @param position is the position of the label.
     */
    private void setArea(final Position position) {
        final JLabel label;

        if (position.equals(gameController.getPlayerPosition())) {
            label = playerView;
        } else if (gameController.isEnemyInCell(position)) {
            final List<String> characterImagePath = gameController
                    .getImagePathOfCharacter("enemy", gameController.getNameOfCharacterInPosition(position)
                            + File.separator + gameController.getNameOfCharacterInPosition(position));
            label = new CharacterView(characterImagePath);
        } else {
            label = new JLabel();
        }

        setLabelMapProperties(label, position);
        gridPanel.add(label);
    }

    private void setLabelMapProperties(final JLabel label, final Position position) {
        label.setOpaque(true);
        final CellType cellType = gameController.getCellType(position);
        final Color backgroundColor = getBackgroundColor(cellType);
        label.setBackground(backgroundColor);
        label.setBorder(new LineBorder(Color.BLACK));
    }

    private Color getBackgroundColor(final CellType cellType) {
        switch (cellType) {
            case VOLCANO -> {
                return Color.ORANGE;
            }
            case PLAINS -> {
                return Color.YELLOW;
            }
            case FOREST -> {
                return Color.GREEN;
            }
            case STORM -> {
                return Color.DARK_GRAY;
            }
            case SWAMP -> {
                return Color.BLUE;
            }
            default -> {
                return Color.WHITE;
            }
        }
    }

    /**
     * Shows the inventory view.
     */
    private void showInventory() {
        this.gamePanel.setVisible(false);
        this.inventoryPanel.setVisible(true);
    }

    /**
     * Hides the inventory view.
     */
    public void hideInventory() {
        this.gamePanel.setVisible(true);
        this.inventoryPanel.setVisible(false);
    }

    /**
     * Shows the menu view.
     */
    public void showMenu() {
        this.gamePanel.setVisible(false);
        this.menuPanel.setVisible(true);
    }

    /**
     * Hides the menu view.
     */
    public void hideMenu() {
        this.gamePanel.setVisible(true);
        this.menuPanel.setVisible(false);
    }

    /**
     * Shows the combat view.
     */
    public void showCombat() {
        this.gamePanel.setVisible(false);
        this.combatPanel.setVisible(true);
    }

    /**
     * Hides the combat view.
     */
    public void hideCombat() {
        this.gamePanel.setVisible(true);
        this.combatPanel.setVisible(false);
    }

    /**
     * Makes the main frame visible on screen.
     */
    public void display() {
        this.mainFrame.setVisible(true);
    }
}
