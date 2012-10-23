package andro.bar.views;

import andro.bar.R;
import andro.bar.wrappers.dialogs.*;
import android.content.Context;
import android.widget.Toast;

public class Base {
    public static void ShowToast(Context context, String msg){
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
    
    public andro.bar.wrappers.dialogs.ImageDialog CreateErrorMessage(Context context, String msg){
        return new ImageDialog(context, "Error", msg, R.drawable.error);
    }
    
    public static andro.bar.wrappers.dialogs.YesNoDialog CreateYesNoMessage(Context context, String title, String msg){
        return new YesNoDialog(context, title, msg, R.drawable.question);
    }
    
    public static andro.bar.wrappers.dialogs.LoadingDialog CreateLoadingMessage(Context context, String title, String msg){
        return new LoadingDialog(context, title, msg);
    }
    
    public andro.bar.wrappers.dialogs.ComboDialog CreateComboMessage(Context context, String title, String prompt, int ArrayID){
        return new ComboDialog(context, title, prompt, ArrayID);
    }
    
    public andro.bar.wrappers.dialogs.TxtDialog CreateTxtMessage(Context context, String title){
        return new TxtDialog(context, title);
    }
}
