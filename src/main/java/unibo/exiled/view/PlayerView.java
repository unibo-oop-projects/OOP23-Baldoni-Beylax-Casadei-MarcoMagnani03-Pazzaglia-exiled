package unibo.exiled.view;

import unibo.exiled.config.Constants;
import unibo.exiled.model.utilities.Direction;

import java.awt.*;
import java.io.File;

import javax.swing.*;

public class PlayerView extends JLabel{
    private static String imagePath = "src"+File.separator+"main"+File.separator+"java"+File.separator+"unibo"+File.separator+"exiled"+File.separator+"resources"+File.separator+"player"+File.separator;

    private String imgAnimationName;

    private int animationNumber = 1;

    private Image image;

    public PlayerView() {
        Constants.loadConfiguration(Constants.DEF_CONFIG_PATH);
        image = new ImageIcon(imagePath + Constants.getConstantOf("STARTING_PLAYER_ANIMATION")).getImage();
    }

    public void changeImage(Direction dir){
        switch (dir) {
            case NORTH:
                if(animationNumber == 1){
                    imgAnimationName = "boy_up_1.png";
                    animationNumber = 2;
                }else{
                    imgAnimationName = "boy_up_2.png";
                    animationNumber = 1;
                }
                break;

            case SOUTH:
                if(animationNumber == 1){
                    imgAnimationName = "boy_down_1.png";
                    animationNumber = 2;
                }else{
                    imgAnimationName = "boy_down_2.png";
                    animationNumber = 1;
                }
                break;

            case WEST:
                if(animationNumber == 1){
                    imgAnimationName = "boy_left_1.png";
                    animationNumber = 2;
                }else{
                    imgAnimationName = "boy_left_2.png";
                    animationNumber = 1;
                }
                break;

            case EAST:
                if(animationNumber == 1){
                    imgAnimationName = "boy_right_1.png";
                    animationNumber = 2;
                }else{
                    imgAnimationName = "boy_right_2.png";
                    animationNumber = 1;
                }
                break;

            default:
                break;
        }

        image = new ImageIcon(imagePath + imgAnimationName).getImage();
    }

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

