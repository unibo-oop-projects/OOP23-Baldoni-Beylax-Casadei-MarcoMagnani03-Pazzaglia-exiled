package unibo.exiled.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;

import unibo.exiled.controller.CombatController;
import unibo.exiled.model.GameModel;
import unibo.exiled.model.character.enemy.Enemy;
import unibo.exiled.model.character.player.Player;
import unibo.exiled.model.move.MagicMove;
import unibo.exiled.view.items.GameButton;

public class CombatView extends JPanel {
    private CombatController combatController;
    private JPanel moveSetPanel;
    private JPanel battlePanel;
    private Player player;
    private Enemy enemy;

    public CombatView(GameModel model, GameView game) {
        this.player = player;
        
        this.setLayout(new BorderLayout());

        this.moveSetPanel = new JPanel(new GridLayout());
        this.battlePanel = new JPanel(new GridLayout());
        this.add(this.moveSetPanel, BorderLayout.SOUTH);
        this.add(this.battlePanel, BorderLayout.CENTER);


        for (MagicMove move : this.player.getMoveSet().getMagicMoves()) {
            JButton moveButton = new GameButton(move.name());

            this.moveSetPanel.add(moveButton);

            // TODO: Add event listener
        }


        JButton escapeButton = new GameButton("ESCAPE");
        escapeButton.addActionListener(e -> game.hideCombat());

        this.moveSetPanel.add(escapeButton);
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;

        this.combatController = new CombatController(this.player, this.enemy);

        JLabel playerLabel = new JLabel(new ImageIcon(this.player.getImagePath()));
        JLabel enemyLabel = new JLabel(new ImageIcon(this.enemy.getImagePath()));
        this.battlePanel.add(playerLabel);
        this.battlePanel.add(enemyLabel);

        this.revalidate();
        this.repaint();
    }
}
