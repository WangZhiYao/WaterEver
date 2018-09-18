package spave.levan.waterever.ui.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 佛祖保佑 永无BUG
 *
 * @author WangZhiYao
 * @date 2018/9/18
 */
public class IntervalItemDecoration extends RecyclerView.ItemDecoration {

    private int mInterval;

    public IntervalItemDecoration(int interval) {
        mInterval = interval;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int index = parent.getChildLayoutPosition(view) % 3;
        switch (index) {
            case 0:
            case 1:
                outRect.top = mInterval;
                outRect.left = mInterval;
                break;
            case 2:
                outRect.top = mInterval;
                outRect.left = mInterval;
                outRect.right = mInterval;
                break;
            default:
                break;
        }
    }
}
