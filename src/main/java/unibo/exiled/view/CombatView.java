package unibo.exiled.view;

import unibo.exiled.controller.GameController;
import unibo.exiled.model.utilities.Position;
import unibo.exiled.view.character.CharacterView;
import unibo.exiled.view.items.GameButton;

import javax.swing.JPanel;
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
        this.battlePanel = new JPanel(new GridLayout(1, 3));
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
        final JLabel playerLabel = new CharacterView(
                this.gameController.getCharacterController().getImagePathOfCharacter("player", "boy"));
        battlePanel.add(playerLabel);
    }

    /**
     * Sets the enemy character in the combat view.
     */
    public void setEnemy() {
        final Position combatPosition = this.gameController.getCharacterController().getPlayerPosition();
        final List<String> enemyImagePath = this.gameController.getCharacterController().getImagePathOfCharacter(
                "enemy",
                this.gameController.getMapController().getNameOfCharacterInPosition(combatPosition)
                        + File.separator
                        + this.gameController.getMapController().getNameOfCharacterInPosition(combatPosition));
        final JLabel enemyLabel = new CharacterView(enemyImagePath);
        this.battlePanel.add(enemyLabel);
    }
}
