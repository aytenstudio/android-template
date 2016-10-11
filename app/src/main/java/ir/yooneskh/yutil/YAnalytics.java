package ir.yooneskh.yutil;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.util.StringBuilderPrinter;

import com.amplitude.api.Amplitude;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by YoonesKh on 2016/10/11.
 */
public class YAnalytics {

    public static void init(Activity activity, String key) {
        Amplitude.getInstance().initialize(activity, key).enableForegroundTracking(activity.getApplication());
    }

    public static void log(String title) {
        Log.i("313 yanalytics event", "title: " + title);
        Amplitude.getInstance().logEvent(title);
    }

    public static void log(String title, Object... args) {

        JSONObject data = new JSONObject();

        int count = 0;
        String key = "";

        for (Object arg : args) {

            if ( ((count++) % 2) == 0 ) {
                key = (String)arg;
            }
            else {
                try {
                    data.put(key, arg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }

        Log.i("313 yanalytics event", "title: " + title + " - data: " + String.valueOf(data));
        Amplitude.getInstance().logEvent(title, data);

    }

}
