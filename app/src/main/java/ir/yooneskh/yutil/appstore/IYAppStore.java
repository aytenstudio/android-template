package ir.yooneskh.yutil.appstore;

import android.app.Activity;
import android.content.Context;

/**
 * Created by YoonesKh on 2016/10/11.
 */
public interface IYAppStore {
    boolean hasApplication(Context context);
    void showCommenting(Context context);
    void showCollection(Context context);
    void showSelf(Context context);
    String getLink(Activity activity);
}
