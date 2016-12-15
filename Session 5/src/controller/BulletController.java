package controller;

import models.Model;
import utils.Util;
import views.View;

/**
 * Created by tranh on 05-Dec-16.
 */
public class BulletController extends Controller implements Body{
    public BulletController(Model model, View view) {
        super(model, view);
        BodyManager.instance.register(this);
    }

    public void run() {
        this.model.move(0, -5);
    }

    public static BulletController createBulletController(int x, int y) {
        return new BulletController(new Model(x, y, 12, 32), new View(Util.loadImage("resources/bullet.png")));
    }

    @Override
    public void onContact(Body other) {
        System.out.println("Dan ban trung gi day");
        this.model.setAlive(false);
    }
}
