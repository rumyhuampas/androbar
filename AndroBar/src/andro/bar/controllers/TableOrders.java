package andro.bar.controllers;

import andro.bar.wrappers.AndroThread;
import andro.bar.wrappers.dialogs.ImageDialog;
import andro.bar.wrappers.dialogs.LoadingDialog;
import andro.bar.wrappers.dialogs.YesNoDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;

public class TableOrders extends andro.bar.controllers.Base {

    private andro.bar.TableOrders Activity;
    private andro.bar.views.TableOrders view;
    private andro.bar.models.TableOrders model;
    private Integer tableId;

    public TableOrders(andro.bar.TableOrders activity) {
        Activity = activity;
        view = new andro.bar.views.TableOrders(activity);
        model = new andro.bar.models.TableOrders();

        view.DrawToolBar(BtnSearchOnClickHandler);
        SetClickListeners();
    }
    public View.OnClickListener BtnSearchOnClickHandler = new View.OnClickListener() {

        public void onClick(View v) {
            tableId = Integer.parseInt(view.GetSearch());
            andro.bar.wrappers.TableOrders orders = new andro.bar.wrappers.TableOrders(view, tableId);
            view.DrawList(orders);
        }
    };

    private void SetClickListeners() {
        view.GetTableCloseButton().setOnClickListener(BtnTableCloseOnClickHandler);
    }
    public View.OnClickListener BtnTableCloseOnClickHandler = new View.OnClickListener() {

        YesNoDialog dialog = null;

        public void onClick(View objView) {

            dialog = view.CreateYesNoMessage(Activity, "Cerrar mesa", "Cerrar mesa " + tableId.toString() + "?");
            dialog.SetCallback(new View.OnClickListener() {

                public void onClick(View v) {
                    if (((Button) v).getText() == YesNoDialog.BUTTON_YES) {
                        final LoadingDialog loadDialog = view.CreateLoadingMessage(Activity, "Cerrar mesa", "Enviando pedido...");
                        loadDialog.show();

                        AndroThread thread = new AndroThread(andro.bar.controllers.Welcome.mysql, model, "RequestTableClose",
                                new Class[]{Integer.class}, new Object[]{tableId}, null, loadDialog, RequestCloseHandler, ExceptionHandler);
                        thread.Start();
                    }
                    dialog.hide();
                }
            });

            dialog.show();
        }
        private Handler RequestCloseHandler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                Object[] message = (Object[]) msg.obj;
                LoadingDialog loadingDialog = (LoadingDialog) message[1];
                loadingDialog.hide();

                view.ShowToast(Activity, "Pedido enviado");
                Activity.finish();
            }
        };
        private Handler ExceptionHandler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                String errorMsg = "Lo sentimos, ocurrio un problema.\n\n"
                        + ((Exception) msg.obj).getMessage();
                ImageDialog dialog = view.CreateErrorMessage(Activity, errorMsg);
                dialog.SetCallback(new View.OnClickListener() {

                    public void onClick(View v) {
                        Activity.finish();
                    }
                });
                dialog.show();
            }
        };
    };
}
