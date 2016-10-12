package ir.yooneskh.yutil;

import android.content.Context;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import ir.aytensoft.androidtemplate.R;

/**
 * Created by YoonesKh on 2016/10/12.
 */
public class YGlide {

    public static <V> DrawableRequestBuilder with(Context context, V resource) {
        return Glide.with(context)
                .load(resource)
                .placeholder(R.drawable.common_image_placeholder)
                .animate(android.R.anim.fade_in)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
    }

}
