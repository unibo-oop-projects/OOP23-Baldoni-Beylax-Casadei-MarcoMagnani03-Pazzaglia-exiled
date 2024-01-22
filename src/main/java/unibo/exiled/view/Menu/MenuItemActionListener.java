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
            // Handle new game
            game.initializeGridComponents();
            game.initializeHud();
        }
        else if (e.getActionCommand().equals(Command.QUIT.getCommandString())) {
            System.exit(0);
        }
        else {
            throw new IllegalArgumentException("Command is not valid");
        }
    }
}
