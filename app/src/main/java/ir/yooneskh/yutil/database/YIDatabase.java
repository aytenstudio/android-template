package ir.yooneskh.yutil.database;

import android.animation.ObjectAnimator;
import android.content.Context;

/**
 * Created by Yoones on 05/08/2016.
 */
public interface YIDatabase {
    void init(Context context);
    boolean contains(String key);
    boolean put(String key, Object value);
    <T> T get(String key, T defaultValue);
}
