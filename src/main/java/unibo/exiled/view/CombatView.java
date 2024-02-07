package unibo.exiled.view;

import unibo.exiled.config.Constants;
import unibo.exiled.controller.GameController;
import unibo.exiled.model.utilities.FontManager;
import unibo.exiled.model.utilities.Position;
import unibo.exiled.view.character.CharacterView;
import unibo.exiled.view.items.GameButton;
import unibo.exiled.view.items.GameLabel;

import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.io.File;
import java.io.Serial;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Locale;
import java.util.Set;

/**
 * The view that starts when the player engages in a combat with an enemy.
 */
public final class CombatView extends JPanel {
    @Serial
    private static final long serialVersionUID = 1L;
    private static final int BUTTON_FONT_SIZE = 40;
    private static final int EXTERNAL_PADDING = 100;
    private static final int ENEMY_PANEL_GAP = 50;
    private static final int STATUS_PANEL_H_GAP = 20;
    private static final int STATUS_PANEL_V_GAP = 5;
    private static final int MOVE_SET_PANEL_GAP = 10;
    private static final int BATTLE_PANEL_ROWS = 1;
    private static final int BATTLE_PANEL_COLS = 2;
    private static final int HEALTH_CRITICAL_PERCENTAGE = 25;

    private final transient GameController gameController;
    private final transient GameView gameView;

    private final JPanel moveSetPanel;
    private final JPanel enemyPanel;
    private final JLabel enemyLabel;
    private final GameLabel enemyHealthBar;
    private final GameLabel enemyClassLabel;
    private transient Position combatPosition;

    /**
     * The constructor of the combat view.
     *
     * @param gameController The controller that manages the whole game.
     * @param gameView       The game view where the map is displayed.
     */
    public CombatView(final GameController gameController, final GameView gameView) {
        this.gameController = gameController;
        this.gameView = gameView;
        this.setLayout(new BorderLayout());

        this.moveSetPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, MOVE_SET_PANEL_GAP, MOVE_SET_PANEL_GAP));
        final JPanel battlePanel = new JPanel(new GridLayout(BATTLE_PANEL_ROWS, BATTLE_PANEL_COLS));
        battlePanel.setBorder(BorderFactory.createEmptyBorder(EXTERNAL_PADDING, EXTERNAL_PADDING, EXTERNAL_PADDING,
                EXTERNAL_PADDING));

        this.add(battlePanel, BorderLayout.CENTER);
        this.add(moveSetPanel, BorderLayout.SOUTH);

        // Display the player character
        final String playerClass = this.gameController.getCharacterController()
                .getPlayerClassName().toLowerCase(Locale.ROOT);
        final JLabel playerLabel = new CharacterView(
                this.gameController.getCharacterController().getImagePathOfCharacter(
                        Constants.PLAYER_PATH + File.separator + playerClass,
                        Constants.PLAYER_NAME + "_" + playerClass));
        battlePanel.add(playerLabel);

        this.enemyLabel = new JLabel("", SwingConstants.CENTER);
        this.enemyLabel.setFont(FontManager.getCustomFont(BUTTON_FONT_SIZE));
        this.enemyLabel.setVerticalAlignment(SwingConstants.CENTER);

        this.enemyHealthBar = new GameLabel("");
        this.enemyClassLabel = new GameLabel("");
        final JPanel statusPanel = new JPanel(
                new FlowLayout(FlowLayout.CENTER, STATUS_PANEL_H_GAP, STATUS_PANEL_V_GAP));
        statusPanel.setBorder(BorderFactory.createEtchedBorder());
        statusPanel.add(this.enemyHealthBar);
        statusPanel.add(this.enemyClassLabel);

        this.enemyPanel = new JPanel(new BorderLayout(ENEMY_PANEL_GAP, ENEMY_PANEL_GAP));
        this.enemyPanel.setBorder(BorderFactory.createEmptyBorder(EXTERNAL_PADDING, EXTERNAL_PADDING, EXTERNAL_PADDING,
                EXTERNAL_PADDING));
        this.enemyPanel.add(enemyLabel, BorderLayout.NORTH);
        this.enemyPanel.add(statusPanel, BorderLayout.SOUTH);

        battlePanel.add(this.enemyPanel);
    }

    /**
     * Sets the enemy character in the combat view.
     */
    public void setEnemy() {
        this.combatPosition = this.gameController.getCharacterController().getPlayerPosition();
        final List<String> enemyImagePath = this.gameController.getCharacterController().getImagePathOfCharacter(
                Constants.ENEMY_PATH,
                this.gameController.getMapController().getNameOfCharacterInPosition(combatPosition)
                        + File.separator
                        + this.gameController.getMapController().getNameOfCharacterInPosition(this.combatPosition));
        final JLabel enemyImage = new CharacterView(enemyImagePath);
        this.enemyLabel
                .setText(this.gameController.getMapController().getNameOfCharacterInPosition(this.combatPosition));
        this.enemyClassLabel.setText(
                "Class: " + gameController.getCharacterController()
                        .getCharacterClassNameFromPosition(this.combatPosition));
        this.enemyPanel.add(enemyImage, BorderLayout.CENTER);
        refreshEnemy();

        // Create buttons for each move in the player's move set
        this.moveSetPanel.removeAll();
        for (final String moveName : this.gameController.getCharacterController().getPlayerMoveSet()) {
            final JButton moveButton = new GameButton(moveName);
            this.moveSetPanel.add(moveButton);
            moveButton.addActionListener(e -> {
                final boolean isEnemyDead = this.gameController.getCharacterController().attack(true, moveName,
                        this.combatPosition);
                refreshEnemy();

                Set.of(this.moveSetPanel.getComponents()).stream().map(b -> {
                    b.setEnabled(false);
                    return b;
                });

                // Enemy turn to attack
                final boolean isPlayerDead = this.gameController.getCharacterController().attack(false,
                        this.gameController.getCharacterController()
                                .getCharacterRandomMoveNameFromPosition(combatPosition),
                        this.combatPosition);
                this.gameView.refreshStatusPanel();
                Set.of(this.moveSetPanel.getComponents()).stream().map(b -> {
                    b.setEnabled(true);
                    return b;
                });
                if (isPlayerDead) {
                    this.gameView.hideCombat();
                    this.gameView.draw();
                }
                if (isEnemyDead) {
                    this.gameController.getCharacterController().removeEnemyFromPosition(combatPosition);
                    this.gameView.hideCombat();
                }
            });
        }
    }

    /**
     * Refreshes the enemy view.
     */
    private void refreshEnemy() {
        final double health = gameController.getCharacterController()
                .getCharacterHealthFromPosition(this.combatPosition);
        final double healthCap = gameController.getCharacterController()
                .getCharacterHealthCapFromPosition(this.combatPosition);
        this.enemyHealthBar.setText("Health: " + health + " / " + healthCap);
        if (health <= (healthCap / 100) * HEALTH_CRITICAL_PERCENTAGE) {
            this.enemyHealthBar.setForeground(Color.RED);
        } else {
            this.enemyHealthBar.setForeground(Color.BLACK);
        }
    }
}
