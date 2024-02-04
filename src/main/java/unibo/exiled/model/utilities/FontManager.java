package unibo.exiled.model.utilities;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

/**
 * The FontManager class is responsible for loading and managing custom font
 * used in the application.
 */
public class FontManager {

    /**
     * The custom font loaded by the FontManager.
     */
    private static Font customFont;

    /**
     * Loads the custom font from the specified file path. If the loading fails, it
     * falls back to a default font.
     */
    public static void loadFont() {
        try {
            final String fontPath = "src"
                    + File.separator
                    + "main" + File.separator
                    + "java" + File.separator
                    + "unibo" + File.separator
                    + "exiled" + File.separator
                    + "resources" + File.separator
                    + "font.ttf";
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath)).deriveFont(20f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            // If the custom font loading fails, use a fallback font (Arial, bold, size 16).
            customFont = new Font("Arial", Font.BOLD, 16);
        }
    }

    /**
     * Retrieves the custom font loaded by the FontManager.
     *
     * @return The custom font.
     */
    public static Font getCustomFont() {
        return customFont;
    }

    /**
     * Retrieves the custom font loaded by the FontManager with a specified size.
     *
     * @param size The size to set for the custom font.
     * @return The custom font with the specified size.
     */
    public static Font getCustomFont(int size) {
        return customFont.deriveFont(size);
    }
}
