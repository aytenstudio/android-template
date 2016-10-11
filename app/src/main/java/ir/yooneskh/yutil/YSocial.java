package ir.yooneskh.yutil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

import java.io.File;

/**
 * Created by Yoones on 11/22/2015.
 */
public class YSocial {

    public static String shareLink = "";

    public static void shareSimpleTextGeneral(Context context, String subject, String body, String chooserTitle) {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        sharingIntent.putExtra(Intent.EXTRA_TEXT, body);
        context.startActivity(Intent.createChooser(sharingIntent, chooserTitle));
    }

    public static void shareAppLink(Context context) {

        StringBuilder text = new StringBuilder();

        text.append("انواع برنامه های بدنسازی برای طول هفته شما");
        text.append("\n\n");
        text.append("دانلود کاملا رایگان در");
        text.append("\n");
        text.append(shareLink);
        text.append("http://link9.ir/bodyfit");

        shareSimpleTextGeneral(context, "فرستادن لینک", text.toString(), "انتخاب برنامه فرستادن");

    }

    public static void shareAppAPK(Activity activity) {

        PackageManager pm = activity.getPackageManager();
        String pname = activity.getPackageName();
        String fpath = "";

        for (ApplicationInfo app : pm.getInstalledApplications(0)) {
            if (app.packageName.equals(pname)) {
                fpath = app.sourceDir;
                break;
            }
        }

        Intent i = new Intent(Intent.ACTION_SEND);
        i.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        i.setType("*/*");
        i.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(fpath)));

        try {
            activity.startActivity(Intent.createChooser(i, "فرستادن فایل برنامه"));
        } catch (android.content.ActivityNotFoundException ex) {
            YToaster.longToast(activity, "برنامه اس که بتواند این فایل را بفرستد یافت نشد ...");
        }

    }

}
