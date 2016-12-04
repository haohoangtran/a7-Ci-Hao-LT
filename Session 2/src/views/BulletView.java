package views;

import model.BulletModel;

import java.awt.*;

/**
 * Created by tranh on 04-Dec-16.
 */
public class BulletView {
    private Image image;

    public BulletView(Image image) {
        this.image = image;
    }

    public void draw(Graphics g, BulletModel bulletModel) {
        g.drawImage(image, bulletModel.getX(), bulletModel.getY(), 13, 33, null);
    }
}
