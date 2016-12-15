package controller.managers;

import controller.Controller;

import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by tranh on 10-Dec-16.
 */
public class ControllerManager {
    protected Vector<Controller> controllers;

    public ControllerManager() {
        controllers=new Vector<>();
    }
    public void draw(Graphics g){
        if (controllers.size()!=0)
        for (Controller controller : controllers) {
            controller.draw(g);
        }
    }
    public void run(){
        for (Controller controller : controllers) {
            controller.run();
        }
        Iterator<Controller>  iterator=this.controllers.iterator();
        while (iterator.hasNext()){
            Controller controller=iterator.next();
            if (!controller.getModel().isAlive()){
                iterator.remove();
            }
        }
    }
    public void add(Controller c){
        this.controllers.add(c);
    }
    public void remove(Controller c){
        this.controllers.remove(c);
    }
}
