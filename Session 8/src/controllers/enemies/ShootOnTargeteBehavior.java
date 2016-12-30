package controllers.enemies;

import controllers.GameVector;
import controllers.PlaneController;
import controllers.managers.ControllerManager;

/**
 * Created by DUC THANG on 12/21/2016.
 */
public class ShootOnTargeteBehavior implements ShootBehavior {
    private final int SPEED = 5;
    @Override
    public void doShoot(EnemyController enemyController) {
        int x = enemyController.getModel().getMidX();
        int y = enemyController.getModel().getMidY();

        PlaneController planeController = PlaneController.instance;

        GameVector dVector = planeController.getModel().subtract(enemyController.getModel());
        double length = dVector.getLength();
        double steps = length / (double) SPEED;
        GameVector bulletMoveVector = new GameVector((int)(dVector.dx / steps), (int)(dVector.dy / steps));

        BulletEnemyController bulletEnemyController = BulletEnemyController.create(x, y, bulletMoveVector);
        ControllerManager.enemyBullet.add(bulletEnemyController);
    }
}
