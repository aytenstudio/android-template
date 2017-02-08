package ir.yooneskh.yutil.appstore;

import android.app.Activity;
import android.content.Context;

/**
 * Created by YoonesKh on 2016/10/11.
 */
public class YAppStore {

    public static IYAppStore appStore;

    public static boolean hasApplication(Context context) {
        return appStore.hasApplication(context);
    }

    public static void showCommenting(Context context) {
        appStore.showCommenting(context);
    }

    public static void showCollection(Context context) {
        appStore.showCollection(context);
    }

    public static void showSelf(Context context) {
        appStore.showSelf(context);
    }

    public static String getLink(Activity activity) {
        return appStore.getLink(activity);
    }

}
