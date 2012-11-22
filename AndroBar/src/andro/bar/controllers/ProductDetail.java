package andro.bar.controllers;

import andro.bar.wrappers.AndroThread;
import andro.bar.wrappers.dialogs.ImageDialog;
import andro.bar.wrappers.dialogs.LoadingDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import entities.Product;

public class ProductDetail extends andro.bar.controllers.Base {

    private andro.bar.ProductDetail Activity;
    private andro.bar.views.ProductDetail view;
    private andro.bar.models.ProductDetail model;
    private Bundle extras;

    public ProductDetail(andro.bar.ProductDetail activity) {
        Activity = activity;
        view = new andro.bar.views.ProductDetail(activity);
        model = new andro.bar.models.ProductDetail();

        extras = Activity.getIntent().getExtras();
        LoadProduct();
    }

    private void LoadProduct() {
        Integer prodId = extras.getInt("productId");

        final LoadingDialog loadDialog = view.CreateLoadingMessage(Activity, "Producto", "Cargando...");
        loadDialog.show();
        AndroThread thread = new AndroThread(andro.bar.controllers.Welcome.mysql, model, "LoadProduct",
                new Class[]{Integer.class}, new Object[]{prodId},
                Product.class, loadDialog, LoadProductHandler, ExceptionHandler);
        thread.Start();
    }
    private Handler LoadProductHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Object[] message = (Object[]) msg.obj;
            Product prod = (Product) message[0];
            LoadingDialog loadingDialog = (LoadingDialog) message[1];
            loadingDialog.hide();
            view.DrawProduct(prod);
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
}