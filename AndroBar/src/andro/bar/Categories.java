package andro.bar;

import android.app.Activity;
import android.os.Bundle;

public class Categories extends Activity {

    private andro.bar.controllers.Categories controller;
            
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.categories);
        
        controller = new andro.bar.controllers.Categories(this);
    }
}
