package spave.levan.waterever.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.zhihu.matisse.Matisse;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobRelation;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadBatchListener;
import spave.levan.waterever.Constants;
import spave.levan.waterever.R;
import spave.levan.waterever.model.GrowthRecord;
import spave.levan.waterever.model.Plant;
import spave.levan.waterever.model.PlantPhoto;
import spave.levan.waterever.model.User;
import spave.levan.waterever.ui.widget.AddPlantDialog;
import spave.levan.waterever.utils.PhotoUtils;
import top.zibin.luban.OnCompressListener;

/**
 * 佛祖保佑 永无BUG
 *
 * @author WangZhiYao
 * @date 2018/9/15
 */
public class MainActivity extends BaseActivity implements AddPlantDialog.OnAddNewPlantClickListener,
        AddPlantDialog.OnPhotoClickListener {

    private static final String TAG = "MainActivity";

    @BindView(R.id.main_Toolbar)
    Toolbar mToolbar;
    @BindView(R.id.main_RecyclerView)
    RecyclerView mPlantRecyclerView;

    private AddPlantDialog mAddPlantDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        initData();
    }

    private void initData() {
        getUserPlants(0, 20);
    }

    private void getUserPlants(int page, int limit) {
        BmobQuery<Plant> plantBmobQuery = new BmobQuery<>();
        plantBmobQuery.addWhereEqualTo("user", User.getCurrentUser())
                .setLimit(limit).setSkip(page * limit)
                .order("-createdAt")
                .findObjects(new FindListener<Plant>() {
                    @Override
                    public void done(List<Plant> list, BmobException e) {
                        if (e != null) {
                            showToast(e.getMessage());
                            return;
                        }

                        if (list == null) {
                            return;
                        }

                        for (Plant plant : list) {
                            Log.d(TAG, "done: " + plant.getName());
                        }
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_Settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.main_AddPlant)
    public void onAddPlantClicked() {
        PhotoUtils.openSelector(this, Constants.REQUEST_CODE_SELECT_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.REQUEST_CODE_SELECT_PHOTO && resultCode == RESULT_OK) {
            showAddNewPlantDialog(Matisse.obtainPathResult(data));
        }

        if (requestCode == Constants.REQUEST_CODE_PHOTO_VIEW && resultCode == RESULT_OK) {
            List<String> selectedPhotoPath = data.getStringArrayListExtra(Constants.EXTRA_PHOTO_PATH_LIST);

            if (selectedPhotoPath != null) {
                if (selectedPhotoPath.isEmpty()) {
                    mAddPlantDialog.dismiss();
                    return;
                }

                mAddPlantDialog.setPhotoList(selectedPhotoPath);
            }
        }
    }

    private void showAddNewPlantDialog(List<String> selectedPhotoPath) {
        mAddPlantDialog = new AddPlantDialog(this);
        mAddPlantDialog.setPhotoList(selectedPhotoPath);
        mAddPlantDialog.setOnPhotoClickListener(this);
        mAddPlantDialog.setOnAddNewPlantClickListener(this);
        mAddPlantDialog.show();
    }

    @Override
    public void onAddNewPlantClicked(String plantName, List<String> photoPathList) {

        List<String> compressedPhotoPathList = new ArrayList<>();

        PhotoUtils.compressPhoto(this, photoPathList, new OnCompressListener() {
            @Override
            public void onStart() {
                showProgress(null, "请稍候...");
            }

            @Override
            public void onSuccess(File file) {
                compressedPhotoPathList.add(file.getAbsolutePath());
                if (compressedPhotoPathList.size() == photoPathList.size()) {
                    addPlant(plantName, compressedPhotoPathList);
                }
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    private void addPlant(String plantName, List<String> photoPathList) {
        String[] photoPaths = new String[photoPathList.size()];
        BmobRelation growthRecordRelation = new BmobRelation();
        BmobRelation photoRelation = new BmobRelation();

        BmobFile.uploadBatch(photoPathList.toArray(photoPaths), new UploadBatchListener() {
            @Override
            public void onSuccess(List<BmobFile> plantPhotoFileList, List<String> plantPhotoUrlList) {
                if (plantPhotoFileList.size() == photoPathList.size()) {

                    for (int i = 0; i < plantPhotoFileList.size(); i++) {

                        int index = i;

                        PlantPhoto plantPhoto = new PlantPhoto();
                        plantPhoto.setPhoto(plantPhotoFileList.get(i));
                        plantPhoto.save(new SaveListener<String>() {
                            @Override
                            public void done(String s, BmobException e) {
                                if (e != null) {
                                    showToast(e.getMessage());
                                    return;
                                }

                                photoRelation.add(plantPhoto);

                                if (index == plantPhotoFileList.size() - 1) {
                                    GrowthRecord growthRecord = new GrowthRecord();
                                    growthRecord.setNote("测试Note");
                                    growthRecord.setActions(Collections.singletonList(GrowthRecord.ACTION_WATER));
                                    growthRecord.setPhotos(photoRelation);
                                    growthRecord.save(new SaveListener<String>() {
                                        @Override
                                        public void done(String s, BmobException e) {
                                            if (e != null) {
                                                showToast(e.getMessage());
                                                return;
                                            }

                                            Plant plant = new Plant();
                                            plant.setUser(User.getCurrentUser(User.class));
                                            plant.setTag("测试");
                                            plant.setStatus(Plant.STATUS_ALIVE);
                                            plant.setName(plantName);
                                            plant.setCover(plantPhotoFileList.get(0));
                                            growthRecordRelation.add(growthRecord);
                                            plant.setGrowthRecords(growthRecordRelation);
                                            plant.save(new SaveListener<String>() {
                                                @Override
                                                public void done(String s, BmobException e) {
                                                    if (e != null) {
                                                        showToast(e.getMessage());
                                                    }

                                                    hideProgress();
                                                    mAddPlantDialog.dismiss();
                                                }
                                            });
                                        }
                                    });
                                }
                            }
                        });
                    }
                }
            }

            @Override
            public void onProgress(int i, int i1, int i2, int i3) {

            }

            @Override
            public void onError(int i, String s) {
                showToast(i + ":" + s);
            }
        });
    }

    @Override
    public void onPhotoClicked(List<String> photoPathList, int position) {
        startActivityForResult(new Intent(this, PhotoViewActivity.class)
                .putExtra(Constants.EXTRA_PHOTO_PATH_LIST, new ArrayList<>(photoPathList))
                .putExtra(Constants.EXTRA_PHOTO_POSITION, position), Constants.REQUEST_CODE_PHOTO_VIEW);
    }
}
