import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by tranh on 02-Dec-16.
 */
public class Bullet {
    private int x;
    private int y;
    private Image image;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public Bullet(int x, int y,Image image) {
        this.x = x;
        this.y = y;
        this.image=image;
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

    public Bullet() {
    }

    public void fire() throws InterruptedException {
        this.y -= 10;
        Thread.sleep(500);
    }

    public void draw(Graphics g) {
        g.drawImage(image,x,y,null);
    }
    public void move(){
        this.y-=5;
    }

}
