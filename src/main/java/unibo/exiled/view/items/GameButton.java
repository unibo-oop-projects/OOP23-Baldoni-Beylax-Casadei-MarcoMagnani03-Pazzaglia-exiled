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
    private static final Color PRIMARY_COLOR = new Color(51, 102, 255);
    private static final Color SECONDARY_COLOR = new Color(0, 51, 204);

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
        setBackground(PRIMARY_COLOR);

        setBorderPainted(false);
        setContentAreaFilled(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(final MouseEvent evt) {
                setBackground(SECONDARY_COLOR);
            }

            @Override
            public void mouseExited(final MouseEvent evt) {
                setBackground(PRIMARY_COLOR);
            }
        });
    }

    @Override
    protected void paintComponent(final Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(SECONDARY_COLOR);
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
