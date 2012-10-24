package andro.bar.wrappers.dialogs;

import andro.bar.R;
import android.app.ProgressDialog;
import android.content.Context;
import android.text.InputType;
import android.view.View;
import android.widget.*;

public class TxtDialog extends andro.bar.wrappers.dialogs.Base {        

    private EditText etxt;
    
    public TxtDialog(Context context, String title){
        super(context, title);
        dialog.setContentView(R.layout.txtdialog);
        ((Button)dialog.findViewById(R.id.txtd_btnOK)).setOnClickListener(defaultCallback);
        etxt = ((EditText)dialog.findViewById(R.id.txtd_txt));
    }
    
    public TxtDialog(Context context, String title, boolean numbersOnly){
        super(context, title);
        dialog.setContentView(R.layout.txtdialog);
        ((Button)dialog.findViewById(R.id.txtd_btnOK)).setOnClickListener(defaultCallback);
        etxt = ((EditText)dialog.findViewById(R.id.txtd_txt));
        etxt.setInputType(InputType.TYPE_CLASS_NUMBER);
    }
    
    public void SetCallback(View.OnClickListener callback){
        if (callback == null){
            callback = defaultCallback;
        }
        ((Button)dialog.findViewById(R.id.txtd_btnOK)).setOnClickListener(callback);
    }
    
    public String GetText(){
        return etxt.getText().toString();
    }
}
