package unibo.exiled.view;

import unibo.exiled.view.items.GameButton;
import unibo.exiled.view.items.GameLabel;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import java.io.File;


/**
 * This class represent the GameCompleted view.
 */
public final class GameCompletedView {
    //Screen constants
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final double screenWidth = screenSize.getWidth();
    private final double screenHeight = screenSize.getHeight();

    //MVC Components (MC)
    private final JFrame mainFrame;

    //The buttons to restart or exit the game.
    private final GameButton restartButton = new GameButton("New Game");
    private final GameButton quitButton = new GameButton("Quit");

    /**
     * The constructor of the GameCompleted view.
     */
    public GameCompletedView() {
        this.mainFrame = new JFrame();
        this.mainFrame.setSize((int) screenWidth / 3, (int) screenHeight / 2);
        this.mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.mainFrame.setTitle("The Exiled");
        this.mainFrame.setLocationByPlatform(true);

        this.initializeHud();
        this.initializeListeners();
    }

    private void initializeHud() {
        final JPanel gameCompletedPanel = new JPanel(new BorderLayout());
        final JLabel gameCompletedLabel = new JLabel(new ImageIcon("src"
                + File.separator
                + "main"
                + File.separator
                + "java"
                + File.separator + "unibo" + File.separator + "exiled" + File.separator
                + "resources" + File.separator + "gamecompleted.png"));
        gameCompletedPanel.add(gameCompletedLabel, BorderLayout.CENTER);

        final JPanel flowPanel = new JPanel(new FlowLayout());
        gameCompletedPanel.add(flowPanel, BorderLayout.SOUTH);
        final JLabel completedLabel = new GameLabel("Congratulations, you have achieved redemption!");
        flowPanel.add(completedLabel);
        flowPanel.add(restartButton);
        flowPanel.add(quitButton);

        this.mainFrame.getContentPane().add(gameCompletedPanel);
    }

    private void initializeListeners() {
        restartButton.addActionListener(e -> {
            final int dialogResult = JOptionPane.showConfirmDialog(null,
                    "Would you like to start a new game?", "Warning",
                    JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                mainFrame.dispose();
                new NewGameView(); // Starting new game
            }
        });

        quitButton.addActionListener(e -> {
            final int dialogResult = JOptionPane.showConfirmDialog(null,
                    "Would you like to quit the game?", "Warning",
                    JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                mainFrame.dispatchEvent(new WindowEvent(mainFrame, WindowEvent.WINDOW_CLOSING));
            }
        });
    }

    /**
     * This method makes the GameCompleted view visible.
     */
    public void display() {
        this.mainFrame.setVisible(true);
    }
}
