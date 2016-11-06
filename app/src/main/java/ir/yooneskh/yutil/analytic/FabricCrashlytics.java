package ir.yooneskh.yutil.analytic;

import android.app.Activity;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.CustomEvent;

import io.fabric.sdk.android.Fabric;
import ir.yooneskh.yutil.YJsonBuilder;

/**
 * Created by YoonesKh on 2016/11/05.
 */
public class FabricCrashlytics implements IYAnalytic {

    @Override
    public void init(Activity activity, String key) {
        Log.i("313 analytics", "crashlytics init");
        Fabric.with(activity, new Crashlytics());
        Fabric.with(activity, new Answers());
    }

    @Override
    public void log(String title) {
        Log.i("313 yanalytics event", "title: " + title);
        Answers.getInstance().logCustom(new CustomEvent(title));
    }

    @Override
    public void log(String title, Object... args) {

        Log.i("313 yanalytics event", "title: " + title + " - data: " + String.valueOf(YJsonBuilder.instantJSONObject(args)));

        CustomEvent event = new CustomEvent(title);

        int i = 0;
        String tag = "wtf :|";

        for (Object arg : args) {
            if ((i++) % 2 == 0) {
                tag = (String) arg;
            }
            else {
                try {
                    event.putCustomAttribute(tag, (Number) arg);
                }
                catch (ClassCastException e) {
                    event.putCustomAttribute(tag, String.valueOf(arg));
                }
            }
        }

        Answers.getInstance().logCustom(event);

    }

}
