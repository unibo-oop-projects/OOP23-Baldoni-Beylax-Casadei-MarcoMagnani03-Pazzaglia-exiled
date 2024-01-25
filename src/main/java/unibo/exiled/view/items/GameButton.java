package unibo.exiled.view.items;

import javax.swing.*;

import unibo.exiled.config.Constants;

import java.awt.*;

public class GameButton extends JButton {
    public GameButton(String text) {
        super(text);
        setButtonStyle();
    }

    private void setButtonStyle() {
        Constants.loadConfiguration(Constants.DEF_CONFIG_PATH);

        setFont(new Font("Arial", Font.BOLD, 16));
        setForeground(Color.WHITE);
        setBackground(new Color(51, 102, 255)); 

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setBackground(new Color(0, 51, 204));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                setBackground(new Color(51, 102, 255));
            }
        });
    }
}
