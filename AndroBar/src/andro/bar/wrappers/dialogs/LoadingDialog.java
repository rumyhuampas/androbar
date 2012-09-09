package andro.bar.wrappers.dialogs;

import android.app.ProgressDialog;
import android.content.Context;

public class LoadingDialog extends andro.bar.wrappers.dialogs.Base {
    public LoadingDialog(Context context, String title, String msg){
        dialog = new ProgressDialog(context);
        ((ProgressDialog)dialog).setTitle(title);
        ((ProgressDialog)dialog).setCancelable(false);
        ((ProgressDialog)dialog).setMessage(msg);
    }
}
