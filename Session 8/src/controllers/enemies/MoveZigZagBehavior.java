package controllers.enemies;

/**
 * Created by DUC THANG on 12/24/2016.
 */
public class MoveZigZagBehavior implements MoveBehavior {
    private int timeMoveRight = 0;
    private int timeMoveLeft = 100;
    @Override
    public void doMove(EnemyController enemyController) {
        if(timeMoveLeft > 0) {
            enemyController.getModel().move(-1, 1);
            timeMoveLeft--;
            if(timeMoveLeft == 0)
                timeMoveRight = 100;
        }

        if(timeMoveRight > 0) {
            enemyController.getModel().move(1, 1);
            timeMoveRight--;
            if(timeMoveRight == 0)
                timeMoveLeft = 100;
        }
    }
}
