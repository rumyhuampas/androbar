package andro.bar.controllers;

import andro.bar.wrappers.dialogs.TxtDialog;
import android.os.Bundle;
import android.view.View;

public class Order extends andro.bar.controllers.Base {

    private andro.bar.Order Activity;
    private andro.bar.views.Order view;
    //private andro.bar.models.Order model;
    private Bundle extras;
    //private Category cat;

    public Order(andro.bar.Order activity) {
        Activity = activity;
        view = new andro.bar.views.Order(activity);
        //model = new andro.bar.models.Order();
        
        view.DrawList();
        SetClickListeners();
    }
    
    private void SetClickListeners(){
        view.GetConfirmButton().setOnClickListener(BtnConfirmOnClickHandler);
    }
    
    public View.OnClickListener BtnConfirmOnClickHandler = new View.OnClickListener() {

        TxtDialog dialog = null;
        
        public void onClick(View objView) {
            //RunActivity(Activity, andro.bar.Order.class, null);
            dialog = view.CreateNumericTxtMessage(Activity, "Numero de Mesa");
            dialog.SetCallback(TxtDialogButtonHandler);
            dialog.show();
        }
        
        public View.OnClickListener TxtDialogButtonHandler = new View.OnClickListener() {

            public void onClick(View objView) {
                if(!dialog.GetText().equals("")){
                    dialog.hide();
                }
                else{
                    view.ShowToast(Activity, "Debe ingresar el numero de mesa");
                }
            }
        };
    };
}
