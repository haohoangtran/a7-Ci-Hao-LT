import controller.*;
import controller.managers.EnemyControllerManager;
import utils.Util;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.TimerTask;
import java.util.Vector;
import java.util.Timer;

import static utils.Util.loadImage;

/**
 * Created by tranh on 05-Dec-16.
 */
public class GameWindow extends Frame implements Runnable {
    KeySetting keySetting;
    PlaneController planeController;
    Image background;
    BufferedImage bufferedImage;
    Vector<BulletController> bulletControllerVector;
    Vector<BulletEnemy> bulletEnemyVector;
    EnemyControllerManager enemyControllerManager;

    public GameWindow() {
        setVisible(true);
        bulletControllerVector = new Vector<>();
        background = loadImage("resources/background.png");
        setSize(800, 600);
        setResizable(false);
        enemyControllerManager = new EnemyControllerManager();
        bulletControllerVector = new Vector<>();
        bulletEnemyVector = new Vector<>();
        planeController = PlaneController.createPlane(300, 550);
        keySetting = new KeySetting(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
        bufferedImage = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        planeController.setKeySetting(keySetting);


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
                if (planeController.getModel().getY() + 30 > 600) {
                    planeController.getModel().move(0, -5);
                }
                if (planeController.getModel().getY() - 65 < 0) {
                    planeController.getModel().move(0, 5);
                }
                if (planeController.getModel().getX() < 40) {
                    planeController.getModel().move(5, 0);
                }
                if (planeController.getModel().getX() > 800 - 40) {
                    planeController.getModel().move(-5, 0);
                }
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    bulletControllerVector.add(BulletController.createBulletController(planeController.getModel().getMidX(), planeController.getModel().getY() - 32));
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

    }


    @Override
    public void update(Graphics g) {
        Graphics backBufferGraphic = bufferedImage.getGraphics();
        backBufferGraphic.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);
        planeController.draw(backBufferGraphic);
        if (bulletControllerVector.size() != 0)
            for (BulletController bulletController : bulletControllerVector) {
                bulletController.draw(backBufferGraphic);
            }
        enemyControllerManager.draw(backBufferGraphic);

        g.drawImage(bufferedImage, 0, 0, this.getWidth(), this.getHeight(), null);
    }


    @Override
    public void run() {
        while (true) {
            try {


                this.repaint();
                Thread.sleep(17);
                for (int i = 0; i < bulletControllerVector.size(); i++) {
                    bulletControllerVector.get(i).run();
                }
                for (int i = 0; i < bulletControllerVector.size(); i++) {
                    if (bulletControllerVector.get(i).getModel().getY() < 0) {
                        bulletControllerVector.remove(i);
                    }
                }
                for (BulletEnemy bulletEnemy : bulletEnemyVector) {
                    bulletEnemy.run();
                }
                for (int i = 0; i < bulletEnemyVector.size(); i++) {
                    if (bulletEnemyVector.get(i).getModel().getY() > 600)
                        bulletEnemyVector.remove(i);
                }
                /*
                if (bulletControllerVector.size() != 0)
                    for (int i = 0; i < bulletControllerVector.size(); i++) {
                        for (int j = 0; j < enemyControllerVector.size(); j++) {
                            int bulletY=bulletControllerVector.get(i).getModel().getY();
                            int enemyY=enemyControllerVector.get(j).getModel().getY();
                            int bulletX=bulletControllerVector.get(i).getModel().getX();
                            int enemyX=enemyControllerVector.get(j).getModel().getX();
                            if (bulletY>enemyY&&bulletY<enemyY+50&&bulletX+13>enemyX&&bulletX<enemyX+70){
                                bulletControllerVector.remove(i);
                                enemyControllerVector.remove(j);
                                break;
                                //chỉ có 1 đạn băn vào 1 máy bay
                            }
                        }

                    }*/
                enemyControllerManager.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}