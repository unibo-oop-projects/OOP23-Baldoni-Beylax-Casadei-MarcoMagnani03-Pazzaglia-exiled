package unibo.exiled.view.menu;

import unibo.exiled.view.GameView;
import unibo.exiled.view.NewGameView;
import unibo.exiled.view.PlayerClassView;
import unibo.exiled.view.items.GameButton;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.Serial;
import java.util.Optional;

/**
 * The view of the in-game menu.
 */
public final class MenuView extends JPanel {
    @Serial
    private static final long serialVersionUID = 4L;

    /**
     * The constructor of the in-game view.
     *
     * @param playerClassView The player class view of the game.
     * @param newGameView     The view of the new game.
     * @param gameView        The view of the game.
     */
    public MenuView(final Optional<PlayerClassView> playerClassView, final Optional<NewGameView> newGameView,
            final Optional<GameView> gameView) {
        super();
        if (newGameView.isPresent()) {
            newGameView.get().hide();
        }
        // CREATING STANDARD UI
        final JPanel buttonListPanel = new JPanel(new GridBagLayout());
        final GridBagConstraints cnst = new GridBagConstraints();
        cnst.gridy = 0;
        cnst.insets = new Insets(3, 3, 3, 3);
        cnst.fill = GridBagConstraints.HORIZONTAL;
        final JLabel logoLabel = new JLabel(new ImageIcon("src"
                + File.separator
                + "main" + File.separator
                + "java" + File.separator
                + "unibo" + File.separator
                + "exiled" + File.separator
                + "resources" + File.separator
                + "logo.png"));
        buttonListPanel.add(logoLabel);
        cnst.gridy++;
        final ActionListener buttonListener = new MenuItemActionListener(playerClassView, newGameView, gameView);

        if (gameView.isPresent()) { // Menu In-Game
            final GameButton resumeGameButton = new GameButton("RESUME");
            resumeGameButton.setActionCommand("close_menu");
            resumeGameButton.addActionListener(buttonListener);
            buttonListPanel.add(resumeGameButton, cnst);
            cnst.gridy++;
        }

        final GameButton newGameButton = new GameButton("NEW GAME");
        newGameButton.setActionCommand("new_game");
        newGameButton.addActionListener(buttonListener);
        buttonListPanel.add(newGameButton, cnst);
        cnst.gridy++;

        final GameButton quitGameButton = new GameButton("QUIT");
        quitGameButton.setActionCommand("quit");
        quitGameButton.addActionListener(buttonListener);
        buttonListPanel.add(quitGameButton, cnst);
        cnst.gridy++;

        this.setLayout(new BorderLayout());
        this.add(buttonListPanel, BorderLayout.CENTER);
    }

}
