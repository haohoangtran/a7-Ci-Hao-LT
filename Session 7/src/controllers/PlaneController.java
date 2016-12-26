package controllers;

import controllers.enemies.EnemyBulletController;
import controllers.enemies.EnemyController;
import controllers.manangers.BodyManager;
import controllers.manangers.ControllerManager;
import models.Model;
import utils.Utils;
import views.Animation;
import views.SingleView;
import views.View;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by tranh on 25/12/2016.
 */
public class PlaneController extends Controller implements Body {

    private static final int SPEED = 5;
    private int life = 4;
    private static int timeCount = 0;

    public int getLife() {
        return life;
    }

    public KeySetting keySetting;

    private ControllerManager bulletManager;

    public static final PlaneController instance = createPlane(300, 300);//singleton

    private PlaneController(Model model, View view) {
        super(model, view);
        bulletManager = new ControllerManager();
        BodyManager.instance.register(this);

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
            } else if (keyCode == keySetting.keyShoot) {
                shoot();
            }
        }
    }

    @Override
    public void run() {
        if (!model.isAlive()) {
            timeCount++;
            if (timeCount > 120) {
                this.model.setAlive(true);
                BodyManager.instance.register(this);
                timeCount = 0;
            }
        }
        super.run();
        bulletManager.run();
    }

    @Override
    public void draw(Graphics g) {
        int xStar = 10;
        for (int i = 0; i < life; i++) {
            g.drawImage(Utils.loadImage("resources/star.png"), xStar, 40, 25, 25, null);
            xStar += 35;
        }
        if (this.model.isAlive()) {
            super.draw(g);
        }
        if (life < 1) {
            g.setFont(new Font("Time New Romans", Font.BOLD, 36));
            g.setColor(Color.red);
            g.drawString("Game Over", 300, 300);
        }
        bulletManager.draw(g);
    }

    private void shoot() {
        Utils.playSound("resources/shoot.wav", false);
        BulletController bulletController = BulletController.create(this.model.getMidX() - BulletController.WIDTH / 2,
                this.model.getY() - BulletController.HEIGHT);
        bulletManager.add(bulletController);
    }

    // Design pattern
    // Factory
    private static PlaneController createPlane(int x, int y) {

        PlaneController planeController = new PlaneController(
                new Model(x, y, 40, 30),
                new SingleView(Utils.loadImage("resources/plane3.png"))
        );
        return planeController;

    }

    @Override
    public void onContact(Body other) {
        if (other instanceof EnemyBulletController) {
            Utils.playSound("resources/ngunay.wav", false);
            this.life -= 1;

            this.getModel().setAlive(false);

        }
        if (other instanceof BombController){
            for (int i = 0; i < BodyManager.instance.getBodies().size(); i++) {
                if (BodyManager.instance.getBodies().get(i) instanceof EnemyController) {
                    int x = model.getMidX() - other.getModel().getMidX();
                    int y = model.getMidY() - other.getModel().getMidY();
                    double r = Math.sqrt(x * x + y * y);
                    if (r < 200) {
                        BodyManager.instance.getBodies().get(i).getModel().setAlive(false);
                    }
                }
            }
        }
    }

    public void destroy() {
        if (!this.model.isAlive()) {
            ExplosionController explosionController = new ExplosionController(
                    new Model(this.getModel().getX(), this.getModel().getY(), 32, 32),
                    new Animation(Utils.loadSheet("resources/explosion.png", 32, 32, 1, 6))
            );

            ControllerManager.explosion.add(explosionController);
        }
    }

}
