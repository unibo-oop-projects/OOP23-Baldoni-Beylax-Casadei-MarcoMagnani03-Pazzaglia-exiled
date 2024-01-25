package unibo.exiled.view.items;

import javax.swing.*;
import java.awt.*;

public class GameProgressBar extends JProgressBar {
    private Color foregroundColor = new Color(51, 102, 255); 
    private Color backgroundColor = new Color(200, 200, 200);

    public GameProgressBar() {
        super(0, 100);
        setProgressBarStyle();
    }

    private void setProgressBarStyle() {
        setStringPainted(true);
        setForeground(foregroundColor);
        setBackground(backgroundColor);
    }

    public void updateProgress(double value) {
        setValue((int)value);
        setString("Health: " + value + "%");
    }
}
