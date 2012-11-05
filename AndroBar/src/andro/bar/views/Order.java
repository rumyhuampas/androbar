package andro.bar.views;

import andro.bar.R;
import android.widget.Button;
import android.widget.LinearLayout;

public class Order extends andro.bar.views.Base {
    private andro.bar.Order Activity;

    public Order(andro.bar.Order activity) {
        Activity = activity;
    }
    
    public void DrawList(){
        LinearLayout llMain = (LinearLayout) Activity.findViewById(R.id.order_List);
        llMain.removeAllViews();
        andro.bar.controllers.Welcome.MainList.DrawList(Activity, llMain, GetConfirmButton());
        //llMain.addView(order);
    }
    
    public Button GetConfirmButton(){
        return (Button) Activity.findViewById(R.id.order_btnConfirm);
    }
    
    public void DisableConfirmButton(){
        ((Button) Activity.findViewById(R.id.order_btnConfirm)).setEnabled(false);
    }
}
