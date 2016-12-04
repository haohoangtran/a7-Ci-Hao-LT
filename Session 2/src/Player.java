import java.awt.*;

/**
 * Created by tranh on 01-Dec-16.
 */
public class Player extends Plane {

    public Player(int x, int y, Image image, int keyUp, int keyDown, int keyLeft, int keyRight) {
        super(x, y, image, keyUp, keyDown, keyLeft, keyRight);
    }

    public Player(int x, int y) {
        super(x, y);
    }
}
