package spave.levan.waterever.ui.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 佛祖保佑 永无BUG
 *
 * @author WangZhiYao
 * @date 2018/9/17
 */
public class IntervalItemDecoration extends RecyclerView.ItemDecoration {

    private int mInterval;

    public IntervalItemDecoration(int interval) {
        mInterval = interval;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.top = mInterval;
        if (parent.getChildLayoutPosition(view) % 2 == 0) {
            outRect.right = mInterval / 2;
            outRect.left = mInterval;
        } else {
            outRect.right = mInterval;
            outRect.left = mInterval / 2;
        }
    }
}
