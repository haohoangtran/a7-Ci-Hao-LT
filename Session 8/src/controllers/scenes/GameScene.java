package controllers.scenes;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by DUC THANG on 12/28/2016.
 */
public abstract class GameScene {
    protected SceneListener sceneListener;

    public void setSceneListener(SceneListener sceneListener) {
        this.sceneListener = sceneListener;
    }

    public abstract void update(Graphics g);
    public  abstract void run();
    public  abstract void keyPressed(KeyEvent e);
    public abstract void gameEnd();
}
