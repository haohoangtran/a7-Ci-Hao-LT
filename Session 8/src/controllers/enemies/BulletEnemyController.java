package controllers.enemies;

import controllers.*;
import controllers.managers.BodyManager;
import models.Model;
import utils.Utils;
import views.SingleView;
import views.View;

/**
 * Created by DUC THANG on 12/16/2016.
 */
public class BulletEnemyController extends Controller implements Body {
    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;
    private static final int SPEED = 7;

    private GameVector moveVector;

    public BulletEnemyController(Model model, View view) {
        this(model, view, new GameVector(0, SPEED));
    }

    public BulletEnemyController(Model model, View view, GameVector moveVector) {
        super(model, view);
        this.moveVector = moveVector;
        BodyManager.instance.register(this);
    }

    public void run() {
        model.move(moveVector);
    }

    public static BulletEnemyController create(int x, int y) {
        return new BulletEnemyController(new Model(x, y, WIDTH, HEIGHT), new SingleView(Utils.loadImage("resources/bullet-round.png")));
    }

    public static BulletEnemyController create(int x, int y, GameVector gameVector) {
        return new BulletEnemyController(new Model(x, y, WIDTH, HEIGHT), new SingleView(Utils.loadImage("resources/bullet-round.png")), gameVector);
    }

    @Override
    public void onContact(Body other) {
        if(other instanceof PlaneController) {
            this.getModel().setAlive(false);
        }
    }
}
