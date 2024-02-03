package unibo.exiled.view.items;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

/**
 * A label with a title.
 */
public final class TitleGameLabel extends JLabel {
	private static final long serialVersionUID = 9L;
    private final static int RED = 255;
    private final static int GREEN = 100;
    private final static int BLUE = 50;
    private final static int FONT_SIZE = 40;
    private final Color foregroundColor = new Color(RED, GREEN, BLUE);

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
        setFont(new Font("Arial", Font.BOLD, FONT_SIZE));
    }
}
