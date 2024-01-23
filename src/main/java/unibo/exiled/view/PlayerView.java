package unibo.exiled.view;

import unibo.exiled.config.Constants;
import unibo.exiled.model.utilities.Direction;

import java.awt.*;
import javax.swing.*;

public class PlayerView extends JPanel{
    private static String imagePath = "src\\main\\java\\unibo\\exiled\\resources\\";

    private Image img;
    private Image scaled;
    private String imgAnimationName;

    private int animationNumber = 1;

    public PlayerView(){
        this(new ImageIcon(imagePath + Constants.getConstantOf("STARTING_PLAYER_ANIMATION")).getImage());
    }

    public PlayerView(Image img){
        this.img = img;
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
        // this.setIcon(new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance((int)size.getWidth(),(int)size.getHeight(), Image.SCALE_DEFAULT)));
        // TODO: Da guardare con BALDO e KEKKO
        img = (new ImageIcon(imagePath+imgAnimationName).getImage());
    }

    public void updateSize(Dimension size){
        // this.setIcon(new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance((int)size.getWidth(),(int)size.getHeight(), Image.SCALE_DEFAULT)));
        // TODO: Da guardare con BALDO e KEKKO
        img = (new ImageIcon(imagePath+imgAnimationName).getImage());
    }

    @Override
    public void invalidate() {
      super.invalidate();
      int width = getWidth();
      int height = getHeight();
  
      if (width > 0 && height > 0) {
        scaled = img.getScaledInstance(getWidth(), getHeight(),
            Image.SCALE_SMOOTH);
      }
    }
  
    @Override
    public Dimension getPreferredSize() {
      return img == null ? new Dimension(200, 200) : new Dimension(
          img.getWidth(this), img.getHeight(this));
    }
  
    @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.drawImage(scaled, 0, 0, null);
    }
}
