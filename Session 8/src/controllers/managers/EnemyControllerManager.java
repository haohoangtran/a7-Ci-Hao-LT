package controllers.managers;

import controllers.enemies.EnemyController;
import controllers.enemies.EnemyType;
import utils.Utils;

import java.util.Random;

/**
 * Created by DUC THANG on 12/16/2016.
 */
public class EnemyControllerManager extends ControllerManager {
    private int timeCount = 0;

    private void spawn() {
        timeCount++;
        if (timeCount == 100) {
            EnemyController enemyController = EnemyController.create(new Random().nextInt(800), 0, EnemyType.BROWN);
            add(enemyController);
        }

        if (timeCount == 200) {
            EnemyController enemyController1 = EnemyController.create(new Random().nextInt(800), 0, EnemyType.GREEN);
            add(enemyController1);
        }

        if (timeCount == 300) {
            EnemyController enemyController2 = EnemyController.create(new Random().nextInt(800), 0, EnemyType.WHITE);
            add(enemyController2);
            timeCount = 0;
        }
    }

    @Override
    public void remove() {
        super.remove();
    }

    public void run() {
        super.run();
        spawn();
        remove();
    }

}
