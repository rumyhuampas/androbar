package andro.bar.wrappers;

import andro.bar.R;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.io.InputStream;

public class ViewDrawer {
    
    public static LinearLayout DrawCategoryRow(Context context) {
        LinearLayout row = new LinearLayout(context);
        row.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT);
        row.setLayoutParams(params);
        
        return row;
    }
    
    public static View DrawCategory(Context context, String name, InputStream image) {
        FrameLayout main = new FrameLayout(context);
        LinearLayout.LayoutParams mainParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT);
        mainParams.weight = 1;
        main.setLayoutParams(mainParams);
        //main.setBackgroundColor(Color.RED);
        
        ImageView imBack = new ImageView(context);
        //LinearLayout.LayoutParams imBackParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
          //      LinearLayout.LayoutParams.FILL_PARENT);
        //imParams.gravity = Gravity.CENTER;
        imBack.setBackgroundResource(R.drawable.buttonblack);
        //im.setScaleType(ImageView.ScaleType.FIT_XY);
        
        main.addView(imBack);

        LinearLayout cat = new LinearLayout(context);
        cat.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams catParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT);
        catParams.weight = 1;
        cat.setPadding(10, 10, 10, 10);
        cat.setLayoutParams(catParams);
        
        main.addView(cat);
        
        ImageView im = new ImageView(context);
        LinearLayout.LayoutParams imParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT);
        imParams.weight = 1;
        imParams.gravity = Gravity.CENTER;
        im.setBackgroundResource(R.drawable.buttonblack);
        im.setImageBitmap(BitmapFactory.decodeStream((InputStream) image));
        im.setLayoutParams(imParams);
        
        
        
        //im.setScaleType(ImageView.ScaleType.FIT_XY);
        
        cat.addView(im);
        
        TextView txt = new TextView(context);
        LinearLayout.LayoutParams txtParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.FILL_PARENT);
        txtParams.weight = 2;
        txt.setLayoutParams(txtParams);
        txt.setText(name);
        txt.setTextColor(Color.BLACK);
        txt.setGravity(Gravity.CENTER);
        
        cat.addView(txt);

        //fl.addView(llInt);
        
        return main;
    }
}
