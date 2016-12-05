import controller.BulletController;
import controller.KeySetting;
import controller.PlaneController;
import models.BulletModel;
import models.PlaneModel;
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
 * Created by tranh on 05-Dec-16.
 */
public class GameWindow extends Frame implements Runnable {
    KeySetting keySetting;
    PlaneModel planeModel;
    PlaneView planeView;
    PlaneController planeController;
    Image background;
    BufferedImage bufferedImage;
    Vector<BulletController> bulletControllerVector;
    BulletModel bulletModel;
    BulletView bulletView;

    public GameWindow() {
        setVisible(true);
        background = loadImage("resources/background.png");
        setSize(800, 600);
        bulletControllerVector = new Vector<>();
        keySetting = new KeySetting(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
        planeModel = new PlaneModel(200, 550);
        planeView = new PlaneView(loadImage("resources/plane3.png"));
        planeController = new PlaneController(planeModel, planeView, keySetting);
        bufferedImage = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {

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

            }

            @Override
            public void keyPressed(KeyEvent e) {
                planeController.keyPress(e);
                if (planeController.getPlaneModel().getY() + 35 > 600) {
                    planeController.getPlaneModel().move(0, -5);
                }
                if (planeController.getPlaneModel().getY() - 70 < 0) {
                    planeController.getPlaneModel().move(0, 5);
                }
                if (planeController.getPlaneModel().getX() < 45) {
                    planeController.getPlaneModel().move(5, 0);
                }
                if (planeController.getPlaneModel().getX() > 800 - 45) {
                    planeController.getPlaneModel().move(-5, 0);
                }
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    bulletModel = new BulletModel(planeController.getPlaneModel().getX() + 35, planeController.getPlaneModel().getY() - 50);
                    bulletView = new BulletView(loadImage("resources/bullet.png"));
                    bulletControllerVector.add(new BulletController(bulletModel, bulletView));
                    System.out.println("Số đạn = " + bulletControllerVector.size());
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

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

    @Override
    public void update(Graphics g) {
        Graphics backBufferGraphic = bufferedImage.getGraphics();
        backBufferGraphic.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);
        planeController.draw(backBufferGraphic);
        for (BulletController bulletController : bulletControllerVector) {
            bulletController.draw(backBufferGraphic);

        }

        g.drawImage(bufferedImage, 0, 0, this.getWidth(), this.getHeight(), null);
    }

    @Override
    public void run() {
        while (true) {
            try {

                this.repaint();
                Thread.sleep(17);
                for (int i = 0; i < bulletControllerVector.size(); i++) {
                    bulletControllerVector.get(i).getBulletModel().move(0, -5);
                    if (bulletControllerVector.get(i).getBulletModel().getY() < 0) {
                        bulletControllerVector.remove(i);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
