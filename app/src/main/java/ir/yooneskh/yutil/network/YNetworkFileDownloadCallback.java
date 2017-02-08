package ir.yooneskh.yutil.network;

import java.io.File;

/**
 * Created by YoonesKh on 2016/12/20.
 */
public interface YNetworkFileDownloadCallback {
    void onProgress(long progress, long total);
    void onSuccess(File file);
    void onFail(Exception e);
}
