package controllers.enemies;

import controllers.Body;
import controllers.BulletController;
import controllers.Controller;
import controllers.ExplosionController;
import controllers.gifts.BombController;
import controllers.managers.BodyManager;
import controllers.managers.ControllerManager;
import controllers.notifications.EventSubcriber;
import controllers.notifications.EventType;
import controllers.notifications.NotificationCenter;
import models.Model;
import utils.Utils;
import views.Animation;
import views.SingleView;
import views.View;

import java.awt.image.BufferedImage;
import java.util.Vector;

/**
 * Created by DUC THANG on 12/16/2016.
 */

public class EnemyController extends Controller implements Body, EventSubcriber {
    private int hp;
    private static final int WIDTH = 35;
    private static final int HEIGHT = 30;
    private int timeCounter;

    private MoveBehavior moveBehavior;
    private ShootBehavior shootBehavior;

    public EnemyController(Model model, View view, MoveBehavior moveBehavior, ShootBehavior shootBehavior) {
        super(model, view);
        this.moveBehavior = moveBehavior;
        this.shootBehavior = shootBehavior;
        this.hp = 5;
        BodyManager.instance.register(this);
        NotificationCenter.instance.register(this);
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void run() {
        if(moveBehavior != null) {
            moveBehavior.doMove(this);
        }

        timeCounter++;
        if(timeCounter > 70) {
            shoot();
            timeCounter = 0;
        }
    }

    private void shoot() {
        if(shootBehavior != null) {
            shootBehavior.doShoot(this);
        }
    }

    public static EnemyController create(int x, int y, EnemyType enemyType) {
        switch (enemyType) {
            case BROWN:
                return new EnemyController(new Model(x, y, WIDTH, HEIGHT), new SingleView(Utils.loadImage("resources/plane1.png")), new MoveStraightDownBehavior(), new ShootDownBehavior());
            case GREEN:
                return new EnemyController(new Model(x, y, WIDTH, HEIGHT), new SingleView(Utils.loadImage("resources/enemy-green-3.png")), new MoveLeftRightBehavior(), new ShootLeftRightBehavior());
            case WHITE:
                Vector <BufferedImage> images = new Vector<>();
                images.add(Utils.loadImage("resources/enemy_plane_white_1.png"));
                images.add(Utils.loadImage("resources/enemy_plane_white_2.png"));
                images.add(Utils.loadImage("resources/enemy_plane_white_3.png"));

                return new EnemyController(new Model(x, y, 30, 30), new Animation(images), new MoveZigZagBehavior(), new ShootOnTargeteBehavior());
        }

        return null;
    }

    @Override
    public void onContact(Body other) {
        if(other instanceof BulletController) {
            System.out.println("trúng đạn");
            this.hp -= 5;
            BombController bombController = BombController.create(model.getX(), model.getY());
            ControllerManager.bomb.add(bombController);
            if(hp <= 0)
                this.getModel().setAlive(false);
        }
    }

    public void destroy() {
        ExplosionController explosionController = new ExplosionController(
                new Model(this.getModel().getX(), this.getModel().getY(), 32, 32),
                new Animation(Utils.loadSheet("resources/explosion.png", 32, 32, 1, 6))
        );
        ControllerManager.explosion.add(explosionController);
        Utils.playSound("resources/explosion10.wav", false);
        this.model.setAlive(false);
    }

    @Override
    public boolean onEvent(EventType eventType, Object params) {
        switch (eventType) {
            case BOMB_EXPLOSION:
                this.destroy();
                return false;
        }
        return true;
    }
}
