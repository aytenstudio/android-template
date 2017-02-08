package ir.yooneskh.yutil.appstore;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

/**
 * Created by YoonesKh on 2016/10/11.
 */
public class YIranappsStore implements IYAppStore {

    private static final String PACKAGE_NAME = "ir.tgbs.android.iranapp";
    @Override
    public boolean hasApplication(Context context) {

        boolean res = true;

        try {
            context.getPackageManager().getPackageInfo(PACKAGE_NAME, PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            res = false;
        }

        return res;

    }

    @Override
    public void showCommenting(Context context) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://iranapps.ir/app/" + context.getPackageName() + "?a=comment&r=5"));
        if (hasApplication(context)) intent.setPackage(PACKAGE_NAME);
        context.startActivity(intent);
    }

    @Override
    public void showCollection(Context context) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://iranapps.ir/user/" + "yooneskh@gmail.com"));
        if (hasApplication(context)) intent.setPackage(PACKAGE_NAME);
        context.startActivity(intent);
    }

    @Override
    public void showSelf(Context context) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://iranapps.ir/app/" + context.getPackageName()));
        if (hasApplication(context)) intent.setPackage(PACKAGE_NAME);
        context.startActivity(intent);
    }

    @Override
    public String getLink(Activity activity) {
        return "http://iranapps.ir/app/" + activity.getPackageName();
    }

}
