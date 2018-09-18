package spave.levan.waterever.ui.widget;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import spave.levan.waterever.Constants;
import spave.levan.waterever.R;
import spave.levan.waterever.ui.activities.PhotoViewActivity;
import spave.levan.waterever.ui.adapters.DialogPlantPhotoAdapter;
import spave.levan.waterever.utils.StringUtils;
import spave.levan.waterever.utils.UIUtils;

/**
 * 佛祖保佑 永无BUG
 *
 * @author WangZhiYao
 * @date 2018/9/18
 */
public class AddNewPlantDialogView extends ConstraintLayout implements View.OnClickListener {

    private EditText mEtPlantName;
    private RecyclerView mRvPlantPhoto;
    private Button mBtnAddNewPlant;

    private List<String> mPhotoPathList;

    private DialogPlantPhotoAdapter mPlantPhotoAdapter;

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
        LayoutInflater.from(context).inflate(R.layout.layout_dialog_new_plant, this);

        mEtPlantName = findViewById(R.id.dialog_PlantName);
        mRvPlantPhoto = findViewById(R.id.dialog_PlantPhoto);
        mBtnAddNewPlant = findViewById(R.id.dialog_AddNewPlant);

        mPhotoPathList = new ArrayList<>();

        mPlantPhotoAdapter = new DialogPlantPhotoAdapter(context);
        mPlantPhotoAdapter.setOnItemClickListener((photoPathList, position) ->
                context.startActivity(new Intent(context, PhotoViewActivity.class)
                        .putStringArrayListExtra(Constants.EXTRA_PHOTO_PATH_LIST, new ArrayList<>(photoPathList))
                        .putExtra(Constants.EXTRA_PHOTO_POSITION, position)));

        mPlantPhotoAdapter.setOnItemLongClickListener(position -> {
            if (position <= mPhotoPathList.size() - 1) {
                mPhotoPathList.remove(position);
                mPlantPhotoAdapter.setPhotoPathList(mPhotoPathList);
            }
            return false;
        });

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
                    mOnAddNewPlantClickListener.onAddNewPlantClick(plantName, mPhotoPathList);
                }
            }
        }
    }

    public void setPhotoList(List<String> photoList) {
        mPhotoPathList.clear();
        mPhotoPathList.addAll(photoList);
        mPlantPhotoAdapter.setPhotoPathList(mPhotoPathList);
    }

    public void setOnAddNewPlantClickListener(OnAddNewPlantClickListener onAddNewPlantClickListener) {
        mOnAddNewPlantClickListener = onAddNewPlantClickListener;
    }

    public interface OnAddNewPlantClickListener {

        void onAddNewPlantClick(String plantName, List<String> photoPathList);
    }
}
