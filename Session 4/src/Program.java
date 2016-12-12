/**
 * Created by tranh on 05-Dec-16.
 */
public class Program {
    public static void main(String[] args) {
        GameWindow gameWindow = new GameWindow();
        new Thread(gameWindow).start();
        // giải quyết bài toán 1 loạt hcn làm tn để biết nó va chạm
    }
}
