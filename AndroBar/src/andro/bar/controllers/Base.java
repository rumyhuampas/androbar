package andro.bar.controllers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class Base {
    public static void RunActivity(Context context, Class className, Bundle extras) {
        Intent intent = new Intent(context, className);
        if (extras != null) {
            intent.putExtras(extras);
        }
        context.startActivity(intent);
    }
    
    public static void RunActivityForResult(Activity activity, Class className, Bundle extras) {
        Intent intent = new Intent(activity, className);
        if (extras != null) {
            intent.putExtras(extras);
        }
        //context.startActivity(intent);
        activity.startActivityForResult(intent, 1);
    }
}
