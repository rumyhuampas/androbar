package andro.bar.views;

import andro.bar.R;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import entities.Category;

public class Categories extends andro.bar.views.Base {

    private andro.bar.Categories Activity;
    private int TextSize;

    public Categories(andro.bar.Categories activity) {
        Activity = activity;
    }
    
    public void DrawCategories(Object[] categories, View.OnClickListener CategoryOnClickHandler, View.OnLongClickListener CategoryOnLongClickHandler){
        LinearLayout llMain = (LinearLayout) Activity.findViewById(R.id.cat_List);
        llMain.removeAllViews();
        for(int i=0; i<categories.length;i++){
            LinearLayout ll = new LinearLayout(Activity);
            if(i % 2 == 0){
                
                ll.setOrientation(LinearLayout.HORIZONTAL);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.FILL_PARENT);
                ll.setLayoutParams(params);
                
                llMain.addView(ll);
            }
            ll.addView(DrawCategory((Object[])categories[i], CategoryOnClickHandler, CategoryOnLongClickHandler));
        }
    }
    
    private LinearLayout DrawCategory(Object[] cat, View.OnClickListener CategoryOnClickHandler, View.OnLongClickListener CategoryOnLongClickHandler) {
        LinearLayout ll = new LinearLayout(Activity);
        ll.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.FILL_PARENT);
        params.weight = 1/2;
        ll.setLayoutParams(params);
        
        ll.setBackgroundResource(R.drawable.buttonblack);
        ll.addView(DrawCatName((String)cat[1]));
        
        //ll.addView(DrawCatName((String)cat[1]));
        ll.setOnClickListener(CategoryOnClickHandler);
        ll.setOnLongClickListener(CategoryOnLongClickHandler);
        //ll.setPadding(0, 5, 0, 5);
        return ll;
    }
    
    private View DrawCategory(Object[] cat) {
        LinearLayout ll = new LinearLayout(Activity);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.FILL_PARENT);
        //params.weight = Float.parseFloat("0.5");
        ll.setBackgroundResource(R.drawable.buttonblack);
        ll.addView(DrawCatName((String)cat[1]));
        return ll;
    }
    
    private TextView DrawCatName(String name) {
        //LinearLayout ll = new LinearLayout(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.FILL_PARENT);
        params.weight = 1;
        //ll.setLayoutParams(params);
        TextView catName = new TextView(Activity);
        catName.setLayoutParams(params);
        catName.setTextColor(Color.WHITE);
        catName.setText(name);
        //ll.addView(saleValueView);
        return catName;
    }
}
