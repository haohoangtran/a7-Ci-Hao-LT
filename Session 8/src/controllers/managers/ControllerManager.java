package controllers.managers;

import controllers.BaseController;
import controllers.Controller;
import controllers.GameSetting;

import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by DUC THANG on 12/16/2016.
 */
public class ControllerManager implements BaseController{
    protected Vector <Controller> controllers;
    public static  ControllerManager explosion = new ControllerManager();
    public static  ControllerManager enemyBullet = new ControllerManager();
    public static  ControllerManager bomb = new ControllerManager();

    public ControllerManager() {
        controllers = new Vector<>();
    }

    public Vector<Controller> getControllers() {
        return controllers;
    }

    public void draw(Graphics g) {
        for (Controller controller : controllers) {
            controller.draw(g);
        }
    }

    public void run() {
        for (Controller controller : controllers) {
            controller.run();
        }

        Iterator <Controller> iterator = this.controllers.iterator();
        while(iterator.hasNext()) {
            Controller controller = iterator.next();
            if(!controller.getModel().isAlive() || !GameSetting.instance.isInScreen(controller)) {
                iterator.remove();
            }
        }
    }

    public void add(Controller controller) {
        controllers.add(controller);
    }

    public void remove() {
        for (int i = 0; i < controllers.size(); i++) {
            if(controllers.get(i).getModel().getY() > GameSetting.instance.getHeight() || !controllers.get(i).getModel().isAlive()) {
                controllers.remove(controllers.get(i));
            }
        }
    }
}
