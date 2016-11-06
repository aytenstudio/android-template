package ir.yooneskh.yutil.component;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by YoonesKh on 2016/10/30.
 */
public class YNonSwipeableViewpager extends ViewPager {

    public YNonSwipeableViewpager(Context context) {
        super(context);
    }

    public YNonSwipeableViewpager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

}
