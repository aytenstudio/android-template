package ir.yooneskh.yutil;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by Yoones on 05/08/2016.
 */
public class YNavigation {

    public static void launchActivity(Activity activity, Class activityClass) {
        activity.startActivity(new Intent(
                activity,
                activityClass
        ));
    }

    public static void launchActivityFinishing(Activity activity, Class activityClass) {

        activity.startActivity(new Intent(
                activity,
                activityClass
        ));

        activity.finish();

    }

    public static void launchActivityClearing(Activity activity, Class activityClass) {

        Intent intent = new Intent(activity, activityClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        activity.startActivity(intent);

    }

}
