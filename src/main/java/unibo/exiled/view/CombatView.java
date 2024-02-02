package unibo.exiled.view;

import unibo.exiled.controller.CombatController;
import unibo.exiled.controller.GameController;
import unibo.exiled.model.GameModel;
import unibo.exiled.model.character.enemy.Enemy;
import unibo.exiled.model.character.enemy.EnemyImpl;
import unibo.exiled.model.character.player.Player;
import unibo.exiled.model.move.MagicMove;
import unibo.exiled.view.items.GameButton;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class CombatView extends JPanel {
    private final CombatController combatController;
    private final JPanel moveSetPanel;
    private final JPanel battlePanel;

    public CombatView(final CombatController combatController, GameController gameController, GameView game) {
        this.combatController = combatController;

        this.setLayout(new BorderLayout());

        this.moveSetPanel = new JPanel(new GridLayout());
        this.battlePanel = new JPanel(new GridLayout());

        this.add(this.moveSetPanel, BorderLayout.SOUTH);
        this.add(this.battlePanel, BorderLayout.CENTER);

        for (MagicMove move : this.combatController.getPlayerMoveSet().getMagicMoves()) {
            JButton moveButton = new GameButton(move.name());
            this.moveSetPanel.add(moveButton);
            moveButton.addActionListener(e -> combatController.attack(true));
        }

        JButton escapeButton = new GameButton("ESCAPE");
        escapeButton.addActionListener(e -> game.hideCombat());
        this.moveSetPanel.add(escapeButton);

        JLabel player = new JLabel();
        player.imageUpdate(
                new ImageIcon(gameController.getImagePathOfCharacter(this.combatController.getPlayer()).get(0))
                        .getImage(),
                ERROR, ALLBITS, ABORT, 20, 20);
        this.battlePanel.add(player);
    }
    
    public void setEnemy(Optional<Enemy> enemy) {
        this.combatController.setEnemy(enemy.get());
    }
}
