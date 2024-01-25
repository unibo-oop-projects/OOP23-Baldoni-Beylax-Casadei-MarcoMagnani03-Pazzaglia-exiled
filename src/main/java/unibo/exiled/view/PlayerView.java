package unibo.exiled.view;

import unibo.exiled.config.Constants;
import unibo.exiled.model.utilities.Direction;

import java.awt.*;
import java.io.File;

import javax.swing.*;

public class PlayerView extends JLabel{
    private final String FIRST_IMAGE = "_1.png";
    private final String SECOND_IMAGE = "_2.png";
    private final static String imagePath = "src"+File.separator+
                                            "main"+File.separator+
                                            "java"+File.separator+
                                            "unibo"+File.separator+
                                            "exiled"+File.separator+
                                            "resources"+File.separator+
                                            "player"+File.separator;
    private Image image;
    private String imgAnimationName;
    private int animationNumber = 1;

    public PlayerView() {
        Constants.loadConfiguration(Constants.DEF_CONFIG_PATH);
        image = new ImageIcon(imagePath + Constants.getConstantOf("STARTING_PLAYER_ANIMATION")).getImage();
    }

    private void checkAnimation(String animationName){
        if(animationNumber == 1){
            imgAnimationName = animationName + FIRST_IMAGE;
            animationNumber = 2;
        }else{
            imgAnimationName = animationName + SECOND_IMAGE;
            animationNumber = 1;
        }
    }

    public void changeImage(Direction dir){
        switch (dir) {
            case NORTH -> checkAnimation("boy_up");

            case SOUTH -> checkAnimation("boy_down");

            case WEST -> checkAnimation("boy_left");

            case EAST -> checkAnimation("boy_right");

            default -> {}
        }

        image = new ImageIcon(imagePath + imgAnimationName).getImage();
    }

    // This override permit to rescale the image inner the JPanel 
    // when the main window resize it's width, height or both.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();

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

