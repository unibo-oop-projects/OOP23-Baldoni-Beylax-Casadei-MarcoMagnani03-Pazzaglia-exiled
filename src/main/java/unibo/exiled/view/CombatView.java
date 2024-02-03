package unibo.exiled.view;

import unibo.exiled.controller.CombatController;
import unibo.exiled.controller.GameController;
import unibo.exiled.model.character.enemy.Enemy;
import unibo.exiled.model.move.MagicMove;
import unibo.exiled.view.items.GameButton;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.util.Optional;

/**
 * The view that starts when the player engages in a combat with an enemy.
 */
public final class CombatView extends JPanel {
    private static final long serialVersionUID = 1L;
    private final CombatController combatController;
    private static final int ICON_SIZE = 20;

    /**
     * The constructor of the combat view.
     *
     * @param combatController The controller that manages the combat.
     * @param gameController   The controller that manages the whole game.
     * @param game             The view of the game (Main view).
     */
    public CombatView(final CombatController combatController, final GameController gameController, final GameView game) {
        this.combatController = combatController;

        this.setLayout(new BorderLayout());

        final JPanel moveSetPanel = new JPanel(new GridLayout());
        final JPanel battlePanel = new JPanel(new GridLayout());

        this.add(moveSetPanel, BorderLayout.SOUTH);
        this.add(battlePanel, BorderLayout.CENTER);

        for (final MagicMove move : this.combatController.getPlayerMoveSet().getMagicMoves()) {
            final JButton moveButton = new GameButton(move.name());
            moveSetPanel.add(moveButton);
            moveButton.addActionListener(e -> combatController.attack(true));
        }

        final JButton escapeButton = new GameButton("ESCAPE");
        escapeButton.addActionListener(e -> game.hideCombat());
        moveSetPanel.add(escapeButton);

        final JLabel player = new JLabel();
        player.imageUpdate(
                new ImageIcon(gameController.getImagePathOfCharacter(this.combatController.getPlayer()).get(0))
                        .getImage(),
                ERROR, ALLBITS, ABORT, ICON_SIZE, ICON_SIZE);
        battlePanel.add(player);
    }

    /**
     * Sets the enemy in the combat controller.
     *
     * @param enemy The enemy to set in the controller.
     */
    public void setEnemy(final Optional<Enemy> enemy) {
        this.combatController.setEnemy(enemy.get());
    }
}
