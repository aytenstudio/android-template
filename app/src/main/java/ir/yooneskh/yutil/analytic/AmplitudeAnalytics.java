package ir.yooneskh.yutil.analytic;

import android.app.Activity;
import android.util.Log;

import com.amplitude.api.Amplitude;

import org.json.JSONObject;

import ir.yooneskh.yutil.json.YJsonBuilder;


/**
 * Created by YoonesKh on 2016/11/05.
 */
public class AmplitudeAnalytics implements IYAnalytic {

    @Override
    public void init(Activity activity, String key) {
        Log.i("313 analytics", "amplitude init");
        Amplitude.getInstance().initialize(activity, key).enableForegroundTracking(activity.getApplication());
    }

    @Override
    public void log(String title) {
        Log.i("313 yanalytics event", "title: " + title);
        Amplitude.getInstance().logEvent(title);
    }

    @Override
    public void log(String title, Object... args) {

        JSONObject data = YJsonBuilder.instantJSONObject(args);

        Log.i("313 yanalytics event", "title: " + title + " - data: " + String.valueOf(data));
        Amplitude.getInstance().logEvent(title, data);

    }

}
