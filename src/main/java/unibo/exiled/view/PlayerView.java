package unibo.exiled.view;
import javax.swing.*;
import java.awt.*;


public class PlayerView extends JLabel{
    private static final String IMAGE_PATH = "src\\main\\java\\unibo\\exiled\\resources\\playerImage.png";

    public PlayerView(){
        super();
        this.setIcon(new ImageIcon(new ImageIcon(IMAGE_PATH).getImage().getScaledInstance(50,100, Image.SCALE_DEFAULT)));
    }

    public void updateSize(Dimension size){
        this.setIcon(new ImageIcon(new ImageIcon(IMAGE_PATH).getImage().getScaledInstance((int)size.getWidth(),(int)size.getHeight(), Image.SCALE_DEFAULT)));
    }
}
