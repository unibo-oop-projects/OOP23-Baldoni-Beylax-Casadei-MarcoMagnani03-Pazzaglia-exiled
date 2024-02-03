package unibo.exiled.view.menu;

import unibo.exiled.model.menu.Command;
import unibo.exiled.view.GameView;
import unibo.exiled.view.NewGameView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        if (e.getActionCommand().equals(Command.NEW_GAME.getCommandString())) {
            new GameView().display();
            this.newGameView.hide();
        } else if (e.getActionCommand().equals(Command.CLOSE_MENU.getCommandString())) {
            this.game.hideMenu();
        } else if (e.getActionCommand().equals(Command.QUIT.getCommandString())) {
            System.exit(0);
        } else {
            throw new IllegalArgumentException("Command is not valid");
        }
    }
}
