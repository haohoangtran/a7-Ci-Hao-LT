package controller;

import models.PlaneModel;
import views.PlaneView;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by tranh on 05-Dec-16.
 */
public class PlaneController {
    private PlaneModel planeModel;
    private PlaneView planeView;
    private KeySetting keySetting;

    public PlaneController(PlaneModel planeModel, PlaneView planeView, KeySetting keySetting) {
        this.planeModel = planeModel;
        this.planeView = planeView;
        this.keySetting = keySetting;
    }

    public PlaneModel getPlaneModel() {
        return planeModel;
    }

    public void setPlaneModel(PlaneModel planeModel) {
        this.planeModel = planeModel;
    }

    public PlaneView getPlaneView() {
        return planeView;
    }

    public void setPlaneView(PlaneView planeView) {
        this.planeView = planeView;
    }

    public KeySetting getKeySetting() {
        return keySetting;
    }

    public void setKeySetting(KeySetting keySetting) {
        this.keySetting = keySetting;
    }

    public void keyPress(KeyEvent e) {
        if (keySetting != null) {
            if (e.getKeyCode() == keySetting.getKeyUp()) {
                planeModel.move(0, -5);
            }
            if (e.getKeyCode() == keySetting.getKeyDown()) {
                planeModel.move(0, 5);
            }
            if (e.getKeyCode() == keySetting.getKeyLeft()) {
                planeModel.move(-5, 0);
            }
            if (e.getKeyCode() == keySetting.getKeyRight()) {
                planeModel.move(5, 0);
            }
        }
    }

    public void draw(Graphics g) {
        planeView.draw(g, planeModel);
    }
}
