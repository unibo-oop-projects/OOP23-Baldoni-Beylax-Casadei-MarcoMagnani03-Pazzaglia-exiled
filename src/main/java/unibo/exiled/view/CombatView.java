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
import java.awt.Dimension;
import java.awt.FlowLayout;

/**
 * The view that starts when the player engages in a combat with an enemy.
 */
public final class CombatView extends JPanel {
    private static final long serialVersionUID = 1L;

    private final GameController gameController;

    private final JPanel battlePanel;
    private final JPanel moveSetPanel;

    /**
     * The constructor of the combat view.
     *
     * @param gameController   The controller that manages the whole game.
     * @param game             The view of the game (Main view).
     */
    public CombatView(final GameController gameController, final GameView game) {
        this.gameController = gameController;
        this.setLayout(new BorderLayout());

        this.moveSetPanel = new JPanel(new FlowLayout(1, 10, 10));
        this.battlePanel = new JPanel(new GridLayout(1, 3));
        this.battlePanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));

        this.add(this.battlePanel, BorderLayout.CENTER);
        this.add(this.moveSetPanel, BorderLayout.SOUTH);

        for (final String moveName : this.gameController.getPlayerMoveSet()) {
            final JButton moveButton = new GameButton(moveName);
            moveSetPanel.add(moveButton);
            moveButton.addActionListener(e -> this.gameController.attack(true));
        }

        final JLabel playerLabel = new CharacterView(this.gameController.getImagePathOfCharacter("player", "boy"));
        battlePanel.add(playerLabel);
    }
    
    public void setEnemy() {
        final Position combatPosition = this.gameController.getPlayerPosition();
        final List<String> enemyImagePath = this.gameController.getImagePathOfCharacter(
                "enemy",
                this.gameController.getNameOfCharacterInPosition(combatPosition)
                        + File.separator + this.gameController.getNameOfCharacterInPosition(combatPosition));
        final JLabel enemyLabel = new CharacterView(enemyImagePath);
        this.battlePanel.add(enemyLabel);
    }
}
