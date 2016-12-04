import controllers.BulletController;
import controllers.KeySetting;
import controllers.PlaneController;
import model.BulletModel;
import model.PlaneModel;
import views.BulletView;
import views.PlaneView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

/**
 * Created by tranh on 30-Nov-16.
 */
public class GameWindow extends Frame implements Runnable {
    Image background;
    PlaneController planeController;
    KeySetting keySetting;
    PlaneModel planeModel;
    PlaneView planeView;
    BufferedImage backBuffer;
    BulletController bulletController;
    Vector<BulletController> bulletControllerVector;

    public GameWindow() {
        keySetting = new KeySetting(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
        planeModel = new PlaneModel(300, 300);
        planeView = new PlaneView(loadImage("resources/plane3.png"));
        planeController = new PlaneController(planeModel, planeView);
        planeController.setKeySetting(keySetting);
        bulletControllerVector=new Vector<>();

        background = loadImage("resources/background.png");

        setVisible(true);
        setSize(800, 600);
        backBuffer = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                System.out.println("window Opened!");
            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("windowClosing");
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {
                System.out.println("windowClosed");
            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println("keyTyped");
            }

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("keyPressed");

                planeController.keyPress(e);
                if ((e.getKeyCode() == KeyEvent.VK_SPACE)) {
                    int bulletX = planeController.getPlaneModel().getX() + 30;
                    int bulletY = planeController.getPlaneModel().getY() - 15;
                    BulletView bulletView = new BulletView(loadImage("resources/bullet.png"));
                    BulletModel bulletModel=new BulletModel(bulletX,bulletY);
                    bulletControllerVector.add(new BulletController(bulletModel,bulletView));
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println("keyReleased");
            }
        });
    }

    @Override
    public void update(Graphics g) {
        Graphics backBufferGraphic = backBuffer.getGraphics();
        backBufferGraphic.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);
        planeController.draw(backBufferGraphic);
        for (BulletController bulletController: bulletControllerVector) {
            bulletController.draw(backBufferGraphic);
        }
        g.drawImage(backBuffer, 0, 0, this.getWidth(), this.getHeight(), null);
    }

    @Override
    public void run() {
        while (true) {
            try {

                this.repaint();
                Thread.sleep(17);
                for (int i = 0; i < bulletControllerVector.size(); i++) {
                    bulletControllerVector.get(i).getBulletModel().move(0,-5);
                    if (bulletControllerVector.get(i).getBulletModel().getY() < 0) {
                        bulletControllerVector.remove(i);
                    }
                    System.out.println("size bulletControllerVector = " + bulletControllerVector.size());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Image loadImage(String path) {
        try {
            Image image = ImageIO.read(new File(path));
            return image;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
