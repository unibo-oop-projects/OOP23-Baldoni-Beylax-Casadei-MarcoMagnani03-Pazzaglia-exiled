package unibo.exiled.view.Menu;

import unibo.exiled.controller.menu.MenuController;
import unibo.exiled.model.menu.MenuItem;
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
    private final MenuController menuController;

    /**
     * The constructor of the in-game view.
     *
     * @param menuController The controller of the menu.
     * @param game           The main view of the game.
     * @param newGameView    The view of the new game.
     */
    public MenuView(final MenuController menuController, final GameView game, final NewGameView newGameView) {
        super();
        this.menuController = menuController;

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
        for (MenuItem menuItem : this.menuController.getMenuItems()) {
            final GameButton btn = new GameButton(menuItem.getItemText());
            btn.setActionCommand(menuItem.getItemCommand().getCommandString());
            btn.addActionListener(buttonListener);
            buttonListPanel.add(btn, cnst);
            cnst.gridy++;
        }

        this.setLayout(new BorderLayout());
        this.add(buttonListPanel, BorderLayout.CENTER);
    }
}
