package controllers.enemies;

import controllers.BulletController;
import controllers.managers.ControllerManager;

/**
 * Created by DUC THANG on 12/21/2016.
 */
public class ShootDownBehavior implements ShootBehavior {
    @Override
    public void doShoot(EnemyController enemyController) {
        int x = enemyController.getModel().getMidX() - 5;
        int y = enemyController.getModel().getY() + 30;
        BulletEnemyController bulletEnemyController = BulletEnemyController.create(x, y);
        ControllerManager.enemyBullet.add(bulletEnemyController);
    }
}
