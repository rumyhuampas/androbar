package andro.bar;

import android.app.Activity;
import android.os.Bundle;

public class Products extends Activity {
    
    private andro.bar.controllers.Products controller;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.products);
        
        controller = new andro.bar.controllers.Products(this);
    }
}
