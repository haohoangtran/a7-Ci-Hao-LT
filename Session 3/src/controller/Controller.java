package controller;

import models.Model;
import views.View;

import java.awt.*;

/**
 * Created by tranh on 07-Dec-16.
 */
public class Controller {
    protected View view;

    protected Model model;

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Controller(Model model, View view) {

        this.model = model;
        this.view = view;
    }

    public void draw(Graphics g){
        view.draw(g,model);

    }
    public void run(){


    }
}
