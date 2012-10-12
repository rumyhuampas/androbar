package andro.bar.controllers;

import andro.bar.wrappers.AndroThread;
import andro.bar.wrappers.ExtraObject;
import andro.bar.wrappers.ViewDrawer;
import andro.bar.wrappers.dialogs.LoadingDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import entities.Category;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Categories extends andro.bar.controllers.Base {

    private andro.bar.Categories Activity;
    private andro.bar.views.Categories view;
    private andro.bar.models.Categories model;
    private Bundle extras;

    public Categories(andro.bar.Categories activity) {
        Activity = activity;
        view = new andro.bar.views.Categories(activity);
        model = new andro.bar.models.Categories();

        //extras = Activity.getIntent().getExtras();
        //GetExtras();
        LoadCategories();
    }

    /*private void GetExtras() {
        ExtraObject extraObj = (ExtraObject) extras.getParcelable("categories");
        LoadCategoriesImages((Object[]) extraObj.Obj);
    }*/

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
            /*String errorMsg = "Un problema ocurrió al iniciar el programa.\n\n"
                    + ((Exception) msg.obj).getMessage()
                    + "\n\n¿Desea ir a la ventana de configuración?";
            YesNoDialog dialog = view.CreateYesNoMessage(Activity, "Error", errorMsg);
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
            dialog.show();*/
        }
    };
    
    public View.OnClickListener ObjectOnClickHandler = new View.OnClickListener() {

        public void onClick(View objView) {
            final LoadingDialog loadDialog = view.CreateLoadingMessage(Activity, "Categorias", "Cargando...");
            loadDialog.show();

            AndroThread thread = new AndroThread(andro.bar.controllers.Welcome.mysql, model, "GetCategory", 
                    new Class[]{View.class}, new Object[]{objView}, Object[].class, loadDialog, GetCategoryHandler, ExceptionHandler);
            thread.Start();

            /*saleList.AddSaleItem(view.GetObjectId(objView), view.GetObjectName(objView),
                    Float.parseFloat(view.GetObjectPrice(objView).replace("$", "")),
                    view.GetObjectType(objView));
            view.RefreshSaleList(saleList, SaleItemOnClickHandler, SaleItemOnLongClickHandler);
            view.SetSaleTotal(String.valueOf(saleList.GetSaleTotal()));*/
        }
    };
    
    private Handler GetCategoryHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Object[] message = (Object[]) msg.obj;
            Object[] category = (Object[]) message[0];
            LoadingDialog loadingDialog = (LoadingDialog) message[1];
            loadingDialog.hide();
            
            ExtraObject catObj = new ExtraObject(category);
            
            Bundle extras = new Bundle();
            extras.putParcelable("category", catObj);
            RunActivity(Activity, ExtraObject.class, extras);
            //view.DrawCategories(Category, ObjectOnClickHandler, null);
        }
    };
    
    public View.OnLongClickListener ObjectOnLongClickHandler = new View.OnLongClickListener() {

        public boolean onLongClick(View objView) {
            /*Bundle extras = new Bundle();
            extras.putInt("ItemID", view.GetObjectId(objView));
            extras.putString("ItemType", view.GetObjectType(objView));
            RunActivity(Activity, ItemDetails.class, extras);*/
            return true;
        }
    };
}
