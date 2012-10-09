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
    
    public void DrawCategories(Object[] categories, View.OnClickListener CategoryOnClickHandler, View.OnLongClickListener CategoryOnLongClickHandler){
        LinearLayout llMain = (LinearLayout) Activity.findViewById(R.id.cat_List);
        llMain.removeAllViews();
        LinearLayout row = null;
        for(int i=0; i<categories.length;i++){
            if(i % 2 == 0){
                row = ViewDrawer.DrawCategoryRow(Activity);
                llMain.addView(row);
            }
            String name = (String)((Object[])categories[i])[1];
            InputStream image = (InputStream)((Object[])categories[i])[2];
            row.addView(ViewDrawer.DrawCategory(Activity, name, image));
        }
    }
}
