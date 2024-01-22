package unibo.exiled.view.Menu;

import unibo.exiled.controller.MenuController;
import unibo.exiled.model.menu.MenuItem;
import unibo.exiled.view.GameView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuView extends JPanel {
    private MenuController menuController;

    public MenuView(MenuController menuController, GameView game) {
        super();
        this.menuController = menuController;

        // CREATING STANDARD UI
        JPanel buttonListPanel = new JPanel(new GridBagLayout());
        GridBagConstraints cnst = new GridBagConstraints();
        cnst.gridy = 0;
        cnst.insets = new Insets(3, 3, 3, 3);
        cnst.fill = GridBagConstraints.HORIZONTAL;
        
        ActionListener buttonListener = new MenuItemActionListener(game);
        for (MenuItem menuItem : this.menuController.getMenuItems()) {
            JButton btn = new JButton(menuItem.getItemText());
            btn.setActionCommand(menuItem.getItemCommand().getCommandString());
            btn.addActionListener(buttonListener);
            buttonListPanel.add(btn, cnst);
            cnst.gridy++;
        }

        this.setLayout(new BorderLayout());
        this.add(buttonListPanel, BorderLayout.CENTER);
    }
}