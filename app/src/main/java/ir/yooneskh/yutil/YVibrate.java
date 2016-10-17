package ir.yooneskh.yutil;

import android.content.Context;
import android.os.Vibrator;

/**
 * Created by YoonesKh on 2016/10/17.
 */
public class YVibrate {

    public static void once(Context context, int length) {
        ((Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE)).vibrate(length);
    }

    public static void patternOnce(Context context, long[] pattern) {
        ((Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE)).vibrate(pattern, -1);
    }

}
