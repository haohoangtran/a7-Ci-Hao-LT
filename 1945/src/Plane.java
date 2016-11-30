import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by tranh on 01-Dec-16.
 */
public class Plane {
    protected int x;
    protected int y;
    Image image;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(String path) throws IOException {
        this.image = ImageIO.read(new File(path));
    }
    public void moveUp(){
        this.y-=5;
    }
    public void moveDown(){
        this.y+=5;
    }
    public void moveRight(){
        this.x+=5;
    }
    public void moveLeft(){
        this.x-=5;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "x=" + x +
                ", y=" + y +
                ", image=" + image +
                '}';
    }
}
