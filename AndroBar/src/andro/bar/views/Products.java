package andro.bar.views;

import andro.bar.R;
import andro.bar.wrappers.ViewDrawer;
import android.view.View;
import android.widget.LinearLayout;

public class Products extends andro.bar.views.Base {
    private andro.bar.Products Activity;
    private int TextSize;

    public Products(andro.bar.Products activity) {
        Activity = activity;
    }
    
    public void DrawToolBar(View.OnClickListener OrderOnClickHandler, View.OnClickListener SearchOnClickHandler){
        LinearLayout llMain = (LinearLayout) Activity.findViewById(R.id.prod_List);
        llMain.removeAllViews();
        View order = ViewDrawer.DrawToolBar(Activity, OrderOnClickHandler, SearchOnClickHandler);
        //order.setOnClickListener(OrderOnClickHandler);
        llMain.addView(order);
    }
    
    public void DrawProducts(Object[] products, View.OnClickListener ProductOnClickHandler, View.OnLongClickListener ProductOnLongClickHandler){
        LinearLayout llMain = (LinearLayout) Activity.findViewById(R.id.prod_List);
        //llMain.removeAllViews();
        for(int i=0; i<products.length;i++){
            Integer id = (Integer)((Object[])products[i])[0];
            String name = (String)((Object[])products[i])[1];
            String desc = (String) ((Object[])products[i])[2];
            Float price = (Float) ((Object[])products[i])[3];
            View prod = ViewDrawer.DrawProduct(Activity, id, name, price);
            prod.setOnClickListener(ProductOnClickHandler);
            prod.setOnLongClickListener(ProductOnLongClickHandler);
            llMain.addView(prod);
        }
    }
}
