package controllers.scenes;

import controllers.BaseController;
import controllers.GameSetting;
import controllers.PlaneController;
import controllers.managers.BodyManager;
import controllers.managers.ControllerManager;
import controllers.managers.EnemyControllerManager;
import utils.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Vector;

import static utils.Utils.loadImage;

/**
 * Created by DUC THANG on 12/28/2016.
 */
public class PlayGameScene extends GameScene{
    Image background;

    Vector<BaseController> controllers;

    public PlayGameScene() {
        controllers = new Vector<>();
        PlaneController.instance.setLives(3);
        PlaneController.instance.setHp(3);
        ControllerManager.explosion=new ControllerManager();
        ControllerManager.bomb=new ControllerManager();
        ControllerManager.enemyBullet=new ControllerManager();
        controllers.add(new EnemyControllerManager());
        controllers.add(ControllerManager.explosion);

        controllers.add(PlaneController.instance);
        controllers.add(BodyManager.instance);
        controllers.add(ControllerManager.enemyBullet);
        controllers.add(ControllerManager.bomb);

        background = loadImage("resources/background.png");
    }
    @Override
    public void update(Graphics g) {
        g.drawImage(background, 0, 0, GameSetting.instance.getWidth(), GameSetting.instance.getHeight(), null);
        for(BaseController baseController : this.controllers) {
            baseController.draw(g);
        }
    }

    @Override
    public void run() {
        for (BaseController controller : controllers) {
            controller.run();
        }
        if (PlaneController.instance.getLives()<0&&PlaneController.instance.getHp()<=0){
            gameEnd();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        PlaneController.instance.keyPressed(e);
    }

    @Override
    public void gameEnd() {
        this.sceneListener.replaceScene(new FinalScene(),false);

        Utils.playSound("resources/over.wav",false);
    }
}
