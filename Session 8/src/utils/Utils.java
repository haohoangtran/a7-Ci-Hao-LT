package utils;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

/**
 * Created by DUC THANG on 12/16/2016.
 */
public class Utils {
    private static int timeCounter;

    public Utils() {
        timeCounter = 0;
    }

    public static BufferedImage loadImage(String path) {
        try {
            BufferedImage image = ImageIO.read(new File(path));
            return image;
        } catch (IOException e) {
            System.out.println("Load Image Failed!!!");
            e.printStackTrace();
        }
        return null;
    }

    public static int getTimeCounter() {
        timeCounter++;
        return timeCounter;
    }

    public static void setTimeCounter(int x) {
        timeCounter = x;
    }

    public static void playSound(String audioUrl, boolean repeat) {

        File soundFile = new File(audioUrl);
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
            if(repeat) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
            else {
                clip.loop(0);
            }
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static Vector <BufferedImage> loadSheet(String path, int width, int height, int border, int imageCount) {
        BufferedImage bufferedImage = Utils.loadImage(path);
        Vector <BufferedImage> bufferedImages = new Vector<>();
        for(int index = 0; index < imageCount; index++) {
            int x = index * width + border +(index*border);
            int y = border;
            BufferedImage subImage = bufferedImage.getSubimage(x, y, width, height);
            bufferedImages.add(subImage);
        }

        return bufferedImages;
    }
}
