package controllers;

import controllers.enemies.EnemyController;
import controllers.manangers.BodyManager;
import models.Model;
import utils.Utils;
import views.SingleView;
import views.View;

import java.awt.*;

/**
 * Created by tranh on 25/12/2016.
 */
public class BombController extends Controller implements BaseController, Body {
    public BombController(Model model, View view) {
        super(model, view);
        BodyManager.instance.register(this);
    }

    @Override
    public void run() {
        this.model.move(0, 2);
    }

    @Override
    public void draw(Graphics g) {
        if (model.isAlive())
            super.draw(g);
    }

    @Override
    public void onContact(Body other) {
        if (other instanceof PlaneController) {
            this.model.setAlive(false);
            System.out.println("trúng r");
        }

    }

    public static BombController create(int x, int y) {
        return new BombController(new Model(x, y, 40, 40), new SingleView(Utils.loadImage("resources/bomb.png")));
    }

}
