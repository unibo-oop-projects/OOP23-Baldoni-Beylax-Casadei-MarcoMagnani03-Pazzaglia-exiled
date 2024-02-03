package unibo.exiled.view;

import unibo.exiled.config.Constants;
import unibo.exiled.controller.GameController;
import unibo.exiled.view.items.GameButton;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import java.awt.BorderLayout;

/**
 * The view that starts when the player engages in a combat with an enemy.
 */
public final class CombatView extends JPanel {
    private static final long serialVersionUID = 1L;
    private final GameController gameController;
    private static final int ICON_SIZE = 20;

    /**
     * The constructor of the combat view.
     *
     * @param combatController The controller that manages the combat.
     * @param gameController   The controller that manages the whole game.
     * @param game             The view of the game (Main view).
     */
    public CombatView(final GameController gameController, final GameView game) {
        this.gameController = gameController;

        this.setLayout(new BorderLayout());

        final JPanel moveSetPanel = new JPanel(new GridLayout());
        final JPanel battlePanel = new JPanel(new GridLayout());

        this.add(moveSetPanel, BorderLayout.SOUTH);
        this.add(battlePanel, BorderLayout.CENTER);

        for (final String moveName : this.gameController.getMagicMoveNames()) {
            final JButton moveButton = new GameButton(moveName);
            moveSetPanel.add(moveButton);
            moveButton.addActionListener(e -> gameController.attack(true));
        }

        final JButton escapeButton = new GameButton("ESCAPE");
        escapeButton.addActionListener(e -> game.hideCombat());
        moveSetPanel.add(escapeButton);

        final JLabel player = new JLabel();
        player.imageUpdate(
                new ImageIcon(gameController.getImagePathOfCharacter(Constants.PLAYER_PATH, Constants.PLAYER_NAME).get(0))
                        .getImage(),
                ERROR, ALLBITS, ABORT, ICON_SIZE, ICON_SIZE);
        battlePanel.add(player);
    }
}
