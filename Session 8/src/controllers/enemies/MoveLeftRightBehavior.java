package controllers.enemies;

/**
 * Created by DUC THANG on 12/21/2016.
 */
public class MoveLeftRightBehavior implements MoveBehavior {
    @Override
    public void doMove(EnemyController enemyController) {
        enemyController.getModel().move(1, 1);
    }
}
