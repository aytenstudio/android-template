package ir.yooneskh.yutil.versioning;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;

import ir.yooneskh.yutil.database.YDatabase;
import ir.yooneskh.yutil.network.YNetwork;
import ir.yooneskh.yutil.network.YNetworkResultProcessor;

/**
 * Created by YoonesKh on 2016/10/11.
 */
public class YUpdater {

    private final static String DATABASE_TAG = "yupdater-last-version";

    public static void check(Activity activity, YUpdateable updateable) {

        int currentVersion = getVersionCode(activity);
        int lastVersion = YDatabase.get(DATABASE_TAG, -1);

        if (lastVersion == -1) {
            updateable.onFreshInstall();
        }
        else if (currentVersion > lastVersion) {
            updateable.onUpdate();
        }

        YDatabase.put(DATABASE_TAG, currentVersion);

    }

    public static void checkRemote(final Activity activity, final YUpdateable updateable) {
        YNetwork.get(
                activity,
                "http://yooneskh.ir/Versioneer/getversion.php?package_name=" + activity.getPackageName(),
                new YNetworkResultProcessor() {
                    @Override
                    public void process(int httpCode, String result) {
                        try {
                            if (httpCode == 200 && result != null && !result.equals("") && Integer.parseInt(result) > getVersionCode(activity)) {
                                updateable.hasUpdate();
                            }
                        }
                        catch (Exception e) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    checkRemote(activity, updateable);
                                }
                            }, 500);
                        }
                    }
                }
        );
    }

    private static String getPackageName(Activity activity) {
        return activity.getPackageName();
    }

    private static int getVersionCode(Context context) {

        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return -1;

    }

}
