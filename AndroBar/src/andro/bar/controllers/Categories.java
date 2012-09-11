package andro.bar.controllers;

import andro.bar.wrappers.ExtraObject;
import android.os.Bundle;

public class Categories extends andro.bar.controllers.Base {

    private andro.bar.Categories Activity;
    private andro.bar.views.Categories view;
    //private andro.bar.models.Categories model;
    
    private Bundle extras;

    public Categories(andro.bar.Categories activity) {
        Activity = activity;
        view = new andro.bar.views.Categories(activity);
        //model = new andro.bar.models.Categories();
        
        extras = Activity.getIntent().getExtras();
        GetExtras();
    }
    
    private void GetExtras(){
        ExtraObject extraObj = (ExtraObject) extras.getParcelable("categories");
        view.DrawCategories((Object[])extraObj.Obj, null, null);
    }
}
