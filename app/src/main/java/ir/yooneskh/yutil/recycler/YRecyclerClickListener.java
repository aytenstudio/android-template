package ir.yooneskh.yutil.recycler;

import android.view.View;

/**
 * Created by YoonesKh on 2016/11/11.
 */

public interface YRecyclerClickListener {
    void onClick(View view, int position);
    void onLongClick(View view, int position);
}
