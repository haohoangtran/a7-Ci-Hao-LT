package controllers.scenes;

import utils.Utils;

import java.awt.*;
import java.awt.event.KeyEvent;

import static utils.Utils.loadImage;

/**
 * Created by DUC THANG on 12/28/2016.
 */
public class MenuScene extends GameScene{
    Image background;
    boolean checkDraw=false;
    int timeCount=0;

    public MenuScene() {
        background = loadImage("resources/1945-logo.png");
    }

    @Override
    public void update(Graphics g) {

        g.drawImage(background, 0, 0, 800, 600, null);
        if (!checkDraw){
            g.setColor(Color.red);
            g.setFont(new Font("Time new Romans",Font.BOLD,20));
            System.out.println("vẽ");
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
        this.sceneListener.replaceScene(new PlayGameScene(), true);
        Utils.playSound("resources/cutra.wav",false);
    }

    @Override
    public void gameEnd() {
    }
}
