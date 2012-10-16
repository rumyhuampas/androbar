package andro.bar.controllers;

import andro.bar.wrappers.AndroThread;
import andro.bar.wrappers.ExtraObject;
import andro.bar.wrappers.dialogs.LoadingDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import entities.Category;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Products extends andro.bar.controllers.Base {

    private andro.bar.Products Activity;
    private andro.bar.views.Products view;
    private andro.bar.models.Products model;
    private Bundle extras;
    //private Category cat;

    public Products(andro.bar.Products activity) {
        Activity = activity;
        view = new andro.bar.views.Products(activity);
        model = new andro.bar.models.Products();
        
        view.DrawToolBar(OrderOnClickHandler);

        extras = Activity.getIntent().getExtras();
        LoadProducts();
    }
    
    public View.OnClickListener OrderOnClickHandler = new View.OnClickListener() {

        public void onClick(View objView) {
            RunActivity(Activity, andro.bar.Order.class, null);
        }
    };

    private void LoadProducts() {
        Integer catId = extras.getInt("categoryId");

        final LoadingDialog loadDialog = view.CreateLoadingMessage(Activity, "Productos", "Cargando...");
        loadDialog.show();
        AndroThread thread = new AndroThread(andro.bar.controllers.Welcome.mysql, model, "LoadProducts",
                new Class[]{Integer.class}, new Object[]{catId},
                Object[].class, loadDialog, LoadProductsHandler, ExceptionHandler);
        thread.Start();
        /*
         * try { andro.bar.controllers.Welcome.mysql.Open();
         * model.LoadProducts(catId);
         * andro.bar.controllers.Welcome.mysql.Close(); } catch (SQLException
         * ex) { Logger.getLogger(Products.class.getName()).log(Level.SEVERE,
         * null, ex); } catch (Exception ex) {
         * Logger.getLogger(Products.class.getName()).log(Level.SEVERE, null,
         * ex);
        }
         */
    }
    private Handler LoadProductsHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Object[] message = (Object[]) msg.obj;
            Object[] products = (Object[]) message[0];
            LoadingDialog loadingDialog = (LoadingDialog) message[1];
            loadingDialog.hide();
            view.DrawProducts(products, null, null);
        }
    };
    private Handler ExceptionHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            /*
             * String errorMsg = "Un problema ocurrió al iniciar el
             * programa.\n\n" + ((Exception) msg.obj).getMessage() + "\n\n¿Desea
             * ir a la ventana de configuración?"; YesNoDialog dialog =
             * view.CreateYesNoMessage(Activity, "Error", errorMsg);
             * dialog.SetCallback(new View.OnClickListener() {
             *
             * public void onClick(View v) { if (((Button) v).getText() ==
             * YesNoDialog.BUTTON_YES) { Bundle extras = new Bundle();
             * extras.putString("activity", "WELCOME");
             * //andro.bar.controllers.Base.RunActivity(Activity,
             * andro.bar.Settings.class, extras); } else { Activity.finish(); }
             * } });
            dialog.show();
             */
        }
    };
}
