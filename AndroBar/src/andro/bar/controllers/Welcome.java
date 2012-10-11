package andro.bar.controllers;

import Database.MySQL;
import andro.bar.wrappers.AndroThread;
import andro.bar.wrappers.ExtraObject;
import andro.bar.wrappers.dialogs.ImageDialog;
import andro.bar.wrappers.dialogs.YesNoDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import java.sql.SQLException;

public class Welcome extends andro.bar.controllers.Base {

    private andro.bar.Welcome Activity;
    private andro.bar.views.Welcome view;
    private andro.bar.models.Welcome model;
    public static MySQL mysql;

    public Welcome(andro.bar.Welcome activity) {
        Activity = activity;
        view = new andro.bar.views.Welcome(activity);
        model = new andro.bar.models.Welcome();
        
        mysql = new MySQL("10.0.2.2:3306", "androbar", "root", "");
        
        andro.bar.controllers.Base.RunActivity(Activity, andro.bar.Categories.class, null);
    }
}
