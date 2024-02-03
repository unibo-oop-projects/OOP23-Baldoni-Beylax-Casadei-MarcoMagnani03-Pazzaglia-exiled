package unibo.exiled.view.character;

import unibo.exiled.config.Constants;
import unibo.exiled.model.utilities.Direction;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * The CharacterView class represents a graphical view of a character in the
 * game.
 * It extends the JLabel class to display images within a Swing component.
 * The class handles the sprites of the character's movement.
 */
public class CharacterView extends JLabel {
    private static final long serialVersionUID = 5L;
    private Image image;
    private String imgAnimationName;
    private int animationNumber = 1;

    private final String upSprite;
    private final String downSprite;
    private final String rightSprite;
    private final String leftSprite;

    private static final String FIRST_IMAGE = "_1.png";
    private static final String SECOND_IMAGE = "_2.png";

    private String defaultImagePath = "src"
            + File.separator
            + "main"
            + File.separator
            + "java"
            + File.separator
            + "unibo"
            + File.separator
            + "exiled" + File.separator
            + "resources" + File.separator;

    /**
     * Constructs a new CharacterView instance with the specified sprite images.
     *
     * @param sprites A list of strings representing the sprite images for the
     *                character,
     *                where the first element is the folder name, the second is the
     *                up sprite,
     *                the third is the down sprite, the fourth is the left sprite,
     *                and the fifth
     *                is the right sprite.
     */
    public CharacterView(final List<String> sprites) {

        Constants.loadConfiguration(Constants.DEF_CONFIG_PATH);
        defaultImagePath += sprites.get(0) + File.separator;

        this.image = new ImageIcon(defaultImagePath + sprites.get(2) + FIRST_IMAGE).getImage();

        this.upSprite = sprites.get(1);
        this.downSprite = sprites.get(2);
        this.leftSprite = sprites.get(3);
        this.rightSprite = sprites.get(4);
    }

    /**
     * Changes the character's image based on the specified direction.
     *
     * @param dir The direction in which the character is moving.
     */
    public void changeImage(final Direction dir) {
        switch (dir) {
            case NORTH -> checkAnimation(this.upSprite);

            case SOUTH -> checkAnimation(this.downSprite);

            case WEST -> checkAnimation(this.leftSprite);

            case EAST -> checkAnimation(this.rightSprite);

            default -> {
            }
        }
        this.image = new ImageIcon(defaultImagePath + imgAnimationName).getImage();
    }

    /**
     * Checks the animation frame and updates the image name accordingly.
     *
     * @param animationName The base name of the animation sprite.
     */
    private void checkAnimation(final String animationName) {
        if (animationNumber == 1) {
            imgAnimationName = animationName + FIRST_IMAGE;
            animationNumber = 2;
        } else {
            imgAnimationName = animationName + SECOND_IMAGE;
            animationNumber = 1;
        }
    }

    /**
     * Overrides the paintComponent method to rescale the image within the JPanel
     * when the main window is resized.
     *
     * @param g The raphics context used for painting.
     */
    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);

        final int width = getWidth();
        final int height = getHeight();

        if (image != null) {
            final int imgWidth, imgHeight;
            final int originalWidth = image.getWidth(null);
            final int originalHeight = image.getHeight(null);
            final double aspectRatio = (double) originalWidth / originalHeight;

            if (width / aspectRatio <= height) {
                imgWidth = width;
                imgHeight = (int) (width / aspectRatio);
            } else {
                imgWidth = (int) (height * aspectRatio);
                imgHeight = height;
            }

            final int x = (width - imgWidth) / 2;
            final int y = (height - imgHeight) / 2;

            g.drawImage(image, x, y, imgWidth, imgHeight, null);
        }
    }

}
