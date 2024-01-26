package unibo.exiled.view.items;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class TitleGameLabel extends JLabel{
    private Color foregroundColor = new Color(255, 102, 51);

    public TitleGameLabel(String text) {
        super(text);
        setLabelStyle();
    }

    private void setLabelStyle() {
        setForeground(foregroundColor);
        setFont(new Font("Arial", Font.BOLD, 40));
    }
}
