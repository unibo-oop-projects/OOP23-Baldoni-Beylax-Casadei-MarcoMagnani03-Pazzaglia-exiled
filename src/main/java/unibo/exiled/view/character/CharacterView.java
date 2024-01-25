package unibo.exiled.view.character;

import unibo.exiled.config.Constants;
import unibo.exiled.model.utilities.Direction;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class CharacterView extends JLabel {
    private Image image;
    private String imgAnimationName;
    private int animationNumber = 1;

    private final String northSprite;
    private final String southSprite;
    private final String eastSprite;
    private final String westSprite;

    private final static String FIRST_IMAGE = "_1.png";
    private final static String SECOND_IMAGE = "_2.png";

    private String DEFAULT_IMAGE_PATH =
            "src" + File.separator +
            "main" + File.separator +
            "java" + File.separator +
            "unibo" + File.separator +
            "exiled" + File.separator +
            "resources" + File.separator;

    public CharacterView(final String imagePath,
                         final String northSprite,
                         final String southSprite,
                         final String eastSprite,
                         final String westSprite){

        Constants.loadConfiguration(Constants.DEF_CONFIG_PATH);
        DEFAULT_IMAGE_PATH += imagePath + File.separator;

        this.image = new ImageIcon(DEFAULT_IMAGE_PATH + southSprite + FIRST_IMAGE).getImage();

        this.southSprite = southSprite;
        this.eastSprite = eastSprite;
        this.westSprite = westSprite;
        this.northSprite = northSprite;
    }

    public void changeImage(final Direction dir){
        switch (dir) {
            case NORTH -> checkAnimation(this.northSprite);

            case SOUTH -> checkAnimation(this.southSprite);

            case WEST -> checkAnimation(this.westSprite);

            case EAST -> checkAnimation(this.eastSprite);

            default -> {}
        }
        this.image = new ImageIcon(DEFAULT_IMAGE_PATH + imgAnimationName).getImage();
    }

    private void checkAnimation(final String animationName){
        if(animationNumber == 1){
            imgAnimationName = animationName + FIRST_IMAGE;
            animationNumber = 2;
        }else{
            imgAnimationName = animationName + SECOND_IMAGE;
            animationNumber = 1;
        }
    }

    // This override permit to rescale the image inner the JPanel
    // when the main window resize its width, height or both.
    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);

        final int width = getWidth();
        final int height = getHeight();

        if (image != null) {
            int imgWidth, imgHeight;
            int originalWidth = image.getWidth(null);
            int originalHeight = image.getHeight(null);
            double aspectRatio = (double) originalWidth / originalHeight;

            if (width / aspectRatio <= height) {
                imgWidth = width;
                imgHeight = (int) (width / aspectRatio);
            } else {
                imgWidth = (int) (height * aspectRatio);
                imgHeight = height;
            }

            int x = (width - imgWidth) / 2;
            int y = (height - imgHeight) / 2;

            g.drawImage(image, x, y, imgWidth, imgHeight, null);
        }
    }

}
