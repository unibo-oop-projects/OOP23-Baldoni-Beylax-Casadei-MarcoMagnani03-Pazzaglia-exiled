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
    private final CombatController combatController;
    private final JPanel moveSetPanel;
    private final JPanel battlePanel;
    private final int iconSize = 20;

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

        this.moveSetPanel = new JPanel(new GridLayout());
        this.battlePanel = new JPanel(new GridLayout());

        this.add(this.moveSetPanel, BorderLayout.SOUTH);
        this.add(this.battlePanel, BorderLayout.CENTER);

        for (final MagicMove move : this.combatController.getPlayerMoveSet().getMagicMoves()) {
            JButton moveButton = new GameButton(move.name());
            this.moveSetPanel.add(moveButton);
            moveButton.addActionListener(e -> combatController.attack(true));
        }

        JButton escapeButton = new GameButton("ESCAPE");
        escapeButton.addActionListener(e -> game.hideCombat());
        this.moveSetPanel.add(escapeButton);

        final JLabel player = new JLabel();
        player.imageUpdate(
                new ImageIcon(gameController.getImagePathOfCharacter(this.combatController.getPlayer()).get(0))
                        .getImage(),
                ERROR, ALLBITS, ABORT, iconSize, iconSize);
        this.battlePanel.add(player);
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
