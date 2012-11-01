package andro.bar.wrappers.dialogs;

import andro.bar.R;
import android.app.ProgressDialog;
import android.content.Context;
import android.text.InputType;
import android.view.View;
import android.widget.*;
import java.util.ArrayList;

public class ListDialog extends andro.bar.wrappers.dialogs.Base {    
    
    private String selected;
    
    public ListDialog(Context context, String title, ArrayList items) {
        super(context, title);
        dialog.setContentView(R.layout.listdialog);
        ((Button) dialog.findViewById(R.id.listd_btnOK)).setOnClickListener(defaultCallback);
        DrawItems(context, items);
    }
    
    public void SetCallback(View.OnClickListener callback) {
        if (callback == null) {
            callback = defaultCallback;
        }
        ((Button) dialog.findViewById(R.id.listd_btnOK)).setOnClickListener(callback);
    }
    
    public String GetSelected() {
        return selected;
    }
    
    private View.OnClickListener RBtnHandler = new View.OnClickListener() {

        public void onClick(View view) {
            selected = ((RadioButton)view).getText().toString();
        }
    };
    
    private void DrawItems(Context context, ArrayList items) {
        RadioGroup group = (RadioGroup) dialog.findViewById(R.id.listd_items);
        for (int i = 0; i < items.size(); i++) {
            RadioButton rBtn = new RadioButton(context);
            rBtn.setText((String)items.get(i));
            rBtn.setOnClickListener(RBtnHandler);
            group.addView(rBtn);
        }
    }
}
