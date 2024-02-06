package unibo.exiled.view.menu;

import unibo.exiled.view.GameView;
import unibo.exiled.view.NewGameView;
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

/**
 * The view of the in-game menu.
 */
public final class MenuView extends JPanel {
    private static final long serialVersionUID = 4L;

    /**
     * The constructor of the in-game view.
     *
     * @param game        The main view of the game.
     * @param newGameView The view of the new game.
     */
    public MenuView(final GameView game, final NewGameView newGameView) {
        super();
        newGameView.hide();
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
        final ActionListener buttonListener = new MenuItemActionListener(game, newGameView);

        final GameButton newGameButton = new GameButton("NEW GAME");
        newGameButton.setActionCommand("new_game");
        newGameButton.addActionListener(buttonListener);

        final GameButton quitGameButton = new GameButton("QUIT");
        quitGameButton.setActionCommand("quit");
        quitGameButton.addActionListener(buttonListener);

        buttonListPanel.add(newGameButton, cnst);
        cnst.gridy++;
        buttonListPanel.add(quitGameButton, cnst);
        cnst.gridy++;


        /*for (final MenuItem menuItem : controller.getMenuItems()) { 
            final GameButton btn = new GameButton(menuItem.getItemText());
            btn.setActionCommand(menuItem.getItemCommand().getCommandString());
            btn.addActionListener(buttonListener);
            buttonListPanel.add(btn, cnst);
            cnst.gridy++;
        }*/



        this.setLayout(new BorderLayout());
        this.add(buttonListPanel, BorderLayout.CENTER);
    }
}
