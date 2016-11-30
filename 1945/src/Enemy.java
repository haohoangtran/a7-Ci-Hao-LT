import java.util.Random;

/**
 * Created by tranh on 01-Dec-16.
 */
public class Enemy extends Plane {
    public void autoMove() {
        Random random = new Random();
        int rd = random.nextInt() % 4;
        if (rd < 0) {
            rd = -rd;
        }
        switch (rd) {
            case 0:
                moveUp();
                this.y += 5;
                break;
            case 1:
                moveDown();
                this.y += 5;
                break;
            case 2:
                moveRight();
                this.y += 5;
                break;
            default:
                moveLeft();
                this.y += 5;
                break;
        }
    }
}
