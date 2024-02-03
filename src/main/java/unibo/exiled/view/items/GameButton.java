package unibo.exiled.view.items;

import unibo.exiled.config.Constants;

import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;

/**
 * Custom JButton with a specific style for the game.
 */
public final class GameButton extends JButton {
    private static final long serialVersionUID = 6L;
    private static final int ROUNDED_PARAM = 10;

    private static final int PRIMARY_RED = 51;
    private static final int PRIMARY_GREEN = 102;
    private static final int PRIMARY_BLUE = 255;
    private final Color primaryColor = new Color(PRIMARY_RED, PRIMARY_GREEN, PRIMARY_BLUE);

    private static final int SECONDARY_RED = 0;
    private static final int SECONDARY_GREEN = 51;
    private static final int SECONDARY_BLUE = 204;
    private final Color secondaryColor = new Color(SECONDARY_RED, SECONDARY_GREEN, SECONDARY_BLUE);

    /**
     * Constructs a GameButton with the specified text.
     *
     * @param text The text to be displayed on the button.
     */
    public GameButton(final String text) {
        super(text);
        setButtonStyle();
    }

    private void setButtonStyle() {
        Constants.loadConfiguration(Constants.DEF_CONFIG_PATH);

        setFont(new Font("Arial", Font.BOLD, 16));
        setForeground(Color.WHITE);
        setBackground(primaryColor);

        setBorderPainted(false);
        setContentAreaFilled(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(final MouseEvent evt) {
                setBackground(secondaryColor);
            }

            @Override
            public void mouseExited(final MouseEvent evt) {
                setBackground(primaryColor);
            }
        });
    }

    @Override
    protected void paintComponent(final Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(secondaryColor);
        } else {
            g.setColor(getBackground());
        }

        final int width = getWidth();
        final int height = getHeight();
        final Graphics2D graphics = (Graphics2D) g;
        graphics.fill(new RoundRectangle2D.Double(0, 0, width, height, ROUNDED_PARAM, ROUNDED_PARAM));

        super.paintComponent(g);
    }
}
