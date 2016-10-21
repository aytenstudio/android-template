package ir.yooneskh.yutil.network;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.Response;
import com.koushikdutta.ion.builder.Builders;
import com.koushikdutta.ion.builder.Builders.Any.B;

/**
 * Created by Yoones on 05/08/2016.
 */
public class YNetwork {

    private static <T> void request(
            Context context,
            String method,
            String endpoint,
            JsonObject payload,
            final YNetworkResultProcessor<T> processor)
    {

        Log.i("313 ynetwork loading", "endpoint: " + endpoint);

        B builder = Ion.with(context).load(method, endpoint);
        Builders.Any.F fuilder = builder;

        if (payload != null) {
            fuilder = builder.setJsonObjectBody(payload);
        }

        fuilder.as(new TypeToken<T>() {})
            .withResponse()
            .setCallback(new FutureCallback<Response<T>>() {
                @Override
                public void onCompleted(Exception e, Response<T> result) {

                    if (e != null) {

                        Log.e("313 ynetwork error", e.getMessage());
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

    public static <T> void get(Context context, String endpoint, final YNetworkResultProcessor<T> processor) {
        request(context, "GET", endpoint, null, processor);
    }

    public static <T> void get(Context context, String endpoint, JsonObject payload, final YNetworkResultProcessor<T> processor) {
        request(context, "GET", endpoint, payload, processor);
    }

    public static <T> void post(Context context, String endpoint, JsonObject payload, final YNetworkResultProcessor<T> processor) {
        request(context, "POST", endpoint, payload, processor);
    }



}