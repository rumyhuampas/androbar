package andro.bar;

import android.app.Activity;
import android.os.Bundle;

public class ProductDetail extends Activity {

    private andro.bar.controllers.ProductDetail controller;
            
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.productdetail);
        
        controller = new andro.bar.controllers.ProductDetail(this);
    }
}