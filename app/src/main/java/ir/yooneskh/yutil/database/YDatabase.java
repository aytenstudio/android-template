package ir.yooneskh.yutil.database;

import android.content.Context;

/**
 * Created by YoonesKh on 2016/09/22.
 */
public class YDatabase {

    static YIDatabase d = new YHawkDatabase();

    public static void init(Context context) {
        d.init(context);
    }

    public static boolean contains(String key) {
        return d.contains(key);
    }

    public static boolean put(String key, Object value) {
        return d.put(key, value);
    }

    public static <T> T get(String key, T defaultValue) {
        return d.get(key, defaultValue);
    }

}
