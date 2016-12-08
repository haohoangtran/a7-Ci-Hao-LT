package controller;

import models.Model;
import utils.Util;
import views.View;

/**
 * Created by tranh on 08-Dec-16.
 */
public class BulletEnemy extends Controller {
    public BulletEnemy(Model model, View view) {
        super(model, view);
    }
    public void run(){
        this.model.move(0,2);
    }
    public static BulletEnemy createBulletEnemy(int x,int y){
        return new BulletEnemy(new Model(x,y,32,32),new View(Util.loadImage("resources/enemy_bullet.png")));
    }
}
