package ir.yooneskh.yutil.network;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.Future;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.ProgressCallback;
import com.koushikdutta.ion.Response;
import com.koushikdutta.ion.builder.Builders;
import com.koushikdutta.ion.builder.Builders.Any.B;

import java.io.File;

import ir.yooneskh.yutil.YToaster;

/**
 * Created by Yoones on 05/08/2016.
 */

public class YNetwork {

    private static void request(
            Context context,
            String method,
            String endpoint,
            JsonObject payload,
            final YNetworkResultProcessor processor)
    {

        if (!checkHasInternet(context)) {
            Log.e("313 ynetwork no net", "wth :?");
            YToaster.shortToast(context, "به اینترنت متصل نیستید");
            return;
        }

        Log.i("313 ynetwork loading", "endpoint: " + endpoint);

        B builder = Ion.with(context).load(method, endpoint);
        Builders.Any.F fuilder = builder;

        if (payload != null) {
            fuilder = builder.setJsonObjectBody(payload);
        }

        fuilder.asString()
                .withResponse()
                .setCallback(new FutureCallback<Response<String>>() {
                    @Override
                    public void onCompleted(Exception e, Response<String> result) {

                        if (e != null) {

                            Log.e("313 ynetwork error", ":: " + e.getMessage());
                            e.printStackTrace();


                            Log.i("313 ynetwork result", "null");
                            processor.process(-1, null);

                            return;

                        }

                        Log.i("313 ynetwork result", String.valueOf(result.getResult()));
                        processor.process(result.getHeaders().code(), result.getResult());

                    }
                });

    }

    public static boolean checkHasInternet(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static void get(Context context, String endpoint, final YNetworkResultProcessor processor) {
        request(context, "GET", endpoint, null, processor);
    }

    public static void get(Context context, String endpoint, JsonObject payload, final YNetworkResultProcessor processor) {
        request(context, "GET", endpoint, payload, processor);
    }

    public static void post(Context context, String endpoint, JsonObject payload, final YNetworkResultProcessor processor) {
        request(context, "POST", endpoint, payload, processor);
    }

    public static Future downloadUrl(Activity activity, String url, File destinationFile, final YNetworkFileDownloadCallback callback) {

        if (!checkHasInternet(activity)) {
            Log.e("313 ynetwork no net", "wth :?");
            YToaster.shortToast(activity, "به اینترنت متصل نیستید");
            return null;
        }

        return Ion.with(activity)
                .load(url)
                .progress(new ProgressCallback() {
                    @Override
                    public void onProgress(final long downloaded, final long total) {
                        callback.onProgress(downloaded, total);
                    }
                })
                .write(destinationFile)
                .setCallback(new FutureCallback<File>() {
                    @Override
                    public void onCompleted(Exception e, File file) {
                        if (e != null) {
                            callback.onFail(e);
                        }
                        else {
                            callback.onSuccess(file);
                        }
                    }
                });

    }

    public static Future uploadFile(Activity activity, String url, String parameterName, File sourceFile, final YNetworkFileUploadCallback callback) {

        if (!checkHasInternet(activity)) {
            Log.e("313 ynetwork no net", "wth :?");
            YToaster.shortToast(activity, "به اینترنت متصل نیستید");
            return null;
        }

        return Ion.with(activity)
                .load("POST", url)
                .uploadProgressHandler(new ProgressCallback() {
                    @Override
                    public void onProgress(long uploaded, long total) {
                        callback.onProgress(uploaded, total);
                    }
                })
                .setTimeout(60 * 60 * 1000)
                .setMultipartFile(parameterName, sourceFile)
                .asString()
                .withResponse()
                .setCallback(new FutureCallback<Response<String>>() {
                    @Override
                    public void onCompleted(Exception e, Response<String> _result) {

                        int httpCode = _result.getHeaders().code();
                        String result = _result.getResult();

                        if (e == null) {
                            callback.onSuccess(httpCode, result);
                        }
                        else {
                            callback.onFail(httpCode, e);
                        }

                    }
                });

    }

}