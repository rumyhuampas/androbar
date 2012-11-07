package andro.bar.views;

import andro.bar.R;
import andro.bar.wrappers.ViewDrawer;
import android.view.View;
import android.widget.LinearLayout;
import java.io.InputStream;

public class Categories extends andro.bar.views.Base {

    private andro.bar.Categories Activity;
    private int TextSize;

    public Categories(andro.bar.Categories activity) {
        Activity = activity;
    }
    
    public void DrawToolBar(View.OnClickListener OrderOnClickHandler, View.OnClickListener SearchOnClickHandler){
        LinearLayout llMain = (LinearLayout) Activity.findViewById(R.id.cat_List);
        llMain.removeAllViews();
        View order = ViewDrawer.DrawToolBar(Activity, OrderOnClickHandler, SearchOnClickHandler);
        //order.setOnClickListener(OrderOnClickHandler);
        llMain.addView(order);
    }
    
    public void DrawCategories(Object[] categories, View.OnClickListener CategoryOnClickHandler, View.OnLongClickListener CategoryOnLongClickHandler){
        LinearLayout llMain = (LinearLayout) Activity.findViewById(R.id.cat_List);
        //llMain.removeAllViews();
        LinearLayout row = null;
        for(int i=0; i<categories.length;i++){
            if(i % 2 == 0){
                row = ViewDrawer.DrawCategoryRow(Activity);
                llMain.addView(row);
            }
            Integer id = (Integer)((Object[])categories[i])[0];
            String name = (String)((Object[])categories[i])[1];
            InputStream image = (InputStream)((Object[])categories[i])[2];
            View cat = ViewDrawer.DrawCategory(Activity, id, name, image);
            cat.setOnClickListener(CategoryOnClickHandler);
            cat.setOnLongClickListener(CategoryOnLongClickHandler);
            row.addView(cat);
        }
    }
}
