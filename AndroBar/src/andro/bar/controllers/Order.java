package andro.bar.controllers;

import andro.bar.wrappers.dialogs.TxtDialog;
import android.os.Bundle;
import android.view.View;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
                    try {
                        model.SaveOrder(Integer.parseInt(dialog.GetText()));
                    } catch (SQLException ex) {
                        Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    dialog.hide();
                    view.ShowToast(Activity, "Orden enviada");
                    andro.bar.controllers.Welcome.MainList.Clear();
                    Activity.finish();
                }
                else{
                    view.ShowToast(Activity, "Debe ingresar el numero de mesa");
                }
            }
        };
    };
}
