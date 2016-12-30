package views;

import models.Model;

import java.awt.*;

/**
 * Created by DUC THANG on 12/24/2016.
 */
public class SingleView implements View {
    private Image image;

    public SingleView(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public void draw(Graphics g, Model model) {
        g.drawImage(image, model.getX(), model.getY(), model.getWidth(), model.getHeight(), null);
    }
}
