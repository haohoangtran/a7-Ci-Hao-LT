import controllers.Body;
import controllers.manangers.BodyManager;

import java.lang.management.GarbageCollectorMXBean;

/**
 * Created by tranh on 11/30/16.
 */
public class Program {
    public static void main(String[] args) {

        GameWindow gameWindow = new GameWindow();
        Thread thread = new Thread(gameWindow);
        thread.start();

    }
}
