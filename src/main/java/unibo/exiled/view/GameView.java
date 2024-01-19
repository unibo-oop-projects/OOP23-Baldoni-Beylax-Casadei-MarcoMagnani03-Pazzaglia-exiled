package unibo.exiled.view;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class GameView extends JFrame {
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final double widthScreen = screenSize.getWidth();
    private final double heightScreen = screenSize.getHeight();

    public GameView(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize((int)(widthScreen/2), (int)(heightScreen/1.5));

        this.setVisible(true);
    }

}
