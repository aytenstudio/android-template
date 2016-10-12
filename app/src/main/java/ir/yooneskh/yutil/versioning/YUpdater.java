package ir.yooneskh.yutil.versioning;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.renderscript.Double2;

import ir.yooneskh.yutil.YToaster;
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
                new YNetworkResultProcessor<Double>() {
                    @Override
                    public void process(int httpCode, Double result) {
                        if (httpCode == 200 && Math.round(result) > getVersionCode(activity)) {
                            updateable.hasUpdate();
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
