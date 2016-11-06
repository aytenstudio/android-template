package ir.yooneskh.yutil.analytic;

import android.app.Activity;
import android.content.Context;

/**
 * Created by YoonesKh on 2016/11/05.
 */
public interface IYAnalytic {
    void init(Activity activity, String key);
    void log(String title);
    void log(String title, Object... args);
}
