package controllers;

import models.Model;

/**
 * Created by DUC THANG on 12/16/2016.
 */
public interface Body {
    Model getModel();
    void onContact(Body other);
}
