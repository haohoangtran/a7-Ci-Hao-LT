package controllers.gifts;

import controllers.Body;
import controllers.Controller;
import controllers.PlaneController;
import controllers.enemies.EnemyController;
import controllers.managers.BodyManager;
import controllers.notifications.EventType;
import controllers.notifications.NotificationCenter;
import models.Model;
import utils.Utils;
import views.SingleView;
import views.View;

import java.awt.*;


/**
 * Created by DUC THANG on 12/26/2016.
 */
public class BombController extends Controller implements Body {
    private static final int WIDTH = 24;
    private static final int HEIGHT = 24;

    public BombController(Model model, View view) {
        super(model, view);
        BodyManager.instance.register(this);
    }

    public static BombController create(int x, int y) {
        return new BombController(new Model(x, y, WIDTH, HEIGHT), new SingleView(Utils.loadImage("resources/bomb.png")));
    }

    @Override
    public void run() {
        this.getModel().move(0, 1);
    }

    @Override
    public void onContact(Body other) {
        if (other instanceof PlaneController) {
            model.setAlive(false);
            NotificationCenter.instance.OnEvent(EventType.BOMB_EXPLOSION, this);
            this.destroy();
        }
    }

    @Override
    public void draw(Graphics g) {
        if (model.isAlive())
            super.draw(g);
    }
}
