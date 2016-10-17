package ir.yooneskh.yutil;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

/**
 * Created by YoonesKh on 2016/10/17.
 */
 
public class YNotification {

    public static void simple(Activity activity, String title, String body, Class targetActivity) {

        Intent intent = new Intent(activity, targetActivity);
        PendingIntent pendingIntent = PendingIntent.getActivity(activity, (int)System.currentTimeMillis(), intent, 0);

        Notification notification = new Notification.Builder(activity)
                .setContentTitle(title)
                .setContentText(body)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();

        NotificationManager notificationManager = (NotificationManager)activity.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notification);

    }


}
