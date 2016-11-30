import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

/**
 * Created by tranh on 01-Dec-16.
 */
public class Game extends Frame {
    Image background;
    ArrayList<Enemy> enemies = new ArrayList<>();
    Player player = new Player();


    @Override
    public void paint(Graphics g) {
        g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);
        //g.drawImage(player.getImage(), x*this.getWidth(),  y*this.getHeight(), null);
        g.drawImage(player.getImage(), player.getX(), player.getY(), 70, 50, null);
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).autoMove();
            if (enemies.get(i).getY() < 50) {
                enemies.get(i).setY(50);
            }
            g.drawImage(enemies.get(i).getImage(), enemies.get(i).getX(), enemies.get(i).getY(), 70, 50, null);

        }

    }

    public Game() {
        setVisible(true);
        setSize(800, 600);
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
        player.setX(400);
        player.setY(300);
        int w, h;

        try {
            background = ImageIO.read(new File("resources/background.png"));
            player.setImage("resources/plane3.png");
            repaint();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        w = this.getWidth();
        h = this.getHeight();
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println("keyTyped");
            }

            @Override
            public void keyPressed(KeyEvent e) {

                System.out.println("keyPressed");
                if (enemies.size() < 5) {
                    while (enemies.size() < 5) {

                        Enemy enemy = new Enemy();
                        Random random = new Random();
                        int rd = random.nextInt() % 2;
                        if (rd < 0) {
                            rd = -rd;
                        }
                        System.out.println(rd);
                        switch (rd) {
                            case 0:
                                enemy.setY(50);
                                enemy.setX(Math.abs(random.nextInt() % h));
                                try {
                                    enemy.setImage("resources/enemy_plane_yellow_1.png");
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                                break;
                            case 1:
                                enemy.setY(50);
                                enemy.setX(Math.abs(random.nextInt() % h));
                                try {
                                    enemy.setImage("resources/enemy_plane_white_1.png");
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                                break;
                        }
                        enemies.add(enemy);
                        for (int i = 0; i <enemies.size() ; i++) {
                            if(enemies.get(i).getY()>h-50 || enemies.get(i).getX()>h){
                                enemies.remove(i);
                            }
                        }
                        repaint();
                    }
                }
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        player.moveUp();
                        repaint();
                        break;
                    case KeyEvent.VK_DOWN:
                        player.moveDown();
                        repaint();
                        break;
                    case KeyEvent.VK_LEFT:
                        player.moveLeft();
                        repaint();
                        break;
                    case KeyEvent.VK_RIGHT:
                        player.moveRight();
                        repaint();
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

    }


}
