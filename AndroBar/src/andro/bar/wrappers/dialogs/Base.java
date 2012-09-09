package andro.bar.wrappers.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

public class Base {
    
    public Dialog dialog;
    
    public Base(){
    }
    
    public Base(Context context, String title){
        dialog = new Dialog(context);
        dialog.setTitle(title);
    }
    
    public void show(){
        dialog.show();
    }
    
    public void hide(){
        dialog.dismiss();
    }

    public static DialogInterface.OnClickListener closeAppCallback = new DialogInterface.OnClickListener() {

        public void onClick(DialogInterface arg0, int arg1) {
            System.exit(0);
        }
    };
    
    public static View.OnClickListener closeAppViewCallback = new View.OnClickListener() {

        public void onClick(View v) {
            System.exit(0);
        }
    };
    
    public View.OnClickListener defaultCallback = new View.OnClickListener() {

        public void onClick(View v) {
            dialog.dismiss();
        }
    };
}