package controller;

import models.Model;
import utils.Util;
import views.PlaneView;
import views.View;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by tranh on 05-Dec-16.
 */
public class PlaneController extends Controller implements Body{
    private static final int SPEED = 5;

    public static int getSPEED() {
        return SPEED;
    }

    public KeySetting getKeySetting() {
        return keySetting;
    }

    public void setKeySetting(KeySetting keySetting) {
        this.keySetting = keySetting;
    }

    private KeySetting keySetting;

    public PlaneController(Model model, View view) {
        super(model, view);

    }

    //Design pattern: Factory
    public static PlaneController createPlane(int x, int y) {
        return new PlaneController(new Model(x, y, 70, 50), new View(Util.loadImage("resources/plane3.png")));
    }

    public void keyPress(KeyEvent e) {
        if (keySetting != null) {
            if (e.getKeyCode() == keySetting.getKeyUp()) {
                model.move(0, -SPEED);
            }
            if (e.getKeyCode() == keySetting.getKeyDown()) {
                model.move(0, SPEED);
            }
            if (e.getKeyCode() == keySetting.getKeyLeft()) {
                model.move(-SPEED, 0);
            }
            if (e.getKeyCode() == keySetting.getKeyRight()) {
                model.move(SPEED, 0);
            }
        }
    }

    @Override
    public void onContact(Body other) {
        System.out.println("huhu ");
    }
}
