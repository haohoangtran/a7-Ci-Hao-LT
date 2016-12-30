package controllers.managers;

import controllers.*;
import models.Model;

import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by DUC THANG on 12/16/2016.
 */
public class BodyManager implements BaseController{
    private Vector<Body> bodyVector;

    public static  BodyManager instance = new BodyManager();

    private BodyManager() {
        bodyVector = new Vector<>();
    }

    public void register(Body body) {
        this.bodyVector.add(body);
    }
    public void run() {
        Iterator <Body> iterator = bodyVector.iterator();
        while(iterator.hasNext()) {
            Body body = iterator.next();
            if(!body.getModel().isAlive() || body.getModel().getY() > GameSetting.instance.getHeight()) {
                iterator.remove();
            }
        }

        for(int i = 0; i < bodyVector.size() - 1; i++) {
            for(int j = i + 1; j < bodyVector.size(); j++) {
                Body bodyi = bodyVector.get(i);
                Body bodyj = bodyVector.get(j);
                Model modeli = bodyi.getModel();
                Model modelj = bodyj.getModel();
                
                if(modeli.intersects(modelj)) {
                    bodyi.onContact(bodyj);
                    bodyj.onContact(bodyi);
                }
            }
        }
    }

    @Override
    public void draw(Graphics g) {

    }

    public Vector<Body> getBodyVector() {
        return bodyVector;
    }
}
