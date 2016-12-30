import controllers.*;
import controllers.scenes.GameScene;
import controllers.scenes.MenuScene;
import controllers.scenes.SceneListener;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.util.Stack;


/**
 * Created by DUC THANG on 12/16/2016.
 */

public class GameWindow extends Frame implements Runnable, SceneListener{
    BufferedImage backBuffer;
    GameSetting gameSetting;

    GameScene currenScene;

    Stack <GameScene> gameSceneStack;

    public GameWindow() {
        gameSceneStack = new Stack<>();
        this.replaceScene(new MenuScene(), false);
        configSettings();
        setVisible(true);
        setSize(gameSetting.getWidth(), gameSetting.getHeight());
        backBuffer = new BufferedImage(gameSetting.getWidth(), gameSetting.getHeight(), BufferedImage.TYPE_3BYTE_BGR);

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
                currenScene.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        repaint();
    }

    public void replaceScene(GameScene newScene, boolean addToBackStack) {
        if(addToBackStack && currenScene != null) {
            gameSceneStack.push(currenScene);
        }
        currenScene = newScene;
        currenScene.setSceneListener(this);
    }

    public void back() {
        if(!gameSceneStack.isEmpty()) {
            currenScene = gameSceneStack.pop();
        }
    }

    private void configSettings() {
        PlaneController.instance.keySetting = new KeySetting(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_SPACE);
        gameSetting = GameSetting.instance;
    }


    public void update(Graphics g) {
        Graphics backBufferGraphics = backBuffer.getGraphics();
        currenScene.update(backBufferGraphics);
        g.drawImage(backBuffer, 0, 0, gameSetting.getWidth(), gameSetting.getHeight(), null);
    }

    @Override
    public void run() {
        while(true) {
            this.repaint();
            try {
                Thread.sleep(17);
                currenScene.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
