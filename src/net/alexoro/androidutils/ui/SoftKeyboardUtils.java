package net.alexoro.androidutils.ui;

import android.app.Activity;
import android.content.Context;
import android.os.IBinder;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * User: alex.sorokin@realweb.ru
 * Date: 04.06.13
 * Time: 17:11
 */
public class SoftKeyboardUtils {

    public static void hideSoftKeyboard(Activity activity) {
        View v = activity.getCurrentFocus();
        if (v != null) {
            hideSoftKeyboard(activity, v.getWindowToken());
        }
    }

    public static void hideSoftKeyboard(Context context, View bindView) {
        hideSoftKeyboard(context, bindView.getWindowToken());
    }

    public static void hideSoftKeyboard(Context context, IBinder windowToken) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(windowToken, 0);
    }

}