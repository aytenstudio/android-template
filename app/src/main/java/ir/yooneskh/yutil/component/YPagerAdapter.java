package ir.yooneskh.yutil.component;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import ir.yooneskh.yutil.YActivity;
import ir.yooneskh.yutil.YFragment;

/**
 * Created by YoonesKh on 2017/02/08.
 */

public class YPagerAdapter extends FragmentPagerAdapter {

    ArrayList<YPagerElement> elements;

    public YPagerAdapter(FragmentManager fm, YPagerElement... elements) {

        super(fm);

        this.elements = new ArrayList<>(elements.length);

        for (YPagerElement element : elements) {
            this.elements.add(element);
        }

    }

    @Override
    public Fragment getItem(int position) {

        YPagerElement element = elements.get(position);
        YFragment result;

        try {
            result = (YFragment) element.targetFragment.getConstructor(YActivity.class, Integer.class).newInstance(element.parent, element.layoutResId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return result;

    }

    @Override
    public int getCount() {
        return elements.size();
    }

    public static class YPagerElement {

        Class targetFragment;
        YActivity parent;
        int layoutResId;

        public YPagerElement(Class targetFragment, YActivity parent, int layoutResId) {
            this.targetFragment = targetFragment;
            this.parent = parent;
            this.layoutResId = layoutResId;
        }

    }

}
