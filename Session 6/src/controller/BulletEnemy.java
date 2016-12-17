package controller;

import models.Model;
import utils.Util;
import views.View;

/**
 * Created by tranh on 08-Dec-16.
 */
public class BulletEnemy extends Controller implements Body {
    private static  int HIGHT = 32;
    private static int SPEED=4;
    private static int WIDTH=32;
    public BulletEnemy(Model model, View view) {
        super(model, view);
        BodyManager.instance.register(this);
    }
    public void run(){
        this.model.move(0,SPEED);
    }
    public static BulletEnemy createBulletEnemy(int x,int y){
        return new BulletEnemy(new Model(x,y,WIDTH,HIGHT),new View(Util.loadImage("resources/enemy_bullet.png")));
    }

    @Override
    public void onContact(Body other) {
        if(other instanceof PlaneController){
            this.model.setAlive(false);
        }

    }
    //làm sao để k viết lại timecout (Dùng Class)
}
