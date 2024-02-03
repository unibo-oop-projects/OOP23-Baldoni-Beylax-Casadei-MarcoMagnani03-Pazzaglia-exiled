package unibo.exiled.view.menu;

import unibo.exiled.model.menu.Command;
import unibo.exiled.view.GameView;
import unibo.exiled.view.NewGameView;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * The action listener class for the menu. To retrieve input.
 */
public final class MenuItemActionListener implements ActionListener {
    private final GameView game;
    private final NewGameView newGameView;

    /**
     * The constructor of the in-game menu action listener.
     *
     * @param game        The main game view.
     * @param newGameView The new-game view.
     */
    public MenuItemActionListener(final GameView game, final NewGameView newGameView) {
        super();
        this.game = game;
        this.newGameView = newGameView;
    }

    /**
     * Processes the input event.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(final ActionEvent e) {
        if (e.getActionCommand().equals("new_game")) {
            new GameView().display();
            this.newGameView.hide();
        } else if (e.getActionCommand().equals(Command.CLOSE_MENU.getCommandString())) {
            this.game.hideMenu();
        } else if (e.getActionCommand().equals("quit")) {
                        final int dialogResult = JOptionPane.showConfirmDialog(null,
                    "Would you like to quit the game?", "Warning",
                    JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                ((JPanel)e.getSource()).dispatchEvent(new WindowEvent((Window)e.getSource(), WindowEvent.WINDOW_CLOSING));;
            }
        } else {
            throw new IllegalArgumentException("Command is not valid");
        }
    }
}
