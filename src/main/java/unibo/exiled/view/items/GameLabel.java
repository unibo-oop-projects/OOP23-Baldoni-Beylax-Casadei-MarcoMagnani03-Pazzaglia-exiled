package unibo.exiled.view.items;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.util.List;

/**
 * A new styled label for the view.
 */
public class GameLabel extends JLabel {
    private static final long serialVersionUID = 7L;

    private final List<Integer> foregroundColors = List.of(50, 100, 255);
    private final Color foregroundColor = new Color(
            foregroundColors.get(0),
            foregroundColors.get(1),
            foregroundColors.get(2));

    /**
     * The constructor for the new label.
     *
     * @param text The text to insert in the label.
     */
    public GameLabel(final String text) {
        super(text);
        setLabelStyle();
    }

    private void setLabelStyle() {
        setForeground(foregroundColor);
        setFont(new Font("Arial", Font.PLAIN, 16));
    }

}
