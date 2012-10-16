package andro.bar;

import android.app.Activity;
import android.os.Bundle;

public class Order extends Activity {

    private andro.bar.controllers.Order controller;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.order);
        
        controller = new andro.bar.controllers.Order(this);
    }
}
