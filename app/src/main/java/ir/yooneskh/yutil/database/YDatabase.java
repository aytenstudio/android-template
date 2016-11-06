package ir.yooneskh.yutil.database;

import android.content.Context;

/**
 * Created by YoonesKh on 2016/09/22.
 */
public class YDatabase {

    public static YIDatabase database;

    public static void init(Context context) {
        database.init(context);
    }

    public static boolean contains(String key) {
        return database.contains(key);
    }

    public static boolean put(String key, Object value) {
        return database.put(key, value);
    }

    public static <T> T get(String key, T defaultValue) {
        return database.get(key, defaultValue);
    }

}
