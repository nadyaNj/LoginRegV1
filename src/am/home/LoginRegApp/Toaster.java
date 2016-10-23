package am.home.LoginRegApp;

import android.content.Context;
import android.widget.Toast;

public class Toaster {
    private static Toast currentToast;
    private static Context context;

    public static void init(Context context) {
        Toaster.context = context;
    }

    public static void show(String text, int duration) {
        if (currentToast != null)
            currentToast.cancel();

        currentToast = Toast.makeText(context, text, duration);
        currentToast.show();
    }
}
