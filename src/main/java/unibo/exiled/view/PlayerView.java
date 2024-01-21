package unibo.exiled.view;
import javax.swing.*;
import java.awt.*;


public class PlayerView extends ImageIcon{
    private static final String IMAGE_PATH = "src\\main\\java\\unibo\\exiled\\resources\\playerImage.png";

    public PlayerView(){
        super(new ImageIcon(IMAGE_PATH)
                    .getImage()
                    .getScaledInstance(30, 30, Image.SCALE_DEFAULT));
    }

    public void updateSize(Dimension size) {
        Image image = getImage();
        setImage(image.getScaledInstance(size.width, size.height, Image.SCALE_FAST));
    }
}
