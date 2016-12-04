import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

/**
 * Created by tranh on 01-Dec-16.
 */
public class Plane {
    protected int x;
    protected int y;
    protected Image image;
    protected int hp;
    protected int dame;
    protected Bullet bullet;
    protected int keyUp;
    protected int keyDown;
    protected int keyLeft;
    protected int keyRight;

    public Plane(int x, int y, Image image, int keyUp, int keyDown, int keyLeft, int keyRight) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.keyUp = keyUp;
        this.keyDown = keyDown;
        this.keyLeft = keyLeft;
        this.keyRight = keyRight;
    }

    public Plane(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getKeyUp() {
        return keyUp;
    }

    public void setKeyUp(int keyUp) {
        this.keyUp = keyUp;
    }

    public int getKeyDown() {
        return keyDown;
    }

    public void setKeyDown(int keyDown) {
        this.keyDown = keyDown;
    }

    public int getKeyLeft() {
        return keyLeft;
    }

    public void setKeyLeft(int keyLeft) {
        this.keyLeft = keyLeft;
    }

    public int getKeyRight() {
        return keyRight;
    }

    public void setKeyRight(int keyRight) {
        this.keyRight = keyRight;
    }



    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

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

    public Bullet getBullet() {
        return bullet;
    }

    public void setBullet(Bullet bullet) {
        this.bullet = bullet;
    }

    public void draw(Graphics g) {
        g.drawImage(image, x, y, 70, 50, null);
    }

    public void moveUp() {
        this.y -= 5;
    }

    public void moveDown() {
        this.y += 5;
    }

    public void moveRight() {
        this.x += 5;
    }

    public void moveLeft() {
        this.x -= 5;
    }

    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }

    public void press(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == keyUp) {

            move(0, -5);
        } else if (keyCode == keyDown) {
            move(0, 5);
        } else if (keyCode == keyLeft) {
            move(-5, 0);
        } else if (keyCode == keyRight) {
            move(5, 0);
        }
    }

}
