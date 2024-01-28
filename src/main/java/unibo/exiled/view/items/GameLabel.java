package unibo.exiled.view.items;

import javax.swing.*;
import java.awt.*;

public class GameLabel extends JLabel {
    private Color foregroundColor = new Color(51, 102, 255);

    public GameLabel(String text) {
        super(text);
        setLabelStyle();
    }

    public GameLabel(String string, int center) {
        super(string, center);
        setLabelStyle();
    }

    private void setLabelStyle() {
        setForeground(foregroundColor);
        setFont(new Font("Arial", Font.PLAIN, 16));
    }

}

