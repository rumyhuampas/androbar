package andro.bar;

import android.app.Activity;
import android.os.Bundle;

public class TableOrders extends Activity {
    
    private andro.bar.controllers.TableOrders controller;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        
        setContentView(R.layout.tableorders);
        
        controller = new andro.bar.controllers.TableOrders(this);
    }
}
