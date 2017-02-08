package ir.yooneskh.yutil.imageloader;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.GlideModule;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by YoonesKh on 2016/11/19.
 */

public class YGlideModule implements GlideModule {

    @Override public void applyOptions(Context context, GlideBuilder builder) {
        // Apply options to the builder here.
    }

    @Override public void registerComponents(Context context, Glide glide) {

        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .connectTimeout(45, TimeUnit.SECONDS)
                .readTimeout(45, TimeUnit.SECONDS)
                .writeTimeout(45, TimeUnit.SECONDS);

        OkHttpUrlLoader.Factory factory = new OkHttpUrlLoader.Factory(client.build());

        glide.register(GlideUrl.class, InputStream.class, factory);

    }

}
