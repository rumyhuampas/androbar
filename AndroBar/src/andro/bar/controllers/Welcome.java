package andro.bar.controllers;

import Database.MySQL;
import andro.bar.wrappers.OrderList;
import andro.bar.wrappers.dialogs.ListDialog;
import andro.bar.wrappers.dialogs.TxtDialog;
import android.view.View;

public class Welcome extends andro.bar.controllers.Base {

    private andro.bar.Welcome Activity;
    private andro.bar.views.Welcome view;
    private andro.bar.models.Welcome model;
    public static MySQL mysql;
    public static OrderList MainList;

    public Welcome(andro.bar.Welcome activity) {
        Activity = activity;
        view = new andro.bar.views.Welcome(activity);
        model = new andro.bar.models.Welcome();

        //mysql = new MySQL("10.0.2.2:3306", "androbar", "root", "");
        //mysql = new MySQL("192.168.1.103:3306", "androbar", "root", "");
        ShowConnDialog();
    }

    private void ShowConnDialog() {
        final TxtDialog dialog = view.CreateTxtMessage(Activity, "Ingrese IP del servidor", "10.0.2.2");
        dialog.SetCallback(new View.OnClickListener() {

            public void onClick(View view) {
                mysql = new MySQL(dialog.GetText() + ":3306", "androbar", "root", "");

                MainList = new OrderList();
                MainList.Clear();

                andro.bar.controllers.Base.RunActivity(Activity, andro.bar.Categories.class, null);
            }
        });
        dialog.show();
    }
}
