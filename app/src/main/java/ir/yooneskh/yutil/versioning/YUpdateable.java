package ir.yooneskh.yutil.versioning;

/**
 * Created by YoonesKh on 2016/10/11.
 */
public interface YUpdateable {
    void onFreshInstall();
    void onUpdate();
    void hasUpdate();
}
