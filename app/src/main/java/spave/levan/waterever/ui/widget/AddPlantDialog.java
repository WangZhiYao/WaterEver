package spave.levan.waterever.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import spave.levan.waterever.R;
import spave.levan.waterever.ui.adapters.DialogPlantPhotoAdapter;
import spave.levan.waterever.utils.StringUtils;
import spave.levan.waterever.utils.UIUtils;

/**
 * 佛祖保佑 永无BUG
 *
 * @author WangZhiYao
 * @date 2018/9/29
 */
public class AddPlantDialog extends Dialog implements BaseQuickAdapter.OnItemClickListener,
        BaseQuickAdapter.OnItemLongClickListener {

    @BindView(R.id.dialog_PlantName)
    EditText mEtPlantName;
    @BindView(R.id.dialog_PlantPhoto)
    RecyclerView mRvPlantPhoto;

    private DialogPlantPhotoAdapter mDialogPlantPhotoAdapter;
    private OnPhotoClickListener mOnPhotoClickListener;
    private OnAddNewPlantClickListener mOnAddNewPlantClickListener;

    public AddPlantDialog(@NonNull Context context) {
        super(context, R.style.DialogStyle);
        init(context);
    }

    private void init(Context context) {
        View addPlantView = LayoutInflater.from(context)
                .inflate(R.layout.layout_dialog_add_plant, null);
        setContentView(addPlantView);

        ButterKnife.bind(this, addPlantView);

        mDialogPlantPhotoAdapter = new DialogPlantPhotoAdapter(R.layout.item_dialog_plant_photo);
        mDialogPlantPhotoAdapter.setOnItemClickListener(this);
        mDialogPlantPhotoAdapter.setOnItemLongClickListener(this);

        mRvPlantPhoto.setLayoutManager(new GridLayoutManager(context, 3));
        mRvPlantPhoto.addItemDecoration(new IntervalItemDecoration(UIUtils.dp2px(2)));
        mRvPlantPhoto.setAdapter(mDialogPlantPhotoAdapter);

        Window dialogWindow = getWindow();
        if (dialogWindow != null) {
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            DisplayMetrics dm = context.getResources().getDisplayMetrics();

            lp.width = Double.valueOf(dm.widthPixels * 0.8).intValue();
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

            dialogWindow.setAttributes(lp);
        }
    }

    public void setPhotoList(List<String> photoList) {
        mDialogPlantPhotoAdapter.replaceData(photoList);
    }

    public void setOnAddNewPlantClickListener(OnAddNewPlantClickListener onAddNewPlantClickListener) {
        mOnAddNewPlantClickListener = onAddNewPlantClickListener;
    }

    public void setOnPhotoClickListener(OnPhotoClickListener onPhotoClickListener) {
        mOnPhotoClickListener = onPhotoClickListener;
    }

    @OnClick(R.id.dialog_AddNewPlant)
    public void onAddPlantClicked() {
        if (mOnAddNewPlantClickListener != null) {
            String plantName = mEtPlantName.getText().toString();
            if (!StringUtils.isNullOrEmpty(plantName)) {
                mOnAddNewPlantClickListener.onAddNewPlantClicked(plantName, mDialogPlantPhotoAdapter.getData());
                return;
            }

            mEtPlantName.setHint(R.string.dialog_must_enter_plant_name);
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (mOnPhotoClickListener != null) {
            mOnPhotoClickListener.onPhotoClicked(mDialogPlantPhotoAdapter.getData(), position);
        }
    }

    @Override
    public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
        mDialogPlantPhotoAdapter.remove(position);
        return false;
    }

    public interface OnAddNewPlantClickListener {

        /**
         * 添加植物
         *
         * @param plantName     植物名
         * @param photoPathList 植物照片
         */
        void onAddNewPlantClicked(String plantName, List<String> photoPathList);
    }

    public interface OnPhotoClickListener {

        /**
         * 植物照片点击事件
         *
         * @param photoPathList 植物照片
         * @param position      被点击的position
         */
        void onPhotoClicked(List<String> photoPathList, int position);
    }
}
