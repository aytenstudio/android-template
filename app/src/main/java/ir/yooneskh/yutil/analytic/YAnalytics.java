package ir.yooneskh.yutil.analytic;

import android.app.Activity;

/**
 * Created by YoonesKh on 2016/11/06.
 */
public class YAnalytics {

    public static IYAnalytic analytic;

    public static void init(Activity activity, String key) {
        analytic.init(activity, key);
    }

    public static void log(String title) {
        analytic.log(title);
    }

    public static void log(String title, Object... args) {
        analytic.log(title, args);
    }

}
