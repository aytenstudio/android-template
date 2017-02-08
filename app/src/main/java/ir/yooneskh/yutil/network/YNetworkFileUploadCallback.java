package ir.yooneskh.yutil.network;

import java.io.File;

/**
 * Created by YoonesKh on 2016/12/20.
 */
public interface YNetworkFileUploadCallback {
    void onProgress(long progress, long total);
    void onSuccess(int httpCode, String result);
    void onFail(int httpCode, Exception e);
}
