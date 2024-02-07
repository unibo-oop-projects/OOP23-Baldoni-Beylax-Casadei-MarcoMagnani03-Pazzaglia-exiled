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

/**
 * The view that starts when the player engages in a combat with an enemy.
 */
public final class CombatView extends JPanel {
    @Serial
    private static final long serialVersionUID = 1L;
    private static final int MILLIS = 2000;
    private static final int BUTTON_FONT_SIZE = 40;

    private final transient GameController gameController;

    private final JPanel moveSetPanel;
    private final JPanel enemyPanel;
    private final JLabel enemyLabel;
    private final GameLabel enemyHealthBar;
    private final GameLabel enemyClassLabel;
    private boolean hasACharacterDied = false;
    private transient Position combatPosition;

    /**
     * The constructor of the combat view.
     *
     * @param gameController The controller that manages the whole game.
     */
    public CombatView(final GameController gameController) {
        this.gameController = gameController;
        this.setLayout(new BorderLayout());

        this.moveSetPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JPanel battlePanel = new JPanel(new GridLayout(1, 2));
        battlePanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));

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
        final JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        statusPanel.setBorder(BorderFactory.createEtchedBorder());
        statusPanel.add(this.enemyHealthBar);
        statusPanel.add(this.enemyClassLabel);

        this.enemyPanel = new JPanel(new BorderLayout(50, 50));
        this.enemyPanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
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
        for (final String moveName : this.gameController.getCharacterController().getPlayerMoveSet()) {
            final JButton moveButton = new GameButton(moveName);
            this.moveSetPanel.add(moveButton);
            moveButton.addActionListener(e -> {
                hasACharacterDied = this.gameController.getCharacterController().attack(true, moveName,
                        this.combatPosition);
                refreshEnemy();
                try {
                    Thread.sleep(MILLIS);
                } catch (final InterruptedException ignored) {
                }
                // Enemy turn to attack
                hasACharacterDied = this.gameController.getCharacterController().attack(false,
                        this.gameController.getCharacterController()
                                .getCharacterRandomMoveNameFromPosition(combatPosition),
                        this.combatPosition);
                // TODO: Redraw barra della vita del player
            });
        }
    }

    private void refreshEnemy() {
        final double health = gameController.getCharacterController()
                .getCharacterHealthFromPosition(this.combatPosition);
        final double healthCap = gameController.getCharacterController()
                .getCharacterHealthCapFromPosition(this.combatPosition);
        this.enemyHealthBar.setText("Health: " + health + " / " + healthCap);
        if (health <= (healthCap / 100) * 25) {
            this.enemyHealthBar.setForeground(Color.RED);
        }
    }
}
