package models;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by tranh on 07-Dec-16.
 */
public class Model {
    protected int x;
    protected int y;
    protected int wight;

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    protected int hight;
    protected  boolean isAlive=true;

    public int getWight() {
        return wight;
    }

    public void setWight(int wight) {
        this.wight = wight;
    }

    public int getHight() {
        return hight;
    }

    public void setHight(int hight) {
        this.hight = hight;
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

    public Model(int x, int y, int wight, int hight) {
        this.x = x;
        this.y = y;
        this.wight = wight;
        this.hight = hight;
    }

    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    public int getMidX() {
        return this.x + this.wight / 2 - 6;
    }

    public int getBottom() {
        return this.y + this.hight;
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, wight, hight);
    }

    public boolean intersects(Model other) {
        Rectangle rec1 = this.getRect();
        Rectangle rec2 = other.getRect();
        return rec1.intersects(rec2);
    }
}
