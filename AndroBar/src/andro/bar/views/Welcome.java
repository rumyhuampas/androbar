package andro.bar.views;

import andro.bar.R;
import andro.bar.wrappers.GifImageView;
import android.view.Gravity;
import android.widget.LinearLayout;

public class Welcome extends andro.bar.views.Base {
    private andro.bar.Welcome Activity;
    
    public Welcome(andro.bar.Welcome activity){
        Activity = activity;
        LoadLoadingIcon();
    }
    
    private void LoadLoadingIcon(){
        try{
            LinearLayout ll = (LinearLayout) Activity.findViewById(R.id.wl_llLoading);
                        
            LinearLayout llRow = new LinearLayout(Activity);
            llRow.setOrientation(LinearLayout.HORIZONTAL);
            llRow.setGravity(Gravity.RIGHT);
            
            GifImageView loadingGif = new GifImageView(Activity, R.drawable.loading);
            llRow.addView(loadingGif);
            
            ll.addView(llRow, 57, 25);
        }
        catch (Exception ex){
            //ShowError(Activity, "Error", ex.getMessage(), null);
        }
    }
}
