package andro.bar.views;

import andro.bar.R;
import andro.bar.wrappers.ViewDrawer;
import android.view.View;
import android.widget.LinearLayout;
import java.io.InputStream;

public class Order extends andro.bar.views.Base {
    private andro.bar.Order Activity;

    public Order(andro.bar.Order activity) {
        Activity = activity;
    }
    
    public void DrawList(){
        LinearLayout llMain = (LinearLayout) Activity.findViewById(R.id.order_List);
        llMain.removeAllViews();
        View order = andro.bar.controllers.Welcome.MainList.DrawList(Activity);
        llMain.addView(order);
    }
}
