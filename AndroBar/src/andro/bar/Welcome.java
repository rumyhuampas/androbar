package andro.bar;

import android.app.Activity;
import android.os.Bundle;

public class Welcome extends Activity
{
    private andro.bar.controllers.Welcome controller;
    
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        
        controller = new andro.bar.controllers.Welcome(this);
    }
}

