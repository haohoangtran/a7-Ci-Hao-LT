package controller;

import models.Model;
import utils.Util;
import views.View;

import java.awt.*;
import java.util.Random;
import java.util.Vector;

/**
 * Created by tranh on 08-Dec-16.
 */
public class EnemyController extends Controller {


    public static final int SPEEDY = 3;
    public static final int WIGHT = 70;
    public static final int HIGHT = 50;

    private static Random r = new Random();
    private  int SPEEDX;

    private Vector<BulletEnemy> bulletEnemyVector;
    private int timeCounter;

    public static String[] path = {"resources/enemy_plane_white_1.png", "resources/enemy_plane_yellow_1.png",
            "resources/enemy-green-3.png", "resources/plane1.png"
    };

    public EnemyController(Model model, View view) {
        super(model, view);
        bulletEnemyVector = new Vector<>();
        timeCounter = 0;
        SPEEDX=r.nextInt()%3;
    }

    public static EnemyController createEnemy(int x, int y) {
        Random r = new Random();
        int a = Math.abs(r.nextInt() % 4);
        return new EnemyController(new Model(x, y, WIGHT, HIGHT),
                new View(Util.loadImage(path[a])));
    }

    public void run() {
        this.model.move(SPEEDX, SPEEDY);
        timeCounter++;
        if (timeCounter > 30) {
            shoot();
            timeCounter = 0;
        }
        for (BulletEnemy bulletEnemy : bulletEnemyVector) {
            bulletEnemy.run();
        }
    }

    public void shoot() {
        this.bulletEnemyVector.add(BulletEnemy.createBulletEnemy(this.getModel().getMidX() - 13, this.getModel().getBottom()));
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        if (bulletEnemyVector.size()!=0)
        for (BulletEnemy bulletEnemy : bulletEnemyVector) {
            bulletEnemy.draw(g);
        }
    }
}

