package models;

import controllers.GameVector;

import java.awt.*;

/**
 * Created by DUC THANG on 12/16/2016.
 */
public class Model {
    private int x;
    private int y;
    private int width;
    private int height;
    private int timeCounter;
    private boolean isAlive = true;

    public Model(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        timeCounter = 0;
    }

    public void move(GameVector gameVector) {
        this.move(gameVector.dx, gameVector.dy);
    }

    public GameVector subtract(Model model) {
        return new GameVector(this.x - model.x, this.y - model.y);
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }

    public int getMidX() {
        return this.x + width / 2;
    }

    public int getMidY() {
        return this.y + height;
    }

    public int getTimeCounter() {
        timeCounter++;
        return timeCounter;
    }

    public void setTimeCounter(int timeCounter) {
        this.timeCounter = timeCounter;
    }
    public Rectangle getRect() {
        return new Rectangle(x, y, width, height);
    }

    public boolean intersects(Model other) {
        Rectangle rect1 = this.getRect();
        Rectangle rect2 = other.getRect();
        return rect1.intersects(rect2);
    }
}
