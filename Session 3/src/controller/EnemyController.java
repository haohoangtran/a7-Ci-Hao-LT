package controller;

import models.Model;
import utils.Util;
import views.View;

import java.util.Random;

/**
 * Created by tranh on 08-Dec-16.
 */
public class EnemyController extends Controller {


    public EnemyController(Model model, View view) {
        super(model, view);
    }

    public static EnemyController createEnemy(int x, int y) {
        return new EnemyController(new Model(x, y, 70, 50), new View(Util.loadImage("resources/enemy_plane_white_1.png")));
    }
    public void run() {
        this.model.move(0,5);
    }
}
//máy bay tự do di chuyển sang trái

