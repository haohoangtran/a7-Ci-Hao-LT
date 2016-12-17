package controller.managers;

import controller.EnemyController;

import java.awt.*;
import java.util.Random;
import java.util.Vector;

/**
 * Created by tranh on 10-Dec-16.
 */
public class EnemyControllerManager extends ControllerManager {
    private static int timeCounter = 0;

    @Override
    public void run() {
        super.run();
        timeCounter++;
        if (timeCounter > 50) {
            spawn();
            timeCounter = 0;
        }
        for (int i = 0; i < this.controllers.size(); i++) {
            if (controllers.get(i).getModel().getY()>600){
                controllers.remove(i);
                System.out.println(controllers.size());
            }
        }
    }

    private void spawn() {
        //sinh sôi máy bay
        Random random = new Random();
        this.controllers.add(EnemyController.createEnemy(Math.abs(random.nextInt() % 600), 0));
    }

}
