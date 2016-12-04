package controllers;

import model.BulletModel;
import views.BulletView;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by tranh on 04-Dec-16.
 */
public class BulletController {
    private BulletModel bulletModel;
    private BulletView bulletView;

    public BulletModel getBulletModel() {
        return bulletModel;
    }

    public void setBulletModel(BulletModel bulletModel) {
        this.bulletModel = bulletModel;
    }

    public BulletView getBulletView() {
        return bulletView;
    }

    public void setBulletView(BulletView bulletView) {
        this.bulletView = bulletView;
    }


    public BulletController(BulletModel bulletModel, BulletView bulletView) {
        this.bulletModel = bulletModel;
        this.bulletView = bulletView;

    }
    public void keyPress(KeyEvent e){
            bulletModel.move(0,-5);
    }
    public void draw(Graphics g){
        bulletView.draw(g,bulletModel);
    }
}
