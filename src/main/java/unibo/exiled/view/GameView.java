package unibo.exiled.view;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class GameView {
    private final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    private final double SCREEN_WIDTH = SCREEN_SIZE.getWidth();
    private final double HEIGHT_SCREEN = SCREEN_SIZE.getHeight();

    public void startView() {
        JFrame frame = new JFrame();
        
        frame.setSize((int)(SCREEN_WIDTH/2), (int)(HEIGHT_SCREEN));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("The Exiled");
        
        frame.setVisible(true);
    }

}
