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
    private final JButton restartButton = new JButton("Restart");
    private final JButton quitButton = new JButton("Quit");

    public GameOverView(){
        this.controller = new Controller(SIZE);
        this.mainFrame = new JFrame();
        mainFrame.setSize((int)SCREEN_WIDTH / 3,(int)SCREEN_HEIGHT / 2);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setTitle("The Exiled");
        mainFrame.setLocationByPlatform(true);
        this.initializeComponents();
        this.initializeHud();
    }

    private void initializeHud(){
        JPanel gameOverPanel = new JPanel(new BorderLayout());

        JLabel gameOverLabel = new JLabel("Game Over!", JLabel.CENTER);
        gameOverLabel.setFont(new Font("Arial", Font.PLAIN, 24)); 
        gameOverPanel.add(gameOverLabel, BorderLayout.CENTER);

        JPanel flowPanel = new JPanel(new FlowLayout());
        gameOverPanel.add(flowPanel, BorderLayout.SOUTH);

        flowPanel.add(restartButton);
        flowPanel.add(quitButton);

        this.mainFrame.getContentPane().add(gameOverPanel);
    }

    private void initializeComponents(){
        final JPanel externalPanel = new JPanel(new BorderLayout());
        this.mainFrame.setContentPane(externalPanel);

        //Listener initialization
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] options = { "Quit", "Cancel" };
                var selection = JOptionPane.showOptionDialog(null, "Are you sure to quit the game?", "ATTENTION", 
                                                                0, 3, null, options, options[0]);
                if (selection == 0) {
                    mainFrame.dispose();
                }
            }
        });

    }

    public void display() {
        this.mainFrame.setVisible(true);
    }
}
