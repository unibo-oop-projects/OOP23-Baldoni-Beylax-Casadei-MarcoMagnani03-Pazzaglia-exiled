package unibo.exiled.view;

import javax.swing.*;

import unibo.exiled.model.move.MoveSet;

import java.awt.*;

public class MoveSetView extends JPanel {
    private final static int ROW_MOVESET = 2;
    private final static int COLUMNS_MOVESET = 2;
    private final static int MOVE_BUTTON_WIDTH = 200;
    private final static int MOVE_BUTTON_HEIGHT = 50;

    private final MoveSet moveSet;

    public MoveSetView(MoveSet moveSet){
        this.moveSet = moveSet;
        setLayout(new GridLayout(ROW_MOVESET, COLUMNS_MOVESET, 10, 10));
        for (var move : moveSet.getMagicMoves()) {
           add(createMoveJButton(move.getName())); 
        }  
    }   

    private JButton createMoveJButton(String moveName){
        JButton button = new JButton(moveName);
        button.setPreferredSize(new Dimension(MOVE_BUTTON_WIDTH, MOVE_BUTTON_HEIGHT));
        return button;
    }

}
