package unibo.exiled.view;

import unibo.exiled.utilities.ConstantsAndResourceLoader;
import unibo.exiled.view.items.GameButton;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

/**
 * This class represent the GameCompleted view.
 */
public final class GameVictoryView {

    //MVC Components (MC)
    private final JFrame mainFrame;

    //The buttons to restart or exit the game.
    private final GameButton restartButton = new GameButton("New Game");
    private final GameButton quitButton = new GameButton("Quit");

    /**
     * The constructor of the GameCompleted view.
     */
    public GameVictoryView() {
        this.mainFrame = new JFrame();
        this.mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.mainFrame.setTitle("The Exiled");
        this.mainFrame.setLocationByPlatform(true);
        this.mainFrame.setFocusable(true);
        this.mainFrame.setLayout(new BorderLayout());

        this.initializeHud();
        this.initializeListeners();
    }

    private void initializeHud() {
        final JPanel gameCompletedPanel = new JPanel(new BorderLayout());
        final JLabel gameCompletedLabel = new JLabel(new ImageIcon(ConstantsAndResourceLoader
                .getResourceURLFromPath(ConstantsAndResourceLoader.IMAGES_PATH + "/interface/gamecompleted.png")));
        gameCompletedPanel.add(gameCompletedLabel, BorderLayout.NORTH);

        final JPanel buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.add(restartButton);
        buttonsPanel.add(quitButton);
        gameCompletedPanel.add(buttonsPanel, BorderLayout.CENTER);

        this.mainFrame.getContentPane().add(gameCompletedPanel);
    }

    private void initializeListeners() {
        restartButton.addActionListener(e -> {
            final int dialogResult = JOptionPane.showConfirmDialog(null,
                    "Would you like to start a new game?", "Warning",
                    JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                new NewGameView(); // Starting new game
                mainFrame.dispose();
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
