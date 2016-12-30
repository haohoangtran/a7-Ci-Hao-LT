package controllers;

/**
 * Created by DUC THANG on 12/16/2016.
 */
public class KeySetting {
    public int keyUp;
    public int keyDown;
    public int keyLeft;
    public int keyRight;
    public int keyShoot;

    public KeySetting(int keyUp, int keyDown, int keyLeft, int keyRight, int keyShoot) {
        this.keyUp = keyUp;
        this.keyDown = keyDown;
        this.keyLeft = keyLeft;
        this.keyRight = keyRight;
        this.keyShoot = keyShoot;
    }
}
