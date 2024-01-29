package unibo.exiled.view;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;

import javax.swing.*;

import unibo.exiled.controller.CombatController;
import unibo.exiled.model.character.enemy.Enemy;
import unibo.exiled.model.character.player.Player;
import unibo.exiled.model.move.MagicMove;

public class CombatView extends JPanel {
    private CombatController combatController;
    private JPanel moveSetPanel;
    private Player player;
    private Enemy enemy;

    public CombatView(Player player, GameView game) {
        this.player = player;
        
        this.setLayout(new BorderLayout());

        this.moveSetPanel = new JPanel(new GridBagLayout());
        this.add(moveSetPanel, BorderLayout.SOUTH);


        for (MagicMove move : this.player.getMoveSet().getMagicMoves()) {
            JButton moveButton = new JButton(move.getName());

            this.moveSetPanel.add(moveButton);

            // TODO: Add event listener
        }


        JButton escapeButton = new JButton("ESCAPE");
        escapeButton.addActionListener(e -> game.hideCombat());

        this.moveSetPanel.add(escapeButton);
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;

        this.combatController = new CombatController(this.player, this.enemy);
    }
}
