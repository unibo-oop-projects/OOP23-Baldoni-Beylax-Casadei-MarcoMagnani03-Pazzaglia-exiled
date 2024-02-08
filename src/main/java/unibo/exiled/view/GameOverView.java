package unibo.exiled.view;

import unibo.exiled.view.items.GameButton;

import java.io.File;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

/**
 * This class represents the GameOver view.
 */
public final class GameOverView {

    // MVC Components.
    private final JFrame mainFrame;

    // Buttons to restart or exit the game.
    private final GameButton restartButton = new GameButton("Restart");
    private final GameButton quitButton = new GameButton("Quit");

    // Path to the game over image file.
    private static final String GAME_OVER_IMAGE_PATH = "src"
            + File.separator
            + "main"
            + File.separator
            + "java"
            + File.separator
            + "unibo"
            + File.separator
            + "exiled"
            + File.separator
            + "resources"
            + File.separator
            + "gameover.png";

    /**
     * Constructs a GameOver view.
     */
    public GameOverView() {
        this.mainFrame = new JFrame();
        this.mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.mainFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.mainFrame.setTitle("The Exiled");
        this.mainFrame.setLocationByPlatform(true);
        this.mainFrame.setFocusable(true);
        this.mainFrame.setLayout(new BorderLayout());

        initializeUI();
        initializeListeners();
    }

    /**
     * Initializes the user interface of the GameOver view.
     */
    private void initializeUI() {
        final JPanel gameOverPanel = new JPanel(new BorderLayout());
        final JLabel gameOverLabel = new JLabel(new ImageIcon(GAME_OVER_IMAGE_PATH));
        gameOverPanel.add(gameOverLabel, BorderLayout.CENTER);

        final JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(this.restartButton);
        buttonPanel.add(this.quitButton);
        gameOverPanel.add(buttonPanel, BorderLayout.SOUTH);

        this.mainFrame.getContentPane().add(gameOverPanel);
    }

    /**
     * Initializes action listeners for the restart and quit buttons.
     */
    private void initializeListeners() {
        this.restartButton.addActionListener(e -> {
            final int dialogResult = JOptionPane.showConfirmDialog(null,
                    "Would you like to restart the game?", "Warning", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                this.mainFrame.dispose();
                new NewGameView(); // Restart the game
            }
        });

        this.quitButton.addActionListener(e -> {
            final int dialogResult = JOptionPane.showConfirmDialog(null,
                    "Would you like to quit the game?", "Warning", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                this.mainFrame.dispatchEvent(new WindowEvent(this.mainFrame, WindowEvent.WINDOW_CLOSING)); // Close the
                                                                                                           // game
            }
        });
    }

    /**
     * Displays the GameOver view.
     */
    public void display() {
        this.mainFrame.setVisible(true);
    }
}
