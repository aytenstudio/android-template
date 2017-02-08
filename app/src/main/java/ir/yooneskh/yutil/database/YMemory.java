package ir.yooneskh.yutil.database;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by Yoones on 5/29/2015.
 */
public class YMemory {

    public static HashMap<String, Object> mems;

    static {
        mems = new HashMap<>();
    }

    public static boolean contains(String key) {
        return mems.containsKey(key);
    }

    public static boolean put(String key, Object value) {
        return mems.put(key, value) != null;
    }

    public static <T> T get(String key, T defaultValue) {
        return contains(key) ? (T) mems.get(key) : defaultValue;
    }

    public static boolean remove(String key) {
        return mems.remove(key) != null;
    }

}
