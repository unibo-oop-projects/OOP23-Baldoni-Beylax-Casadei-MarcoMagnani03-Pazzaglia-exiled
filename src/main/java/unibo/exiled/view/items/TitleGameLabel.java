package unibo.exiled.view.items;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

/**
 * A label with a title.
 */
public final class TitleGameLabel extends JLabel {
    private final int red = 255;
    private final int green = 100;
    private final int blue = 50;
    private final int fontSize = 40;
    private final Color foregroundColor = new Color(red, green, blue);

    /**
     * Constructs the label with a title.
     *
     * @param text The title.
     */
    public TitleGameLabel(final String text) {
        super(text);
        setLabelStyle();
    }

    private void setLabelStyle() {
        setForeground(foregroundColor);
        setFont(new Font("Arial", Font.BOLD, fontSize));
    }
}
