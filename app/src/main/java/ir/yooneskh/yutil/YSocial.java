package ir.yooneskh.yutil;

import android.app.Activity;
import android.content.ActivityNotFoundException;
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

    public static final int TELEGRAM_LENGTH = 200;

    public static void shareSimpleTextGeneral(Context context, String subject, String body, String chooserTitle) {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        sharingIntent.putExtra(Intent.EXTRA_TEXT, body);
        context.startActivity(Intent.createChooser(sharingIntent, chooserTitle));
    }

    public static void shareImageText(Context context, Uri imageUri, String body, String chooserTitle) {

        Intent shareIntent = new Intent();

        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
        shareIntent.putExtra(Intent.EXTRA_TEXT, body);
        shareIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false);
        shareIntent.setType("image/*");
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        context.startActivity(Intent.createChooser(shareIntent, chooserTitle));

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

    public static void openTelegramId(Activity activity, String id) {
        try {
            activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("tg://resolve?domain=" + id)));
        }
        catch (Exception e) {
            activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://tlgrm.me/" + id)));
        }
    }

    public static void openInstagramId(Activity activity, String id) {

        Uri uri = Uri.parse("http://instagram.com/_u/" + id);
        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

        likeIng.setPackage("com.instagram.android");

        try {
            activity.startActivity(likeIng);
        } catch (ActivityNotFoundException e) {
            activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://instagram.com/" + id)));
        }

    }

}
