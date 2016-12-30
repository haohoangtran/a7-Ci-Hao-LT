package controllers.scenes;

import utils.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by tranh on 25/12/2016.
 */
public class FinalScene extends GameScene {
    Image background;
    boolean checkDraw=false;
    int timeCount=0;

    public FinalScene() {
        background= Utils.loadImage("resources/over.png");
    }

    @Override
    public void update(Graphics g) {

        g.drawImage(background, 350, 250, null);
        if (!checkDraw){
            g.setColor(Color.red);
            g.setFont(new Font("Time new Romans",Font.BOLD,20));
            g.drawString("Press any key to Menu!",300,500);
        }

        if (timeCount%50==0){
            checkDraw=false;
        }
        if (timeCount%90==0){
            checkDraw=true;
        }
        timeCount++;
    }

    @Override
    public void run() {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        this.sceneListener.replaceScene(new MenuScene(),false);
    }

    @Override
    public void gameEnd() {

    }
}
