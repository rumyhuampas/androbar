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

public class Categories extends andro.bar.controllers.Base {

    private andro.bar.Categories Activity;
    private andro.bar.views.Categories view;
    private andro.bar.models.Categories model;

    public Categories(andro.bar.Categories activity) {
        Activity = activity;
        view = new andro.bar.views.Categories(activity);
        model = new andro.bar.models.Categories();

        view.DrawToolBar(OrderOnClickHandler, SearchOnClickHandler);

        LoadCategories();
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

    private void LoadCategories() {
        final LoadingDialog loadDialog = view.CreateLoadingMessage(Activity, "Categorias", "Cargando...");
        loadDialog.show();

        AndroThread thread = new AndroThread(andro.bar.controllers.Welcome.mysql, model, "LoadCategories",
                null, null, Object[].class, loadDialog, LoadCategoriesHandler, ExceptionHandler);
        thread.Start();
    }
    private Handler LoadCategoriesHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Object[] message = (Object[]) msg.obj;
            Object[] categories = (Object[]) message[0];
            LoadingDialog loadingDialog = (LoadingDialog) message[1];
            loadingDialog.hide();
            view.DrawCategories(categories, ObjectOnClickHandler, null);
        }
    };
    private Handler ExceptionHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String errorMsg = "Lo sentimos, ocurrio un problema. La aplicacion se cerrara.\n\n"
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
            final LoadingDialog loadDialog = view.CreateLoadingMessage(Activity, "Categorias", "Abriendo...");
            loadDialog.show();

            AndroThread thread = new AndroThread(andro.bar.controllers.Welcome.mysql, model, "GetCategoryId",
                    new Class[]{View.class}, new Object[]{objView}, Integer.class, loadDialog, GetCategoryHandler, ExceptionHandler);
            thread.Start();
        }
    };
    private Handler GetCategoryHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Object[] message = (Object[]) msg.obj;
            Integer catId = (Integer) message[0];
            LoadingDialog loadingDialog = (LoadingDialog) message[1];
            loadingDialog.hide();

            Bundle extras = new Bundle();
            extras.putInt("categoryId", catId);
            RunActivity(Activity, andro.bar.Products.class, extras);
        }
    };
    public View.OnLongClickListener ObjectOnLongClickHandler = new View.OnLongClickListener() {

        public boolean onLongClick(View objView) {
            /*
             * Bundle extras = new Bundle(); extras.putInt("ItemID",
             * view.GetObjectId(objView)); extras.putString("ItemType",
             * view.GetObjectType(objView)); RunActivity(Activity,
             * ItemDetails.class, extras);
             */
            return true;
        }
    };

    public void Exit() {
        final YesNoDialog dialog = andro.bar.views.Base.CreateYesNoMessage(Activity, "¿Salir?", "¿Esta seguro?");
        dialog.SetCallback(new View.OnClickListener() {

            public void onClick(View view) {
                if (((Button) view).getText() == YesNoDialog.BUTTON_YES) {
                    Activity.finish();
                } else {
                    dialog.hide();
                }
            }
        });
        dialog.show();
    }
}
