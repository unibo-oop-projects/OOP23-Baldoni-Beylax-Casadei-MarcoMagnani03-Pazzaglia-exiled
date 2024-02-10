package unibo.exiled.view;

import unibo.exiled.utilities.ConstantsAndResourceLoader;
import unibo.exiled.view.items.GameButton;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.net.URL;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

/**
 * This class represents the GameOver view.
 */
public final class GameOverView {

    // Path to the game over image file.
    private static final String GAME_OVER_IMAGE_PATH = ConstantsAndResourceLoader.IMAGES_PATH + "/interface/gameover.png";
    // MVC Components.
    private final JFrame mainFrame;
    // Buttons to restart or exit the game.
    private final GameButton restartButton = new GameButton("Restart");
    private final GameButton quitButton = new GameButton("Quit");

    /**
     * Constructs a GameOver view.
     */
    public GameOverView() {
        this.mainFrame = new JFrame();
        this.mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
        final URL imageURL = ClassLoader.getSystemResource(GAME_OVER_IMAGE_PATH);
        final JLabel gameOverLabel = new JLabel(new ImageIcon(imageURL));
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
