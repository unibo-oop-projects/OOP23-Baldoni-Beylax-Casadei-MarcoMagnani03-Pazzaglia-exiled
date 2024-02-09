package unibo.exiled.view;

import java.awt.event.WindowEvent;
import java.io.File;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import unibo.exiled.config.Constants;
import unibo.exiled.controller.GameController;
import unibo.exiled.controller.MenuControllerImpl;
import unibo.exiled.model.map.CellType;
import unibo.exiled.model.utilities.Position;
import unibo.exiled.view.character.CharacterView;

import javax.swing.JLabel;
import javax.swing.JFrame;
import java.awt.Color;

import javax.swing.GroupLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.Container;
import java.awt.BorderLayout;
import javax.swing.border.LineBorder;

/**
 * The main view of the game, everything starts here.
 */
public final class GameView {
    private static final int STATUS_PANEL_H_GAP = 20;
    private static final int STATUS_PANEL_V_GAP = 5;

    // Views
    private final CharacterView playerView;
    private final CombatView combatView;
    private final InventoryView inventoryView;
    private final GameOverView gameOverView;
    private final Hud hud;

    // MVC Components(MC)
    private final JFrame mainFrame;
    private final JPanel gameHudPanel;
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
     *
     * @param gameController The game controller that manages interaction between
     *                       the model and the view.
     */
    public GameView(final GameController gameController) {
        final JPanel gameContainerPanel;
        Constants.loadConfiguration(Constants.DEF_CONFIG_PATH);
        this.gameController = gameController;

        this.mainFrame = new JFrame();
        this.mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.mainFrame.setTitle("The Exiled");
        this.mainFrame.setLocationByPlatform(true);
        this.mainFrame.setFocusable(true);

        gameContainerPanel = new JPanel();
        this.menuPanel = new JPanel();
        this.inventoryPanel = new JPanel();
        this.combatPanel = new JPanel(new BorderLayout());
        this.gamePanel = new JPanel(new BorderLayout());
        this.gameHudPanel = new JPanel(new BorderLayout());
        final JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, STATUS_PANEL_H_GAP, STATUS_PANEL_V_GAP));

        final GroupLayout gamePanelLayout = new GroupLayout(gameContainerPanel);
        gameContainerPanel.setLayout(gamePanelLayout);

        gamePanelLayout.setHorizontalGroup(gamePanelLayout.createSequentialGroup()
                .addComponent(gamePanel)
                .addComponent(combatPanel));
        gamePanelLayout.setVerticalGroup(gamePanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(gamePanel)
                .addComponent(combatPanel));
        this.gameHudPanel.add(gameContainerPanel, BorderLayout.CENTER);

        this.inventoryView = new InventoryView(this.gameController, this);
        this.gameOverView = new GameOverView();
        final String playerClass = this.gameController
                .getCharacterController().getPlayerClassName().toLowerCase(Locale.ROOT);
        this.playerView = new CharacterView(
                this.gameController.getCharacterController().getImagePathOfCharacter(
                        Constants.PLAYER_PATH + File.separator + playerClass,
                        Constants.PLAYER_NAME + "_" + playerClass));
        this.combatView = new CombatView(this.gameController, this);
        final MenuView menuView = new MenuView(new MenuControllerImpl().getInGameMenuItems(), Optional.of(this));
        this.hud = new Hud(this, gameController, gameHudPanel, statusPanel);

        this.menuPanel.add(menuView);
        this.inventoryPanel.add(this.inventoryView);
        this.combatPanel.add(combatView, BorderLayout.CENTER);

        final Container contentPanel = this.mainFrame.getContentPane();

        final GroupLayout mainLayout = new GroupLayout(contentPanel);
        contentPanel.setLayout(mainLayout);

        mainLayout.setHorizontalGroup(mainLayout.createSequentialGroup()
                .addComponent(menuPanel)
                .addComponent(gameHudPanel)
                .addComponent(inventoryPanel));
        mainLayout.setVerticalGroup(mainLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(menuPanel)
                .addComponent(gameHudPanel)
                .addComponent(inventoryPanel));

        this.hideMenu();
        this.hideInventory();
        this.hideCombat();

        this.initializeGridComponents();
        this.initializeHUD();
        this.mainFrame.addKeyListener(new MovementKeyListener(gameController, this, playerView));
    }

    /**
     * This method sets up buttons for inventory and menu, displays player health,
     * level, and class information.
     */
    private void initializeHUD() {
        this.hud.initialize();
    }

    /**
     * Refreshes the player status panel.
     */
    public void refreshStatusPanel() {
        this.hud.refreshStatusPanel();
    }

    private void initializeGridComponents() {
        this.gridPanel = new JPanel(
                new GridLayout(this.gameController.getMapController().getMapSize(),
                        this.gameController.getMapController().getMapSize()));
        draw();
        this.gamePanel.add(this.gridPanel, BorderLayout.CENTER);
    }

    /**
     * Draws the grid game panel.
     */
    public void draw() {
        if (this.gameController.isOver()) {
            this.gameOverView.display();
            this.mainFrame.dispose();
        }

        final Position playerPosition = gameController.getCharacterController().getPlayerPosition();
        final int mapSize = this.gameController.getMapController().getMapSize();
        final int range = 5;

        final int startingY = Math.max(0, playerPosition.y() - range);
        final int endingY = Math.min(mapSize, playerPosition.y() + range + 1);
        final int startingX = Math.max(0, playerPosition.x() - range);
        final int endingX = Math.min(mapSize, playerPosition.x() + range + 1);

        this.gamePanel.remove(this.gridPanel);

        this.gridPanel = new JPanel(new GridLayout(endingY - startingY, endingX - startingX));

        this.gamePanel.add(this.gridPanel, BorderLayout.CENTER);

        for (int i = startingY; i < endingY; i++) {
            for (int j = startingX; j < endingX; j++) {
                placeCell(new Position(j, i));
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
    private void placeCell(final Position position) {
        final JLabel label;

        if (position.equals(gameController.getCharacterController().getPlayerPosition())) {
            label = playerView;
        } else if (gameController.getMapController().isEnemyInCell(position)) {
            final List<String> characterImagePath = gameController.getCharacterController()
                    .getImagePathOfCharacter(Constants.ENEMY_PATH,
                            gameController.getMapController().getNameOfCharacterInPosition(position)
                                    + File.separator
                                    + gameController.getMapController().getNameOfCharacterInPosition(position));
            label = new CharacterView(characterImagePath);
            ((CharacterView) label)
                    .changeImage(gameController.getMapController()
                                    .getLastDirectionOfCharacterInPosition(position),
                            gameController.getCharacterController().getIfCharacterInPositionIsMoving(position));
        } else {
            label = new JLabel();
        }

        configLabels(label, position);
        gridPanel.add(label);
    }

    private void configLabels(final JLabel label, final Position position) {
        label.setOpaque(true);
        final CellType cellType = gameController.getMapController().getCellType(position);
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
     * Returns true if the game is currently in combat mode.
     *
     * @return true if the game is in combat mode, otherwise false.
     */
    public boolean isInCombat() {
        return this.combatPanel.isVisible();
    }

    /**
     * Returns true if the game map is currently in-game mode.
     *
     * @return true if the game map is in-game mode, otherwise false.
     */
    public boolean isInGame() {
        return this.gameHudPanel.isVisible();
    }

    /**
     * Shows the combat view.
     */
    public void initializeCombat() {
        this.combatView.setEnemy();
        this.showCombat();
    }

    /**
     * Shows the inventory view.
     */
    public void showInventory() {
        this.gameHudPanel.setVisible(false);
        this.inventoryPanel.setVisible(true);
    }

    /**
     * Hides the inventory view.
     */
    public void hideInventory() {
        this.gameHudPanel.setVisible(true);
        this.inventoryPanel.setVisible(false);
    }

    /**
     * Updates the buttons of the inventory.
     */
    public void updateInventory() {
        this.inventoryView.updateInventoryButtons();
    }

    /**
     * Shows the menu view.
     */
    public void showMenu() {
        this.gameHudPanel.setVisible(false);
        this.menuPanel.setVisible(true);
    }

    /**
     * Hides the menu view.
     */
    public void hideMenu() {
        this.gameHudPanel.setVisible(true);
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

    /**
     * Close the main frame.
     */
    public void close() {
        mainFrame.dispatchEvent(new WindowEvent(mainFrame, WindowEvent.WINDOW_CLOSING));
    }
}
