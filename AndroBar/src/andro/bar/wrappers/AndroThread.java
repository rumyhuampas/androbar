package andro.bar.wrappers;

import Database.MySQL;
import android.os.Handler;
import android.os.Message;
import java.lang.reflect.Method;

public class AndroThread {

    private Thread thread;

    public AndroThread(final MySQL mysqlObj, final Object classObj, final String aMethod, final Class[] params, final Object[] args,
            final Class returnClass, final Object returnArg, final Handler returnHandler, final Handler exceptionHandler) {
        this.thread = new Thread(new Runnable() {

            public void run() {
                try {
                    mysqlObj.Open();
                    try {
                        Method m = classObj.getClass().getMethod(aMethod, params);
                        Object result = m.invoke(classObj, args);
                        Object[] message = new Object[2];
                        if (returnClass != null) {
                            message[0] = returnClass.cast(result);
                        } else {
                            message[0] = null;
                        }
                        message[1] = returnArg;
                        Message msg = returnHandler.obtainMessage(1, message);
                        returnHandler.sendMessage(msg);

                    } finally {
                        mysqlObj.Close();
                    }
                } catch (Exception ex) {
                    Message msg = exceptionHandler.obtainMessage(1, ex);
                    exceptionHandler.sendMessage(msg);
                }
            }
        });
    }

    public void Start() {
        thread.start();
    }
}
