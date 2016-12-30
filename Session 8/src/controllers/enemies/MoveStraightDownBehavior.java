package controllers.enemies;

/**
 * Created by DUC THANG on 12/21/2016.
 */
public class MoveStraightDownBehavior implements MoveBehavior {
    @Override
    public void doMove(EnemyController enemyController) {
        enemyController.getModel().move(0, 1);
    }
}
