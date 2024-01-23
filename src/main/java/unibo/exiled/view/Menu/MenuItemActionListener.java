package unibo.exiled.view.Menu;

import unibo.exiled.model.menu.Command;
import unibo.exiled.view.GameView;

import java.awt.event.*;

public class MenuItemActionListener implements ActionListener {
    GameView game;

    public MenuItemActionListener(GameView game) {
        super();
        this.game = game;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(Command.NEW_GAME.getCommandString())) {
            //game.hideMenu();
        } else if (e.getActionCommand().equals(Command.CLOSE_MENU.getCommandString())) {
            //game.hideMenu();
        } else if (e.getActionCommand().equals(Command.QUIT.getCommandString())) {
            System.exit(0);
        } else {
            throw new IllegalArgumentException("Command is not valid");
        }
    }
}
