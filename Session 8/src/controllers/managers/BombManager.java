package controllers.managers;

import controllers.gifts.BombController;

import java.util.Random;

/**
 * Created by DUC THANG on 12/26/2016.
 */
public class BombManager extends ControllerManager {
    int counter;

    @Override
    public void run() {
        super.run();
        counter++;
        if (counter == 300) {
            spawn();
            counter = 0;
        }
    }


    private void spawn() {
        BombController bombControler = BombController.create(new Random().nextInt(700), 0);
        this.controllers.add(bombControler);
    }

}
