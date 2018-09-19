package spave.levan.waterever.ui.adapters;

import android.widget.ImageView;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import spave.levan.waterever.GlideApp;
import spave.levan.waterever.R;
import spave.levan.waterever.utils.StringUtils;

/**
 * 佛祖保佑 永无BUG
 *
 * @author WangZhiYao
 * @date 2018/9/18
 */
public class DialogPlantPhotoAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public DialogPlantPhotoAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        if (!StringUtils.isNullOrEmpty(item)) {
            GlideApp.with(mContext)
                    .load(item)
                    .centerCrop()
                    .transition(new DrawableTransitionOptions().crossFade())
                    .into((ImageView) helper.getView(R.id.item_PlantPhoto));
        }
    }
}
