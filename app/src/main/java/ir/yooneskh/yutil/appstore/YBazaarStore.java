package ir.yooneskh.yutil.appstore;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

/**
 * Created by YoonesKh on 2016/10/11.
 */
public class YBazaarStore implements IYAppStore {

    @Override
    public boolean hasApplication(Context context) {

        PackageManager pm = context.getPackageManager();

        try {
            PackageInfo info = pm.getPackageInfo("com.farsitel.bazaar",PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }

        return true;

    }

    @Override
    public void showCommenting(Context context) {
        if (hasApplication(context)) {
            Intent intent = new Intent(Intent.ACTION_EDIT);
            intent.setData(Uri.parse("bazaar://details?id=" + context.getPackageName()));
            intent.setPackage("com.farsitel.bazaar");
            context.startActivity(intent);
        }
    }

    @Override
    public void showCollection(Context context) {
        if (hasApplication(context)) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("bazaar://collection?slug=by_author&aid=" + "yooneskh"));
            intent.setPackage("com.farsitel.bazaar");
            context.startActivity(intent);
        }
    }

    @Override
    public void showSelf(Context context) {
        if (hasApplication(context)) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("bazaar://details?id=" + context.getPackageName()));
            intent.setPackage("com.farsitel.bazaar");
            context.startActivity(intent);
        }
    }

}
