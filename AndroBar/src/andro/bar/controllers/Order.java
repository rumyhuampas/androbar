package andro.bar.controllers;

import android.os.Bundle;

public class Order extends andro.bar.controllers.Base {

    private andro.bar.Order Activity;
    //private andro.bar.views.Order view;
    //private andro.bar.models.Order model;
    private Bundle extras;
    //private Category cat;

    public Order(andro.bar.Order activity) {
        Activity = activity;
        //view = new andro.bar.views.Order(activity);
        //model = new andro.bar.models.Order();
        
        //view.DrawToolBar();

        extras = Activity.getIntent().getExtras();
    }
    
}
