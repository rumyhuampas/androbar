package andro.bar.views;

import andro.bar.R;
import andro.bar.wrappers.ViewDrawer;
import android.view.View;
import android.widget.LinearLayout;
import java.io.InputStream;

public class Products extends andro.bar.views.Base {
    private andro.bar.Products Activity;
    private int TextSize;

    public Products(andro.bar.Products activity) {
        Activity = activity;
    }
    
    public void DrawProducts(Object[] products, View.OnClickListener ProductOnClickHandler, View.OnLongClickListener ProductOnLongClickHandler){
        LinearLayout llMain = (LinearLayout) Activity.findViewById(R.id.cat_List);
        llMain.removeAllViews();
        LinearLayout row = null;
        for(int i=0; i<products.length;i++){
            if(i % 2 == 0){
                row = ViewDrawer.DrawCategoryRow(Activity);
                llMain.addView(row);
            }
            Integer id = (Integer)((Object[])products[i])[0];
            String name = (String)((Object[])products[i])[1];
            View prod = ViewDrawer.DrawProduct(Activity, id, name);
            prod.setOnClickListener(ProductOnClickHandler);
            prod.setOnLongClickListener(ProductOnLongClickHandler);
            row.addView(prod);
        }
    }
}
