package unibo.exiled.view;

import unibo.exiled.config.Constants;
import unibo.exiled.controller.GameController;
import unibo.exiled.model.utilities.ElementalType;
import unibo.exiled.model.utilities.FontManager;
import unibo.exiled.model.utilities.Position;
import unibo.exiled.view.character.CharacterView;
import unibo.exiled.view.items.GameButton;
import unibo.exiled.view.items.GameLabel;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EtchedBorder;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.io.File;
import java.io.Serial;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.util.Locale;

/**
 * The view that starts when the player engages in a combat with an enemy.
 */
public final class CombatView extends JPanel {
    @Serial
    private static final long serialVersionUID = 1L;
    private static final int ENEMY_ATTACK_DELAY = 2000;
    private static final int ENEMY_LIFE_GLITCH_DELAY = 150;
    private static final int BUTTON_FONT_SIZE = 40;
    private static final int EXTERNAL_PADDING = 100;
    private static final int EXTERNAL_NO_PADDING = 0;
    private static final int ENEMY_PANEL_TEXT_PADDING = 10;
    private static final int STATUS_PANEL_H_GAP = 20;
    private static final int STATUS_PANEL_V_GAP = 0;
    private static final int MOVE_SET_PANEL_GAP = 10;
    private static final int BATTLE_PANEL_ROWS = 1;
    private static final int BATTLE_PANEL_COLS = 2;
    private static final int HEALTH_CRITICAL_PERCENTAGE = 25;

    private final transient GameController gameController;
    private final transient GameView gameView;
    private final JPanel moveSetPanel;
    private final JPanel enemyImagePanel;
    private final JTextArea enemyMove;
    private final GameLabel enemyHealthBar;
    private final GameLabel enemyClassLabel;
    private transient Position combatPosition;
    private transient Timer enemyAttackTimer;

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

        this.enemyImagePanel = new JPanel(new BorderLayout());

        final JPanel enemyMoveJPanel = new JPanel(new BorderLayout());
        this.enemyMove = new JTextArea("");
        this.enemyMove.setLineWrap(true);
        this.enemyMove.setEditable(false);
        this.enemyMove.setFont(FontManager.getCustomFont());
        this.enemyMove.setForeground(Color.BLACK);
        this.enemyMove.setBorder(BorderFactory.createEmptyBorder(ENEMY_PANEL_TEXT_PADDING, ENEMY_PANEL_TEXT_PADDING,
                ENEMY_PANEL_TEXT_PADDING, ENEMY_PANEL_TEXT_PADDING));
        enemyMoveJPanel.add(this.enemyMove, BorderLayout.CENTER);
        enemyMoveJPanel.setBorder(BorderFactory.createEtchedBorder());

        this.enemyHealthBar = new GameLabel("");
        this.enemyClassLabel = new GameLabel("");
        final JPanel enemyStatusPanel = new JPanel(
                new FlowLayout(FlowLayout.CENTER, STATUS_PANEL_H_GAP, STATUS_PANEL_V_GAP));
        enemyStatusPanel.setBorder(BorderFactory.createEtchedBorder());
        enemyStatusPanel.add(this.enemyHealthBar);
        enemyStatusPanel.add(this.enemyClassLabel);

        final JPanel enemyPanel = new JPanel();
        enemyPanel.setLayout(new BoxLayout(enemyPanel, BoxLayout.Y_AXIS));
        enemyPanel
                .setBorder(BorderFactory.createEmptyBorder(EXTERNAL_NO_PADDING, EXTERNAL_PADDING, EXTERNAL_NO_PADDING,
                        EXTERNAL_PADDING));

        enemyPanel.add(this.enemyImagePanel);
        enemyPanel.add(enemyStatusPanel);
        enemyPanel.add(enemyMoveJPanel);

        battlePanel.add(enemyPanel);
    }

    /**
     * Sets the enemy character in the combat view.
     */
    public void setEnemy() {
        this.combatPosition = this.gameController.getCharacterController().getPlayerPosition();

        // Remove old enemy
        final List<String> enemyImagePath = this.gameController.getCharacterController().getImagePathOfCharacter(
                Constants.ENEMY_PATH,
                this.gameController.getMapController().getNameOfCharacterInPosition(combatPosition)
                        + File.separator
                        + this.gameController.getMapController().getNameOfCharacterInPosition(this.combatPosition));
        final CharacterView enemyImage = new CharacterView(enemyImagePath);
        this.enemyMove.setText("");
        final String enemyName = this.gameController.getMapController()
                .getNameOfCharacterInPosition(this.combatPosition);
        this.enemyClassLabel.setText(
                "Class: " + gameController.getCharacterController()
                        .getCharacterClassNameFromPosition(this.combatPosition));

        this.enemyImagePanel.removeAll();
        this.enemyImagePanel.add(enemyImage);
        refreshEnemy();

        // Create buttons for each move in the player's move set
        this.moveSetPanel.removeAll();
        for (final String moveName : this.gameController.getCharacterController().getPlayerMoveSet()) {
            final double moveDamage = this.gameController.getCharacterController().getMagicMoveDamage(moveName);
            final ElementalType moveType = this.gameController.getCharacterController()
                    .getMagicMoveElementalType(moveName);
            final JButton moveButton = new GameButton(moveName + " (" + moveDamage + ")");
            moveButton.setBorder(BorderFactory.createLineBorder(moveType.getElementalColor(), 3));
            this.moveSetPanel.add(moveButton);
            moveButton.addActionListener(e -> {
                this.enemyMove.setText(enemyName + " attacking...");

                final boolean isEnemyDead = this.gameController
                        .getCharacterController().attack(true, moveName,
                                this.combatPosition);
                refreshEnemy();
                this.enemyHealthBar.setForeground(Color.ORANGE);
                this.enemyAttackTimer = new Timer(ENEMY_LIFE_GLITCH_DELAY, evt -> {
                    enemyHealthBar.setForeground(Color.BLACK);
                    refreshEnemy();
                });
                this.enemyAttackTimer.setRepeats(false);
                this.enemyAttackTimer.start();

                if (isEnemyDead) {
                    this.gameView.createHUD();
                    this.gameView.updateInventory();
                    this.gameView.hideCombat();
                } else {
                    // Disable buttons
                    for (final Component button : this.moveSetPanel.getComponents()) {
                        button.setEnabled(false);
                    }

                    this.enemyAttackTimer = new Timer(ENEMY_ATTACK_DELAY, evt -> {
                        // Enemy turn to attack
                        final String enemyMoveName = gameController.getCharacterController()
                                .getCharacterRandomMoveNameFromPosition(combatPosition);
                        final String enemyMoveDescription = gameController.getCharacterController()
                                .getMagicMoveDescription(enemyMoveName);
                        final double enemyMoveDamage = gameController.getCharacterController()
                                .getMagicMoveDamage(enemyMoveName);
                        final boolean isPlayerDead = gameController
                                .getCharacterController().attack(false,
                                        enemyMoveName,
                                        combatPosition);

                        enemyMove.setText(
                                enemyName + " used: " + enemyMoveName + "(" + enemyMoveDamage + ")" + "\n\n"
                                        + enemyMoveDescription);
                        gameView.createHUD();

                        if (isPlayerDead) {
                            gameView.hideCombat();
                            gameView.draw();
                        } else {
                            // Enable buttons
                            for (final Component button : moveSetPanel.getComponents()) {
                                button.setEnabled(true);
                            }
                        }
                    });
                    this.enemyAttackTimer.setRepeats(false);
                    this.enemyAttackTimer.start();
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
            this.enemyHealthBar.setForeground(Color.GREEN);
        }

        this.revalidate();
        this.repaint();
    }
}
