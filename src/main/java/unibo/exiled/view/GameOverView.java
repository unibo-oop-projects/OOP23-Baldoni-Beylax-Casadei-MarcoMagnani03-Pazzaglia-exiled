package unibo.exiled.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

import unibo.exiled.view.items.GameButton;


/**
 * This class represent the Game Over view.
 */
public class GameOverView {
    //Screen constants
    private final Dimension SCREEN = Toolkit.getDefaultToolkit().getScreenSize();
    private final double SCREEN_WIDTH = SCREEN.getWidth();
    private final double SCREEN_HEIGHT = SCREEN.getHeight();

    //MVC Components (MC)
    private final JFrame mainFrame;

    //The buttons to restart or exit the game.
    private final GameButton restartButton = new GameButton("Restart");
    private final GameButton quitButton = new GameButton("Quit");

    public GameOverView(){
        this.mainFrame = new JFrame();
        this.mainFrame.setSize((int)SCREEN_WIDTH / 3, (int)SCREEN_HEIGHT / 2);
        this.mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.mainFrame.setTitle("The Exiled - Game Over");
        this.mainFrame.setLocationByPlatform(true);

        this.initializeHud();
        this.initializeListeners();
    }

    private void initializeHud(){
        JPanel gameOverPanel = new JPanel(new BorderLayout());
        JLabel gameOverLabel = new JLabel(new ImageIcon("src" + File.separator +
            "main" + File.separator +
            "java" + File.separator +
            "unibo" + File.separator +
            "exiled" + File.separator +
            "resources" + File.separator +
            "gameover.png"));
        gameOverPanel.add(gameOverLabel, BorderLayout.CENTER);

        JPanel flowPanel = new JPanel(new FlowLayout());
        gameOverPanel.add(flowPanel, BorderLayout.SOUTH);

        flowPanel.add(restartButton);
        flowPanel.add(quitButton);

        this.mainFrame.getContentPane().add(gameOverPanel);
    }

    private void initializeListeners(){
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dialogResult = JOptionPane.showConfirmDialog (null, "Would you like to restart the game?", "Warning", JOptionPane.YES_NO_OPTION);
                if(dialogResult == JOptionPane.YES_OPTION){
                    mainFrame.dispose();
                    new NewGameView(); // Restarting the game
                }
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dialogResult = JOptionPane.showConfirmDialog (null, "Would you like to quit the game?", "Warning", JOptionPane.YES_NO_OPTION);
                if(dialogResult == JOptionPane.YES_OPTION){
                    mainFrame.dispatchEvent(new WindowEvent(mainFrame, WindowEvent.WINDOW_CLOSING)); // Close the program
                }
            }
        });
    }

    public void display() {
        this.mainFrame.setVisible(true);
    }
}
