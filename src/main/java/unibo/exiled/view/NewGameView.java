package unibo.exiled.view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import unibo.exiled.config.Constants;
import unibo.exiled.controller.menu.MenuController;
import unibo.exiled.controller.menu.StartMenuController;
import unibo.exiled.view.Menu.MenuView;

public class NewGameView {
    private JFrame mainFrame;
    private final MenuController menuController;

    public NewGameView() {
        Constants.loadConfiguration(Constants.DEF_CONFIG_PATH);

        this.menuController = new StartMenuController();

        this.mainFrame = new JFrame();
        this.mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.mainFrame.setTitle("The Exiled");
        this.mainFrame.setLocationByPlatform(true);
        this.mainFrame.setFocusable(true);
        this.mainFrame.setLayout(new BorderLayout());

        this.mainFrame.add(new MenuView(this.menuController, null, this), BorderLayout.CENTER);
        this.mainFrame.setVisible(true);
    }

    public void hide() {
        this.mainFrame.setVisible(false);
    }
}
