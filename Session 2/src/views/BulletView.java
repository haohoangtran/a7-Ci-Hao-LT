package views;

import models.BulletModel;

import java.awt.*;

/**
 * Created by tranh on 05-Dec-16.
 */
public class BulletView {
    Image image;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public BulletView(Image image) {

        this.image = image;
    }

    public void draw(Graphics g, BulletModel bulletModel) {
        g.drawImage(image, bulletModel.getX() - 35 - 6, bulletModel.getY(), 13, 32, null);
    }
}
