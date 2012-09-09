package andro.bar.controllers;

import Database.MySQL;
import andro.bar.R;

public class Welcome extends andro.bar.controllers.Base {

    private andro.bar.Welcome Activity;
    private andro.bar.views.Welcome view;
    private andro.bar.models.Welcome model;
    public static MySQL mysql;

    public Welcome(andro.bar.Welcome activity) {
        Activity = activity;
        view = new andro.bar.views.Welcome(activity);
        model = new andro.bar.models.Welcome();
        
        mysql = new MySQL("localhost", "androbar", "root", "");
    }
}
