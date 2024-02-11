package unibo.exiled.view;

import unibo.exiled.controller.GameController;
import unibo.exiled.view.items.GameButton;
import unibo.exiled.view.items.GameLabel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import java.awt.BorderLayout;
import javax.swing.WindowConstants;

/**
 * This class represents the GameMoveChangeView view.
 */
public final class GameMoveChangeView {
    // MVC Components.
    private final JFrame mainFrame;
    private final GameController gameController;

    /**
     * Constructs a GameMoveChangeView view.
     */
    public GameMoveChangeView(final GameController gameController) {
        this.mainFrame = new JFrame();
        this.mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.mainFrame.setTitle("The Exiled");
        this.mainFrame.setLocationByPlatform(true);
        this.mainFrame.setFocusable(true);
        this.mainFrame.setLayout(new BorderLayout());

        this.gameController = gameController;

        initializeUI();
    }

    /**
     * Initializes the user interface of the GameMoveChangeView view.
     */
    private void initializeUI() {
        String moveToLearn = gameController.getCharacterController().getNewMove();

        final JPanel gameOverPanel = new JPanel(new BorderLayout());
        final JLabel gameOverLabel = new GameLabel("Change a move from your MoveSet or refuse to learn the" + moveToLearn + "move.");
        gameOverPanel.add(gameOverLabel, BorderLayout.CENTER);

        final JPanel buttonPanel = new JPanel(new FlowLayout());
        final List<GameButton> movesButtonList = new ArrayList<>();

        for (String move : gameController.getCharacterController().getPlayerMoveSet()) {
            final GameButton moveButton = new GameButton(move);

            moveButton.addActionListener(e -> {
                final String moveSelected = moveButton.getText();
                final int dialogResult = JOptionPane.showConfirmDialog(null,
                        "Would you like to change \'"+ moveSelected +"\' into " + moveToLearn + "?", "Warning",
                        JOptionPane.YES_NO_OPTION);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    this.gameController.getCharacterController().changeMove(moveSelected, moveToLearn);
                }
            });

            movesButtonList.add(moveButton);
            buttonPanel.add(moveButton);
        }

        final GameButton refuseButton = new GameButton("Abort");
        buttonPanel.add(refuseButton);
        refuseButton.addActionListener(e -> {
            final int dialogResult = JOptionPane.showConfirmDialog(null,
                "Are you sure that you don't want to learn: " + moveToLearn, "Warning",
                JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                this.mainFrame.dispose();
            }
        });

        gameOverPanel.add(buttonPanel, BorderLayout.SOUTH);

        this.mainFrame.getContentPane().add(gameOverPanel);
    }

    /**
     * Displays the GameMoveChangeView view.
     */
    public void display() {
        this.mainFrame.setVisible(true);
    }
}
