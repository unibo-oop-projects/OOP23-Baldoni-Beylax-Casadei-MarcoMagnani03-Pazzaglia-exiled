package unibo.exiled.view;

import unibo.exiled.controller.PlayerController;
import unibo.exiled.model.utilities.ElementalType;


import javax.swing.*;
import java.awt.*;

public class PlayerClassView extends JPanel{

    private final PlayerController controller;

    public PlayerClassView(PlayerController controller) {
        this.controller = controller;
        JFrame frame = new JFrame("Select Player Class");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new GridLayout(4, 1));

        JButton fireButton = new JButton(ElementalType.FIRE.getName());
        JButton waterButton = new JButton(ElementalType.WATER.getName());
        JButton boltButton = new JButton(ElementalType.BOLT.getName());
        JButton grassButton = new JButton(ElementalType.GRASS.getName());

        fireButton.addActionListener(e -> classDecision(ElementalType.FIRE));
        waterButton.addActionListener(e -> classDecision(ElementalType.WATER));
        boltButton.addActionListener(e -> classDecision(ElementalType.BOLT));
        grassButton.addActionListener(e -> classDecision(ElementalType.GRASS));

        frame.add(fireButton);
        frame.add(waterButton);
        frame.add(boltButton);
        frame.add(grassButton);

        frame.setVisible(true);
    }


    public void display() {
        setVisible(true);
    }

    private void classDecision(ElementalType playerClass){
        int result = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to choose " + playerClass + " class?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            this.controller.setPlayerClass(playerClass);
        }
    }
}
