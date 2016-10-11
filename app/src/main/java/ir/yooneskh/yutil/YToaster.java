package ir.yooneskh.yutil;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Yoones on 9/17/2015.
 */
public class YToaster {

    public static void shortToast(Context context, String body) {
        Toast.makeText(context, body, Toast.LENGTH_SHORT).show();
    }

    public static void longToast(Context context, String body) {
        Toast.makeText(context, body, Toast.LENGTH_LONG).show();
    }
}
