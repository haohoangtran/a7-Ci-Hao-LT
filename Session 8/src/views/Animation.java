package views;

import models.Model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Vector;

/**
 * Created by DUC THANG on 12/24/2016.
 */
public class Animation implements View {
    private Vector <BufferedImage> bufferedImages;
    private int imageCount;
    private int count;
    private  boolean animationReachEnd = false;

    public boolean isAnimationReachEnd() {
        return animationReachEnd;
    }

    public Animation(Vector<BufferedImage> bufferedImages) {
        this.bufferedImages = bufferedImages;
    }

    @Override
    public void draw(Graphics g, Model model) {

        BufferedImage image = bufferedImages.get(imageCount);
        g.drawImage(image, model.getX(), model.getY(), model.getWidth(), model.getHeight(),null);

        count++;
        if(count > 10) {
            count = 0;
            imageCount++;

            if(imageCount >= bufferedImages.size()) {
                animationReachEnd = true;
                imageCount = 0;
            }
        }
    }
}
