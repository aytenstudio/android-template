package ir.yooneskh.yutil.versioning;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import ir.yooneskh.yutil.appstore.YAppStore;
import ir.yooneskh.yutil.database.YDatabase;
import ir.yooneskh.yutil.database.YMemory;
import ir.yooneskh.yutil.dialog.YDialoger;

/**
 * Created by YoonesKh on 2017/01/11.
 */

public class YCommenter {

    private static String DISMISSED_TAG = "ycmntr-dismissed";
    private static String FIRST_TIME_TAG = "ycmntr-first-tag";

    public static void takeComment(final Activity activity) {
        if (YDatabase.get(DISMISSED_TAG, true)) {
            Log.e("313", "wth");
            YDialoger.threeChoice(
                    activity,
                    "یه قدم کوچیک",
                    "اگه از این برنامه خوشتون اومده، می تونید با نظر دادن به برنامه ما، از ما حمایت کنید.",
                    "الان نظر میدم",
                    new Runnable() {
                        @Override
                        public void run() {
                            acceptedCommenting(activity);
                        }
                    },
                    "بعدا نظر میدم",
                    new Runnable() {
                        @Override
                        public void run() {
                            commentLater(activity);
                        }
                    },
                    "نظر نمی دهم",
                    new Runnable() {
                        @Override
                        public void run() {
                            dismissedCommenting(activity);
                        }
                    }
            );
        }
        else {
            Log.e("313", "htw");
            activity.finish();
        }
    }

    private static void dismissedCommenting(Activity activity) {
        YDatabase.put(DISMISSED_TAG, false);
        activity.finish();
    }

    private static void commentLater(Activity activity) {
        activity.finish();
    }

    private static void acceptedCommenting(Context context) {
        YAppStore.showCommenting(context);
        YDatabase.put(DISMISSED_TAG, false);
    }

}
