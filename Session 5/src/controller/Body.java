package controller;

import models.Model;

/**
 * Created by tranh on 12/14/2016.
 */
public interface Body {
     Model getModel();
     void onContact(Body other);
}
