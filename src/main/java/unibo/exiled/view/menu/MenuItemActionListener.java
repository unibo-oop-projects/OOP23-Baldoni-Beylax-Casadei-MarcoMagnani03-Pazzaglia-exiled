package unibo.exiled.view.menu;

import unibo.exiled.model.menu.Command;
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

    /**
     * The constructor of the in-game menu action listener.
     *
     * @param game        The main game view.
     * @param newGameView The new-game view.
     */
    public MenuItemActionListener(final Optional<PlayerClassView> playerClassView,
            final Optional<NewGameView> newGameView) {
        super();
        this.playerClassView = playerClassView;
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
            this.playerClassView = Optional.of(new PlayerClassView());
            this.playerClassView.get().display();
            this.newGameView.get().hide();
        } else if (e.getActionCommand().equals(Command.CLOSE_MENU.getCommandString())) {
            // this.game.hideMenu();
        } else if (e.getActionCommand().equals(Command.QUIT.getCommandString())) {
            final int dialogResult = JOptionPane.showConfirmDialog(null,
                    "Would you like to quit the game?", "Warning",
                    JOptionPane.YES_NO_OPTION);

            if (dialogResult == JOptionPane.YES_OPTION) {
                if (this.playerClassView.isPresent()) {
                    this.playerClassView.get().close();
                } 
                if(this.newGameView.isPresent()){
                    this.newGameView.get().close();
                }
            }

        } else {
            throw new IllegalArgumentException("Command is not valid");
        }
    }
}
