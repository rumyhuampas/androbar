package andro.bar.controllers;

import andro.bar.R;

public class Welcome extends andro.bar.controllers.Base {

    private andro.bar.Welcome Activity;
    private andro.bar.views.Welcome view;
    private andro.bar.models.Welcome model;

    public Welcome(andro.bar.Welcome activity) {
        Activity = activity;
        view = new andro.bar.views.Welcome(activity);
        model = new andro.bar.models.Welcome();
    }
}
