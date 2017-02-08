package ir.yooneskh.yutil;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by YoonesKh on 2016/10/29.
 */
public abstract class YFragment extends Fragment {

    protected YActivity parent;
    protected YFragment self;

    private int layoutResId;
    private View rootView = null;

    public YFragment(YActivity parent, Integer layoutResId) {
        this.parent = parent;
        this.layoutResId = layoutResId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            View v = inflater.inflate(layoutResId, container, false);
            rootView = v;
            yOnCreate();
            return v;
        }
        else {
            return rootView;
        }
    }

    public View yFindViewById(int id) {
        return rootView.findViewById(id);
    }

    public abstract void yOnCreate();

}
