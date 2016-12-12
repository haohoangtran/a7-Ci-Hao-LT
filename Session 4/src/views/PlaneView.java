package views;

import models.Model;

import java.awt.*;

/**
 * Created by tranh on 05-Dec-16.
 */
public class PlaneView {
    private Image image;

    public PlaneView(Image image) {
        this.image = image;
    }

    public void draw(Graphics g, Model model) {
        g.drawImage(this.image, model.getX() - 35, model.getY() - 25, 70, 50, null);
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
