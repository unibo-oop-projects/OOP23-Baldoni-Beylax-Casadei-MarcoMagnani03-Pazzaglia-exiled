package unibo.exiled.view;

import unibo.exiled.model.utilities.Direction;
import javax.swing.*;
import java.awt.*;


public class PlayerView extends JLabel{
    private static String imagePath = "src\\main\\java\\unibo\\exiled\\resources\\boy_down_1.png";
    private int animationNumber = 1;

    public PlayerView(){
        super();
        this.setIcon(new ImageIcon(imagePath));
    }

    public void changeImage(Direction dir, Dimension size){
        switch (dir) {
            case NORTH:
                if(animationNumber == 1){
                    imagePath = "src\\main\\java\\unibo\\exiled\\resources\\boy_up_1.png";
                    animationNumber = 2;
                }else{
                    imagePath = "src\\main\\java\\unibo\\exiled\\resources\\boy_up_2.png";
                    animationNumber = 1;
                }
                break;

            case SOUTH:
                if(animationNumber == 1){
                    imagePath = "src\\main\\java\\unibo\\exiled\\resources\\boy_down_1.png";
                    animationNumber = 2;
                }else{
                    imagePath = "src\\main\\java\\unibo\\exiled\\resources\\boy_down_2.png";
                    animationNumber = 1;
                }
                break;

            case WEST:
                if(animationNumber == 1){
                    imagePath = "src\\main\\java\\unibo\\exiled\\resources\\boy_left_1.png";
                    animationNumber = 2;
                }else{
                    imagePath = "src\\main\\java\\unibo\\exiled\\resources\\boy_left_2.png";
                    animationNumber = 1;
                }
                break;

            case EAST:
                if(animationNumber == 1){
                    imagePath = "src\\main\\java\\unibo\\exiled\\resources\\boy_right_1.png";
                    animationNumber = 2;
                }else{
                    imagePath = "src\\main\\java\\unibo\\exiled\\resources\\boy_right_2.png";
                    animationNumber = 1;
                }
                break;

            default:
                break;
        }
        // this.setIcon(new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance((int)size.getWidth(),(int)size.getHeight(), Image.SCALE_DEFAULT)));
        // TODO: Da guardare con BALDO e KEKKO
        this.setIcon(new ImageIcon(imagePath));
    }

    public void updateSize(Dimension size){
        // this.setIcon(new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance((int)size.getWidth(),(int)size.getHeight(), Image.SCALE_DEFAULT)));
        // TODO: Da guardare con BALDO e KEKKO
        this.setIcon(new ImageIcon(imagePath));
    }
}
