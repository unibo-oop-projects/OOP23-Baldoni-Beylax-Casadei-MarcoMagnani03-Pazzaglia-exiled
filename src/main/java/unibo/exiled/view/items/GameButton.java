package unibo.exiled.view.items;

import unibo.exiled.config.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class GameButton extends JButton {
    private final int ROUNDED_PARAM = 10;

    public GameButton(String text) {
        super(text);
        setButtonStyle();
    }

    private void setButtonStyle() {
        Constants.loadConfiguration(Constants.DEF_CONFIG_PATH);

        setFont(new Font("Arial", Font.BOLD, 16));
        setForeground(Color.WHITE);
        setBackground(new Color(51, 102, 255));

        setBorderPainted(false);
        setContentAreaFilled(false);

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setBackground(new Color(0, 51, 204));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                setBackground(new Color(51, 102, 255));
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(new Color(0, 51, 204));
        } else {
            g.setColor(getBackground());
        }

        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.fill(new RoundRectangle2D.Double(0, 0, width, height, ROUNDED_PARAM, ROUNDED_PARAM));

        super.paintComponent(g);
    }
}
