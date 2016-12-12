package utils;

import controller.BulletController;
import controller.BulletEnemy;
import controller.EnemyController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;

/**
 * Created by tranh on 07-Dec-16.
 */
public class Util {
    public static Image loadImage(String path) {
        try {
            Image image = ImageIO.read(new File(path));
            return image;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int randomNumber() {
        Random random = new Random();
        random.nextInt();
        return Math.abs(random.nextInt()%750 )+40;
    }
}
