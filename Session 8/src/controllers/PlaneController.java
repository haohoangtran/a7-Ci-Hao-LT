package controllers;

import controllers.enemies.BulletEnemyController;
import controllers.enemies.EnemyController;
import controllers.managers.BodyManager;
import controllers.managers.ControllerManager;
import controllers.managers.EnemyControllerManager;
import controllers.notifications.EventType;
import controllers.notifications.NotificationCenter;
import models.Model;
import utils.Utils;
import views.Animation;
import views.SingleView;
import views.View;

import java.awt.*;
import java.awt.event.KeyEvent;

import static utils.Utils.loadImage;

/**
 * Created by DUC THANG on 12/16/2016.
 */

public class PlaneController extends Controller implements Body {
    private static final int SPEED = 5;
    private int hp;
    private int lives;
    public KeySetting keySetting;
    private ControllerManager bulletManager;

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void setHp(int hp) {

        this.hp = hp;
    }

    public static final PlaneController instance = createPlane(300, 500);

    public PlaneController(Model model, View view) {
        super(model, view);
        BodyManager.instance.register(this);
        this.hp = 3;
        this.lives = 3;
        bulletManager = new ControllerManager();
    }

    public void keyPressed(KeyEvent e) {
        if (keySetting != null) {
            int keyCode = e.getKeyCode();
            if (keyCode == keySetting.keyUp) {
                model.move(0, -SPEED);
            } else if (keyCode == keySetting.keyDown) {
                model.move(0, SPEED);
            } else if (keyCode == keySetting.keyLeft) {
                model.move(-SPEED, 0);
            } else if (keyCode == keySetting.keyRight) {
                model.move(SPEED, 0);
            } else if (keyCode == keySetting.keyShoot && hp > 0) {
                shoot();
            }
        }
    }

    private int time = 0;
    @Override
    public void run() {
        super.run();
        bulletManager.run();

        if(!model.isAlive()) {
            time++;
            System.out.println(lives);
            System.out.println(time);
            if(lives >= 0 && time >= 100) {
                lives--;
                System.out.println("hihi");
                hp = 3;
                model.setAlive(true);
                BodyManager.instance.register(this);
                model.setX(300);
                model.setY(500);
                time = 0;
            }
        }
        else if(lives == 0){
            this.model.setAlive(false);

        }
    }

    @Override
    public void draw(Graphics g) {
        if(model.isAlive()) {
            super.draw(g);
            bulletManager.draw(g);
        }

        g.setFont(new Font("NewellsHand", Font.BOLD, 20));
        g.drawString(String.valueOf(PlaneController.instance.getLives()) + " x ",10, 60);
        g.drawImage(Utils.loadImage("resources/plane3.png"), 50, 40, 35, 35, null);
    }

    private void shoot() {
        Utils.playSound("resources/Jump3.wav", false);
        BulletController bulletController = BulletController.createBulletController(this.getModel().getMidX() - BulletController.WIDTH / 2, this.model.getY() - BulletController.HEIGHT);
        bulletManager.add(bulletController);
    }

    public static PlaneController createPlane(int x, int y) {
        return new PlaneController(new Model(x, y, 70, 50), new SingleView(loadImage("resources/plane3.png")));
    }

    public void destroy() {
        ExplosionController explosionController = new ExplosionController(
                new Model(this.getModel().getX(), this.getModel().getY(), 70, 50),
                new Animation(Utils.loadSheet("resources/explosion.png", 32, 32, 1, 6))
        );
        ControllerManager.explosion.add(explosionController);
        Utils.playSound("resources/explosion10.wav", false);
    }

    @Override
    public void onContact(Body other) {
        if (other instanceof BulletEnemyController) {
            System.out.println("trúng đạn địch");
            this.hp--;
            if(hp <= 0) {
                this.getModel().setAlive(false);
                destroy();
            }
        }
    }

    //interface PlaneDestroyListener

    public int getHp() {
        return hp;
    }

    public int getLives() {
        return lives;
    }
}
