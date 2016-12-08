package views;

import models.Model;

import java.awt.*;

/**
 * Created by tranh on 07-Dec-16.
 */
public class View {
    protected Image image;

    public View(Image image) {
        this.image = image;
    }

    public void draw(Graphics g, Model model) {
        g.drawImage(this.image, model.getX() - model.getWight()/2, model.getY() - model.getHight()/2, model.getWight(), model.getHight(), null);
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
