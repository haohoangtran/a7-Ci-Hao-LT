import java.io.IOException;

/**
 * Created by tranh on 30-Nov-16.
 */
public class Program {
    public static void main(String[] args) {
        System.out.println("Hello there!");
        //Game game=new Game();
        GameWindow gameWindow = new GameWindow();
        Thread thread = new Thread(gameWindow);
        thread.start();
    }
}
