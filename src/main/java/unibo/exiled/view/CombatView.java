package unibo.exiled.view;

import unibo.exiled.controller.GameController;
import unibo.exiled.model.utilities.Position;
import unibo.exiled.view.character.CharacterView;
import unibo.exiled.view.items.GameButton;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.io.File;
import java.util.List;
import java.awt.BorderLayout;

/**
 * The view that starts when the player engages in a combat with an enemy.
 */
public final class CombatView extends JPanel {
    private static final long serialVersionUID = 1L;

    /**
     * The constructor of the combat view.
     *
     * @param gameController   The controller that manages the whole game.
     * @param game             The view of the game (Main view).
     */
    public CombatView(final GameController gameController, final GameView game) {
        this.setLayout(new BorderLayout());

        final JPanel battlePanel = new JPanel(new GridLayout(2, 1));
        final JPanel moveSetPanel = new JPanel(new GridLayout(5, 2, 10, 10));

        this.add(battlePanel, BorderLayout.CENTER);
        this.add(moveSetPanel, BorderLayout.SOUTH);

        for (final String moveName : gameController.getMagicMoveNames()) {
            final JButton moveButton = new GameButton(moveName);
            moveSetPanel.add(moveButton);
            moveButton.addActionListener(e -> gameController.attack(true));
        }

        final Position combatPosition = gameController.getPlayerPosition();

        final List<String> characterImagePath = gameController.getImagePathOfCharacter(
                "enemy",
            gameController.getNameOfCharacterInPosition(combatPosition)
                        + File.separator + gameController.getNameOfCharacterInPosition(combatPosition));
        final JLabel enemyLabel = new CharacterView(characterImagePath);
        final JLabel playerLabel = new CharacterView(gameController.getImagePathOfCharacter("player", "boy"));
        battlePanel.add(playerLabel);
        battlePanel.add(enemyLabel);
    }
}
