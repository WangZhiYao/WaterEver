package spave.levan.waterever.ui.widget;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import spave.levan.waterever.R;
import spave.levan.waterever.ui.adapters.DialogPlantPhotoAdapter;
import spave.levan.waterever.utils.StringUtils;
import spave.levan.waterever.utils.UIUtils;

/**
 * 佛祖保佑 永无BUG
 *
 * @author WangZhiYao
 * @date 2018/9/18
 */
public class AddNewPlantDialogView extends ConstraintLayout implements View.OnClickListener,
        BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemLongClickListener {

    private Context mContext;

    private EditText mEtPlantName;
    private RecyclerView mRvPlantPhoto;
    private Button mBtnAddNewPlant;

    private DialogPlantPhotoAdapter mPlantPhotoAdapter;

    private OnPhotoClickListener mOnPhotoClickListener;
    private OnAddNewPlantClickListener mOnAddNewPlantClickListener;

    public AddNewPlantDialogView(Context context) {
        super(context);
        init(context);
    }

    public AddNewPlantDialogView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AddNewPlantDialogView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;

        LayoutInflater.from(context).inflate(R.layout.layout_dialog_new_plant, this);

        mEtPlantName = findViewById(R.id.dialog_PlantName);
        mRvPlantPhoto = findViewById(R.id.dialog_PlantPhoto);
        mBtnAddNewPlant = findViewById(R.id.dialog_AddNewPlant);

        mPlantPhotoAdapter = new DialogPlantPhotoAdapter(R.layout.item_dialog_plant_photo);
        mPlantPhotoAdapter.setOnItemClickListener(this);
        mPlantPhotoAdapter.setOnItemLongClickListener(this);

        mRvPlantPhoto.setLayoutManager(new GridLayoutManager(context, 3));
        mRvPlantPhoto.addItemDecoration(new IntervalItemDecoration(UIUtils.dp2px(2)));
        mRvPlantPhoto.setAdapter(mPlantPhotoAdapter);

        mBtnAddNewPlant.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.dialog_AddNewPlant) {
            if (mOnAddNewPlantClickListener != null) {
                String plantName = mEtPlantName.getText().toString();
                if (!StringUtils.isNullOrEmpty(plantName)) {
                    mOnAddNewPlantClickListener.onAddNewPlantClick(plantName, mPlantPhotoAdapter.getData());
                }
            }
        }
    }

    public void setPhotoList(List<String> photoList) {
        mPlantPhotoAdapter.setNewData(photoList);
    }

    public void setOnAddNewPlantClickListener(OnAddNewPlantClickListener onAddNewPlantClickListener) {
        mOnAddNewPlantClickListener = onAddNewPlantClickListener;
    }

    public void setOnPhotoClickListener(OnPhotoClickListener onPhotoClickListener) {
        mOnPhotoClickListener = onPhotoClickListener;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (mOnPhotoClickListener != null) {
            mOnPhotoClickListener.onPhotoClick(mPlantPhotoAdapter.getData(), position);
        }
    }

    @Override
    public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
        mPlantPhotoAdapter.remove(position);
        return false;
    }

    public interface OnAddNewPlantClickListener {

        void onAddNewPlantClick(String plantName, List<String> photoPathList);
    }

    public interface OnPhotoClickListener {

        void onPhotoClick(List<String> photoPathList, int position);
    }
}
