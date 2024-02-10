package unibo.exiled.view.items;

import javax.swing.JProgressBar;

import unibo.exiled.utilities.FontManager;

import java.awt.Color;
import java.util.List;

/**
 * A custom progress bar for the view.
 */
public final class GameProgressBar extends JProgressBar {
    private static final long serialVersionUID = 8L;
    private final List<Integer> foregroundColors = List.of(50, 100, 255);
    private final List<Integer> backGroundColors = List.of(200, 200, 200);
    private final Color foregroundColor = new Color(foregroundColors.get(0), foregroundColors.get(1), foregroundColors.get(2));
    private final Color backgroundColor = new Color(backGroundColors.get(0), backGroundColors.get(1), backGroundColors.get(2));

    /**
     * The constructor of the progress bar with the selected limits.
     */
    public GameProgressBar() {
        super(0, 100);
        setProgressBarStyle();
    }

    private void setProgressBarStyle() {
        setStringPainted(true);
        setFont(FontManager.getCustomFont());
        setForeground(foregroundColor);
        setBackground(backgroundColor);
    }

    /**
     * Updates the progress bar with the selected value.
     *
     * @param value The new value of the progress bar.
     */
    public void updateProgress(final double value) {
        setValue((int) value);
        setString("Health: " + value + "%");
    }
}
