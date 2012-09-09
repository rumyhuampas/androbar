package andro.bar.wrappers.dialogs;

import andro.bar.R;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.widget.*;

public class ComboDialog extends andro.bar.wrappers.dialogs.Base {        

    private Spinner sp;
    
    public ComboDialog(Context context, String title, String prompt, int ArrayID){
        super(context, title);
        dialog.setContentView(R.layout.combodialog);
        sp = (Spinner) dialog.findViewById(R.id.cmbd_spOptions);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(context, ArrayID, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
        sp.setPrompt(prompt);
        ((Button)dialog.findViewById(R.id.cmbd_btnOK)).setOnClickListener(defaultCallback);
    }
    
    public void SetCallback(View.OnClickListener callback){
        if (callback == null){
            callback = defaultCallback;
        }
        ((Button)dialog.findViewById(R.id.cmbd_btnOK)).setOnClickListener(callback);
    }
    
    public String GetSelectedValue(){
        return (String)sp.getSelectedItem();
    }
}
