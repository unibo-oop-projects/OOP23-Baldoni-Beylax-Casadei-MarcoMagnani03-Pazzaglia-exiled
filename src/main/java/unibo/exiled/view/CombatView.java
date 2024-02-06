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
import java.util.List;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

/**
 * The view that starts when the player engages in a combat with an enemy.
 */
public final class CombatView extends JPanel {
    private static final long serialVersionUID = 1L;

    private final int BUTTON_FONT_SIZE = 40;

    private final transient GameController gameController;

    private final JPanel battlePanel;

    /**
     * The constructor of the combat view.
     *
     * @param gameController The controller that manages the whole game.
     */
    public CombatView(final GameController gameController) {
        final JPanel moveSetPanel;
        this.gameController = gameController;
        this.setLayout(new BorderLayout());

        moveSetPanel = new JPanel(new FlowLayout(1, 10, 10));
        this.battlePanel = new JPanel(new GridLayout(1, 2));
        this.battlePanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));

        this.add(this.battlePanel, BorderLayout.CENTER);
        this.add(moveSetPanel, BorderLayout.SOUTH);

        // Create buttons for each move in the player's move set
        for (final String moveName : this.gameController.getCharacterController().getPlayerMoveSet()) {
            final JButton moveButton = new GameButton(moveName);
            moveSetPanel.add(moveButton);
            moveButton.addActionListener(e -> this.gameController.getCharacterController().attack(true));
        }

        // Display the player character
        final String playerClass = this.gameController.getCharacterController().getPlayerClassName().toLowerCase();
        final JLabel playerLabel = new CharacterView(
                this.gameController.getCharacterController().getImagePathOfCharacter(
                        Constants.PLAYER_PATH + File.separator + playerClass, Constants.PLAYER_NAME + "_" + playerClass));
        this.battlePanel.add(playerLabel);
    }

    /**
     * Sets the enemy character in the combat view.
     */
    public void setEnemy() {
        final Position combatPosition = this.gameController.getCharacterController().getPlayerPosition();
        final List<String> enemyImagePath = this.gameController.getCharacterController().getImagePathOfCharacter(
                Constants.ENEMY_PATH,
                this.gameController.getMapController().getNameOfCharacterInPosition(combatPosition)
                        + File.separator
                        + this.gameController.getMapController().getNameOfCharacterInPosition(combatPosition));
        final JLabel enemyImage = new CharacterView(enemyImagePath);
        final JLabel enemyLabel = new JLabel(
                this.gameController.getMapController().getNameOfCharacterInPosition(combatPosition),
                SwingConstants.CENTER);
        enemyLabel.setFont(FontManager.getCustomFont(BUTTON_FONT_SIZE));
        enemyLabel.setVerticalAlignment(SwingConstants.CENTER);

        final GameLabel healthBar = new GameLabel("Health: "
                + gameController.getCharacterController().getCharacterHealthFromPosition(combatPosition) + " / "
                + gameController.getCharacterController().getCharacterHealthCapFromPosition(combatPosition));
        final GameLabel classLabel = new GameLabel(
                "Class: " + gameController.getCharacterController().getCharacterClassNameFromPosition(combatPosition));
        final JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        statusPanel.setBorder(BorderFactory.createEtchedBorder());
        statusPanel.add(healthBar);
        statusPanel.add(classLabel);

        final JPanel enemyPanel = new JPanel(new BorderLayout(50, 50));
        enemyPanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        enemyPanel.add(enemyLabel, BorderLayout.NORTH);
        enemyPanel.add(enemyImage, BorderLayout.CENTER);
        enemyPanel.add(statusPanel, BorderLayout.SOUTH);

        this.battlePanel.add(enemyPanel);
    }
}
