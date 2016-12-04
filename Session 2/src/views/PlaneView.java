package views;

import model.PlaneModel;

import java.awt.*;

/**
 * Created by tranh on 03-Dec-16.
 */
public class PlaneView {
    private Image image;
    public void draw(Graphics g, PlaneModel planeModel){
        g.drawImage(image,planeModel.getX(),planeModel.getY(),70,50,null);
    }

    public PlaneView(Image image) {
        this.image = image;
    }
}
