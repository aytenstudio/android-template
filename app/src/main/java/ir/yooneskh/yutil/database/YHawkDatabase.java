package ir.yooneskh.yutil.database;

import android.content.Context;

import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.HawkBuilder;
import com.orhanobut.hawk.LogLevel;

/**
 * Created by Yoones on 1/26/2016.
 */
public class YHawkDatabase implements YIDatabase {

    @Override
    public void init(Context context) {
                Hawk.init(context)
                .setEncryptionMethod(HawkBuilder.EncryptionMethod.MEDIUM)
                .setStorage(HawkBuilder.newSharedPrefStorage(context))
                .setLogLevel(LogLevel.FULL)
                .build();
    }

    @Override
    public boolean contains(String key) {
        return Hawk.contains(key);
    }

    @Override
    public boolean put(String key, Object value) {
        return Hawk.put(key, value);
    }

    @Override
    public <T> T get(String key, T defaultValue) {
        return Hawk.get(key, defaultValue);
    }

}
