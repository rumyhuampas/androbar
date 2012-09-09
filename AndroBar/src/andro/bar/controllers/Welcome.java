package andro.bar.controllers;

import Database.MySQL;
import andro.bar.wrappers.AndroThread;
import andro.bar.wrappers.ExtraObject;
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
        
        mysql = new MySQL("localhost", "androbar", "root", "");
        
            //view.CreateErrorMessage(Activity, MySQL.class.getName());
            //Activity.finish();
        LoadCategories();
    }
    
    public void LoadCategories(){
        AndroThread thread = new AndroThread(mysql, model, "LoadCategories", null, null, Object[].class, null, LoadCategoriesHandler, ExceptionHandler);
        thread.Start();
    }
    
    private Handler LoadCategoriesHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Object[] message = (Object[]) msg.obj;
            Object[] categories = (Object[])message[0];
            ExtraObject extraCategories = new ExtraObject(categories);
            Bundle extras = new Bundle();
            extras.putParcelable("categories", extraCategories);
            andro.bar.controllers.Base.RunActivity(Activity, andro.bar.Categories.class, extras);
        }
    };
    private Handler ExceptionHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            YesNoDialog dialog = view.CreateYesNoMessage(Activity, "Error", "Un problema ocurrió al iniciar el programa.\n\ns "
                    + ((SQLException) msg.obj).getMessage()
                    + "\n\n¿Desea ir a la ventana de configuración?");
            dialog.SetCallback(new View.OnClickListener() {

                public void onClick(View v) {
                    if (((Button) v).getText() == YesNoDialog.BUTTON_YES) {
                        Bundle extras = new Bundle();
                        extras.putString("activity", "WELCOME");
                        //andro.bar.controllers.Base.RunActivity(Activity, andro.bar.Settings.class, extras);
                    } else {
                        Activity.finish();
                    }
                }
            });
            dialog.show();
        }
    };
}
