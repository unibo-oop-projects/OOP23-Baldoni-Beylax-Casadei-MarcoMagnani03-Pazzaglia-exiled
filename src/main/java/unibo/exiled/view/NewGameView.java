package unibo.exiled.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import unibo.exiled.config.Constants;
import unibo.exiled.controller.GameController;
import unibo.exiled.controller.GameControllerImpl;
import unibo.exiled.view.Menu.MenuView;

public class NewGameView {
    private JFrame mainFrame;
    private final GameController gameController;

    public NewGameView() {
        Constants.loadConfiguration(Constants.DEF_CONFIG_PATH);

        this.gameController = new GameControllerImpl();

        this.mainFrame = new JFrame();
        this.mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.mainFrame.setTitle("The Exiled");
        this.mainFrame.setLocationByPlatform(true);
        this.mainFrame.setFocusable(true);

        this.mainFrame.setLayout(new BorderLayout());

        this.mainFrame.add(new MenuView(gameController.getStartMenuController(), null), BorderLayout.CENTER);
        this.mainFrame.setVisible(true);
    }
}
