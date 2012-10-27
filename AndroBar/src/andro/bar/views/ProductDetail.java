package andro.bar.views;

import andro.bar.R;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.widget.TextView;
import entities.Product;
import java.io.InputStream;

public class ProductDetail extends andro.bar.views.Base {

    private andro.bar.ProductDetail Activity;
    
    public ProductDetail(andro.bar.ProductDetail activity) {
        Activity = activity;
    }
    
    public void DrawProduct(Product prod) {        
        TextView name = (TextView) Activity.findViewById(R.id.pd_name);
        name.setText(prod.Name);
        
        ImageView img = (ImageView) Activity.findViewById(R.id.pd_img);
        if (prod.ImageStream != null) {
            img.setImageBitmap(BitmapFactory.decodeStream((InputStream) prod.ImageStream));
        } else {
            img.setImageResource(R.drawable.noimage);
        }
        
        TextView desc = (TextView) Activity.findViewById(R.id.pd_desc);
        desc.setText(prod.Description);
        
        TextView price = (TextView) Activity.findViewById(R.id.pd_txtPrice);
        price.setText("$ " + String.valueOf(prod.Price));
    }
}
