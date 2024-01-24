package unibo.exiled.view;

import unibo.exiled.config.Constants;
import unibo.exiled.model.utilities.Direction;

import java.awt.*;
import javax.swing.*;

public class PlayerView extends JLabel{
    private static String imagePath = "src\\main\\java\\unibo\\exiled\\resources\\";

    private String imgAnimationName;

    private int animationNumber = 1;

    public PlayerView() {
        if(this.getWidth() != 0 && this.getHeight() != 0){
            this.setIcon(new ImageIcon(new ImageIcon(imagePath + Constants.getConstantOf("STARTING_PLAYER_ANIMATION")).getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH)));
        }else{
            this.setIcon(new ImageIcon(imagePath + Constants.getConstantOf("STARTING_PLAYER_ANIMATION")));
        }
    }

    public void changeImage(Direction dir, Dimension size){
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
        //this.setIcon(new ImageIcon(imagePath + imgAnimationName));
        this.setIcon(new ImageIcon(new ImageIcon(imagePath + imgAnimationName).getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH)));

    }

}
