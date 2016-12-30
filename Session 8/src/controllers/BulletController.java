package controllers;

import controllers.enemies.EnemyController;
import controllers.managers.BodyManager;
import models.Model;
import views.SingleView;
import views.View;

import static utils.Utils.loadImage;

/**
 * Created by DUC THANG on 12/16/2016.
 */
public class BulletController extends Controller implements Body {

    public static final int WIDTH = 13;
    public static final int HEIGHT = 33;

    public BulletController(Model model, View view) {
        super(model, view);
        BodyManager.instance.register(this);
    }

    public void run() {
        this.model.move(0, -5);
    }

    public static BulletController createBulletController(int x, int y) {
        return new BulletController(new Model(x, y, WIDTH, HEIGHT), new SingleView(loadImage("resources/bullet.png")));
    }

    @Override
    public void onContact(Body other) {
        if (other instanceof EnemyController) {
            System.out.println("trúng máy bay");
            this.getModel().setAlive(false);
            ((EnemyController) other).destroy();
        }
    }
}
