package andro.bar.controllers;

import andro.bar.wrappers.AndroThread;
import andro.bar.wrappers.dialogs.ImageDialog;
import andro.bar.wrappers.dialogs.ListDialog;
import andro.bar.wrappers.dialogs.LoadingDialog;
import andro.bar.wrappers.dialogs.TxtDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import java.util.ArrayList;

public class Order extends andro.bar.controllers.Base {

    private andro.bar.Order Activity;
    private andro.bar.views.Order view;
    private andro.bar.models.Order model;
    private Bundle extras;
    //private Category cat;

    public Order(andro.bar.Order activity) {
        Activity = activity;
        view = new andro.bar.views.Order(activity);
        model = new andro.bar.models.Order();

        view.DrawList();
        SetClickListeners();
    }

    private void SetClickListeners() {
        view.GetConfirmButton().setOnClickListener(BtnConfirmOnClickHandler);
    }
    public View.OnClickListener BtnConfirmOnClickHandler = new View.OnClickListener() {

        ListDialog dialog = null;

        public void onClick(View objView) {
            //RunActivity(Activity, andro.bar.Order.class, null);

            AndroThread thread = new AndroThread(andro.bar.controllers.Welcome.mysql, model, "GetTables",
                    null, null, ArrayList.class, null, GetTablesHandler, GetTablesExceptionHandler);
            thread.Start();

        }
        private Handler GetTablesHandler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                Object[] message = (Object[]) msg.obj;
                ArrayList tables = (ArrayList) message[0];

                dialog = view.CreateListMessage(Activity, "Elegir Mesa", tables);
                dialog.SetCallback(TxtDialogButtonHandler);
                dialog.show();
            }
        };
        private Handler GetTablesExceptionHandler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                ImageDialog dialog = view.CreateErrorMessage(Activity, "Un problema ocurrió al enviar el pedido.\n\n" + ((Exception) msg.obj).getMessage());
                dialog.show();
            }
        };
        public View.OnClickListener TxtDialogButtonHandler = new View.OnClickListener() {

            public void onClick(View objView) {
                if (!dialog.GetSelected().equals("")) {

                    final LoadingDialog loadDialog = view.CreateLoadingMessage(Activity, "Orden", "Enviando pedido...");
                    loadDialog.show();

                    AndroThread thread = new AndroThread(andro.bar.controllers.Welcome.mysql, model, "SaveOrder",
                            new Class[]{Integer.class}, new Object[]{Integer.parseInt(dialog.GetSelected())},
                            null, loadDialog, SaveOrderHandler, SaveOrderExceptionHandler);
                    thread.Start();

                    dialog.hide();
                } else {
                    view.ShowToast(Activity, "Debe ingresar el numero de mesa");
                }
            }
            private Handler SaveOrderHandler = new Handler() {

                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);

                    Object[] message = (Object[]) msg.obj;
                    //Object[] categories = (Object[]) message[0];
                    LoadingDialog loadingDialog = (LoadingDialog) message[1];
                    loadingDialog.hide();

                    view.ShowToast(Activity, "Orden enviada");
                    andro.bar.controllers.Welcome.MainList.Clear();
                    Activity.finish();
                }
            };
            private Handler SaveOrderExceptionHandler = new Handler() {

                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);

                    ImageDialog dialog = view.CreateErrorMessage(Activity, "Un problema ocurrió al enviar el pedido.\n\n" + ((Exception) msg.obj).getMessage());
                    dialog.show();
                    /*
                     * String errorMsg = "Un problema ocurrió al iniciar el
                     * programa.\n\n" + ((Exception) msg.obj).getMessage() +
                     * "\n\n¿Desea ir a la ventana de configuración?";
                     * YesNoDialog dialog = view.CreateYesNoMessage(Activity,
                     * "Error", errorMsg); dialog.SetCallback(new
                     * View.OnClickListener() {
                     *
                     * public void onClick(View v) { if (((Button) v).getText()
                     * == YesNoDialog.BUTTON_YES) { Bundle extras = new
                     * Bundle(); extras.putString("activity", "WELCOME");
                     * //andro.bar.controllers.Base.RunActivity(Activity,
                     * andro.bar.Settings.class, extras); } else {
                     * Activity.finish(); } } }); dialog.show();
                     */
                }
            };
        };
    };
}
