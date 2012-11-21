package andro.bar.controllers;

import andro.bar.wrappers.AndroThread;
import andro.bar.wrappers.ViewDrawer;
import andro.bar.wrappers.dialogs.ImageDialog;
import andro.bar.wrappers.dialogs.LoadingDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

public class Products extends andro.bar.controllers.Base {

    private andro.bar.Products Activity;
    private andro.bar.views.Products view;
    private andro.bar.models.Products model;
    private Bundle extras;

    public Products(andro.bar.Products activity) {
        Activity = activity;
        view = new andro.bar.views.Products(activity);
        model = new andro.bar.models.Products();

        view.DrawToolBar(OrderOnClickHandler, SearchOnClickHandler);

        extras = Activity.getIntent().getExtras();
        LoadProducts();
    }
    public View.OnClickListener OrderOnClickHandler = new View.OnClickListener() {

        public void onClick(View objView) {
            RunActivity(Activity, andro.bar.Order.class, null);
        }
    };
    public View.OnClickListener SearchOnClickHandler = new View.OnClickListener() {

        public void onClick(View objView) {
            RunActivity(Activity, andro.bar.TableOrders.class, null);
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
    }
    private Handler LoadProductsHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Object[] message = (Object[]) msg.obj;
            Object[] products = (Object[]) message[0];
            LoadingDialog loadingDialog = (LoadingDialog) message[1];
            loadingDialog.hide();
            view.DrawProducts(products, ObjectOnClickHandler, ObjectOnLongClickHandler);
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
    public View.OnClickListener ObjectOnClickHandler = new View.OnClickListener() {

        public void onClick(View objView) {
            andro.bar.controllers.Welcome.MainList.Add(ViewDrawer.GetProductId(objView));

            view.ShowShortToast(Activity, "Producto agregado");
        }
    };
    public View.OnLongClickListener ObjectOnLongClickHandler = new View.OnLongClickListener() {

        public boolean onLongClick(View objView) {
            Integer prodId = ViewDrawer.GetProductId(objView);
            Bundle extras = new Bundle();
            extras.putInt("productId", prodId);
            RunActivity(Activity, andro.bar.ProductDetail.class, extras);

            return true;
        }
    };
}
