package andro.bar.views;

import andro.bar.R;
import andro.bar.wrappers.ViewDrawer;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import entities.Product;
import java.io.InputStream;

public class ProductDetail extends andro.bar.views.Base {
    private andro.bar.ProductDetail Activity;

    public ProductDetail(andro.bar.ProductDetail activity) {
        Activity = activity;
    }
    
    public void DrawProduct(Product prod){
        //LinearLayout llMain = (LinearLayout) Activity.findViewById(R.id);
        //ImageView img = (ImageView) Activity.findViewById(R.id.pd_img);
        
        TextView name = (TextView) Activity.findViewById(R.id.pd_name);
        name.setText(prod.Name);
    }
}
