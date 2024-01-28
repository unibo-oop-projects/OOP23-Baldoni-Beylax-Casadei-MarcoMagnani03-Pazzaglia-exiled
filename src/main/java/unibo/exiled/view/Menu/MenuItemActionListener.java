package unibo.exiled.view.Menu;

import unibo.exiled.model.menu.Command;
import unibo.exiled.view.GameView;
import unibo.exiled.view.NewGameView;

import java.awt.event.*;

public class MenuItemActionListener implements ActionListener {
    GameView game;
    NewGameView newGameView;

    public MenuItemActionListener(GameView game, NewGameView newGameView) {
        super();
        this.game = game;
        this.newGameView = newGameView;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(Command.NEW_GAME.getCommandString())) {
            new GameView().display();
            this.newGameView.hide();
            // new PlayerClassView().display();
        } else if (e.getActionCommand().equals(Command.CLOSE_MENU.getCommandString())) {
            this.game.hideMenu();
        } else if (e.getActionCommand().equals(Command.QUIT.getCommandString())) {
            System.exit(0);
        } else {
            throw new IllegalArgumentException("Command is not valid");
        }
    }
}
