package unibo.exiled.view;

import unibo.exiled.controller.Controller;
import unibo.exiled.model.utilities.Position;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class GameOverView {
    //Screen constants
    private static final int SIZE = 10;
    private final Dimension SCREEN = Toolkit.getDefaultToolkit().getScreenSize();
    private final double SCREEN_WIDTH = SCREEN.getWidth();
    private final double SCREEN_HEIGHT = SCREEN.getHeight();

    //MVC Components (MC)
    private final JFrame mainFrame;
    private final Controller controller;

    //The buttons to restart or exit the game.
    private final JButton buttonRestart = new JButton("Restart");
    private final JButton buttonExit = new JButton("Exit");

    public GameOverView(){
        this.controller = new Controller(SIZE);
        this.mainFrame = new JFrame();
        mainFrame.setSize((int)SCREEN_WIDTH / 3,(int)SCREEN_HEIGHT / 2);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setTitle("The Exiled - Game Over");
        mainFrame.setLocationByPlatform(true);
        this.initializeGridComponents();
        this.initializeHud();
    }

    private void initializeHud(){
        JPanel flowButtonPanel = new JPanel(new FlowLayout());
        this.mainFrame.getContentPane().add(flowButtonPanel);
        flowButtonPanel.add(buttonRestart);
        flowButtonPanel.add(buttonExit);
    }

    private void initializeGridComponents(){
        final JPanel externalPanel = new JPanel(new BorderLayout());
        this.mainFrame.setContentPane(externalPanel);
        final JPanel gridPanel = new JPanel(new GridLayout(controller.getMapWidth(),controller.getMapHeight()));

        //Listener initialization

        

        this.mainFrame.getContentPane().add(gridPanel,BorderLayout.CENTER);
    }

    public void display() {
        this.mainFrame.setVisible(true);
    }
}
