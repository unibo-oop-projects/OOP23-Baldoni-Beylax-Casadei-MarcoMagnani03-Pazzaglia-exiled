package unibo.exiled.view.menu;

import unibo.exiled.model.menu.Command;
import unibo.exiled.view.GameView;
import unibo.exiled.view.NewGameView;
import unibo.exiled.view.PlayerClassView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

import javax.swing.JOptionPane;

/**
 * The action listener class for the menu. To retrieve input.
 */
public final class MenuItemActionListener implements ActionListener {
    private Optional<PlayerClassView> playerClassView;
    private final Optional<NewGameView> newGameView;
    private final Optional<GameView> gameView;

    /**
     * The constructor of the in-game menu action listener.
     *
     * @param playerClassView The player class view (optional).
     * @param newGameView     The new-game view (optional).
     * @param gameView        The game view (optional).
     */
    public MenuItemActionListener(final Optional<PlayerClassView> playerClassView,
            final Optional<NewGameView> newGameView, final Optional<GameView> gameView) {
        super();
        this.playerClassView = playerClassView;
        this.newGameView = newGameView;
        this.gameView = gameView;
    }

    /**
     * Processes the input event.
     *
     * @param e The event to be processed.
     */
    @Override
    public void actionPerformed(final ActionEvent e) {
        if (e.getActionCommand().equals(Command.NEW_GAME.getCommandString())) {
            this.playerClassView = Optional.of(new PlayerClassView());
            this.playerClassView.get().display();
            this.newGameView.ifPresent(NewGameView::hide);
        } else if (e.getActionCommand().equals(Command.CLOSE_MENU.getCommandString())) {
            this.gameView.ifPresent(GameView::hideMenu);
        } else if (e.getActionCommand().equals(Command.QUIT.getCommandString())) {
            final int dialogResult = JOptionPane.showConfirmDialog(null,
                    "Would you like to quit the game?", "Warning",
                    JOptionPane.YES_NO_OPTION);

            if (dialogResult == JOptionPane.YES_OPTION) {
                this.playerClassView.ifPresent(PlayerClassView::close);
                this.newGameView.ifPresent(NewGameView::close);
            }
        } else {
            throw new IllegalArgumentException("Command is not valid");
        }
    }
}
