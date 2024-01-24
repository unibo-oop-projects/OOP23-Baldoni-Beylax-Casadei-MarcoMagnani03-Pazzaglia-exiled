package unibo.exiled.view;

import unibo.exiled.config.Constants;
import unibo.exiled.model.utilities.Direction;

import java.awt.*;
import javax.swing.*;

public class PlayerView extends JLabel{
    private static String imagePath = "src\\main\\java\\unibo\\exiled\\resources\\";

    private String imgAnimationName;

    private int animationNumber = 1;
    private Dimension dimensionImg;

    public PlayerView() {
        //this.setIcon(new ImageIcon(imagePath + imgAnimationName));
    }

    public void setImageDimension(Dimension dimension){
        this.dimensionImg = dimension;
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

        this.setIcon(new ImageIcon(new ImageIcon(imagePath + imgAnimationName).getImage().getScaledInstance((int)dimensionImg.getWidth(), (int)dimensionImg.getHeight(), Image.SCALE_SMOOTH)));
        //this.setIcon(new ImageIcon(imagePath + imgAnimationName));
    }

}
