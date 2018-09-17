package spave.levan.waterever.ui.widget;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 佛祖保佑 永无BUG
 *
 * @author WangZhiYao
 * @date 2018/9/17
 */
public class GrowthRecordToolbar extends Toolbar implements View.OnClickListener {

    private ImageView mToolbarBack;
    private TextView mToolbarTitle;
    private ImageView mToolbarSettings;
    private ImageView mToolbarWatered;
    private ImageView mToolbarWorm;
    private ImageView mToolbarSterilized;
    private ImageView mToolbarFertilized;
    private ImageView mToolbarChangePot;

    public GrowthRecordToolbar(Context context) {
        super(context);
    }

    public GrowthRecordToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GrowthRecordToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onClick(View view) {

    }

    public interface OnGrowthRecordToolbarClickedListener {

    }
}
