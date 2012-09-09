package andro.bar.wrappers.dialogs;

import andro.bar.R;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageDialog extends andro.bar.wrappers.dialogs.Base{

    public ImageDialog(Context context, String title, String msg, int imageId){
        super(context, title);
        dialog.setContentView(R.layout.imagedialog);
        ((TextView)dialog.findViewById(R.id.id_txtMsg)).setText(msg);
        ((ImageView)dialog.findViewById(R.id.id_imgLogo)).setImageResource(imageId);
        ((Button)dialog.findViewById(R.id.id_btnOK)).setOnClickListener(defaultCallback);
    }
    
    public void SetCallback(View.OnClickListener callback){
        if (callback == null){
            callback = defaultCallback;
        }
        ((Button)dialog.findViewById(R.id.id_btnOK)).setOnClickListener(callback);
    }
}
