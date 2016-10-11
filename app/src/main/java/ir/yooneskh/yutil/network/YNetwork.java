package ir.yooneskh.yutil.network;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;

/**
 * Created by Yoones on 05/08/2016.
 */
public class YNetwork {

    public static <T> void get(Context context, String endpoint, final YNetworkResultProcessor<T> processor) {

        Log.i("313 ynetwork loading", "endpoint: " + endpoint);

        Ion.with(context)
                .load(endpoint)
                .as(new TypeToken<T>() {})
                .withResponse()
                .setCallback(new FutureCallback<Response<T>>() {
                    @Override
                    public void onCompleted(Exception e, Response<T> result) {

                        if (e != null) {
                            Log.e("313 ynetwork error", e.getMessage());
                            e.printStackTrace();
                        }

                        Log.i("313 ynetwork result", String.valueOf(result.getResult()));

                        processor.process(result.getHeaders().code(), result.getResult());

                    }
                });

    }

    public static <T> void get(Context context, String endpoint, JsonObject data, final YNetworkResultProcessor<T> processor) {

        Log.i("313 ynetwork loading", "endpoint: " + endpoint);

        Ion.with(context)
                .load("GET", endpoint)
                .setJsonObjectBody(data)
                .as(new TypeToken<T>() {})
                .withResponse()
                .setCallback(new FutureCallback<Response<T>>() {
                    @Override
                    public void onCompleted(Exception e, Response<T> result) {

                        if (e != null) {
                            Log.e("313 ynetwork error", e.getMessage());
                            e.printStackTrace();
                        }

                        Log.i("313 ynetwork result", String.valueOf(result.getResult()));

                        processor.process(result.getHeaders().code(), result.getResult());

                    }
                });

    }

    public static <T> void post(Context context, String endpoint, JsonObject data, final YNetworkResultProcessor<T> processor) {

        Log.i("313 ynetwork loading", "endpoint: " + endpoint);

        Ion.with(context)
                .load("POST", endpoint)
                .setJsonObjectBody(data)
                .as(new TypeToken<T>() {})
                .withResponse()
                .setCallback(new FutureCallback<Response<T>>() {
                    @Override
                    public void onCompleted(Exception e, Response<T> result) {

                        if (e != null) {
                            Log.e("313 ynetwork error", e.getMessage());
                            e.printStackTrace();
                        }

                        Log.i("313 ynetwork result", String.valueOf(result.getResult()));

                        processor.process(result.getHeaders().code(), result.getResult());

                    }
                });

    }



}