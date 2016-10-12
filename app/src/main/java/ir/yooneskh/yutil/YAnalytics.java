package ir.yooneskh.yutil;

import android.app.Activity;
import android.util.Log;

import com.amplitude.api.Amplitude;

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

        JSONObject data = YJsonBuilder.instantJSONObject(args);

        Log.i("313 yanalytics event", "title: " + title + " - data: " + String.valueOf(data));
        Amplitude.getInstance().logEvent(title, data);

    }

}
