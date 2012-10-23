package andro.bar.wrappers.dialogs;

import andro.bar.R;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.widget.*;

public class TxtDialog extends andro.bar.wrappers.dialogs.Base {        

    //private Spinner sp;
    
    public TxtDialog(Context context, String title){
        super(context, title);
        dialog.setContentView(R.layout.txtdialog);
        ((Button)dialog.findViewById(R.id.txtd_btnOK)).setOnClickListener(defaultCallback);
    }
    
    public void SetCallback(View.OnClickListener callback){
        if (callback == null){
            callback = defaultCallback;
        }
        ((Button)dialog.findViewById(R.id.txtd_btnOK)).setOnClickListener(callback);
    }
    
    /*public String GetSelectedValue(){
        return (String)sp.getSelectedItem();
    }*/
}
