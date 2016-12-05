package views;

import com.sun.org.apache.xpath.internal.operations.Gt;
import models.PlaneModel;

import java.awt.*;

/**
 * Created by tranh on 05-Dec-16.
 */
public class PlaneView {
    private Image image;

    public PlaneView(Image image) {
        this.image = image;
    }

    public void draw(Graphics g, PlaneModel planeModel) {
        g.drawImage(this.image, planeModel.getX() - 35, planeModel.getY() - 25, 70, 50, null);
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
