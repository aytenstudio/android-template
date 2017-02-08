package ir.yooneskh.yutil;

import android.content.Context;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import ir.aytensoft.androidtemplate.R;

/**
 * Created by YoonesKh on 2016/10/12.
 */
public class YGlide {

    public static <V> DrawableRequestBuilder with(Context context, V resource) {
        return Glide.with(context)
                .load(resource)
                .placeholder(R.drawable.common_image_placeholder)
                .error(R.drawable.common_image_placeholder_error)
                .animate(android.R.anim.fade_in)
                .listener(new RequestListener<V, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, V v, Target<GlideDrawable> target, boolean b) {
                        e.printStackTrace();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable glideDrawable, V v, Target<GlideDrawable> target, boolean b, boolean b1) {
                        return false;
                    }
                })
                .diskCacheStrategy(DiskCacheStrategy.ALL);
    }

}
