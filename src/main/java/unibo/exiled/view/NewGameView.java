package unibo.exiled.view;

import unibo.exiled.config.Constants;
import unibo.exiled.model.utilities.FontManager;
import unibo.exiled.view.menu.MenuView;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.event.WindowEvent;

/**
 * The new game selection menu view.
 */
public final class NewGameView {
    private final JFrame mainFrame;

    /**
     * The constructor of the new game selection view.
     */
    public NewGameView() {
        Constants.loadConfiguration(Constants.DEF_CONFIG_PATH);
        FontManager.loadFont();

        this.mainFrame = new JFrame();
        this.mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.mainFrame.setTitle("The Exiled");
        this.mainFrame.setLocationByPlatform(true);
        this.mainFrame.setFocusable(true);
        this.mainFrame.setLayout(new BorderLayout());
        this.mainFrame.add(new MenuView(null, this), BorderLayout.CENTER);
        this.mainFrame.setVisible(true);
    }

    /**
     * Hides the "New Game" view.
     */
    public void hide() {
        this.mainFrame.setVisible(false);
    }

    /**
     * Close the main frame.
     */
    public void close() {
        this.mainFrame.dispatchEvent(new WindowEvent(mainFrame, WindowEvent.WINDOW_CLOSING));
    }
}
