package unibo.exiled.view;

import unibo.exiled.controller.MenuController;
import unibo.exiled.model.menu.MenuItem;

import javax.swing.*;
import java.awt.*;

public class MenuView extends JPanel {
    private MenuController menuController;

    public MenuView(MenuController menuController) {
        super();
        this.menuController = menuController;

        // CREATING STANDARD UI
        JPanel buttonListPanel = new JPanel(new GridBagLayout());
        GridBagConstraints cnst = new GridBagConstraints();
        cnst.gridy = 0;
        cnst.insets = new Insets(3, 3, 3, 3);
        cnst.fill = GridBagConstraints.HORIZONTAL;
        
        for (MenuItem menuItem : this.menuController.getMenuItems()) {
            buttonListPanel.add(new JButton(menuItem.getItemText()), cnst);
            cnst.gridy++;
        }

        this.setLayout(new BorderLayout());
        this.add(buttonListPanel, BorderLayout.CENTER);
    }
}
