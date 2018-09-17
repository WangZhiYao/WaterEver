package spave.levan.waterever.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.zhihu.matisse.Matisse;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import spave.levan.waterever.Constants;
import spave.levan.waterever.R;
import spave.levan.waterever.db.DBHelper;
import spave.levan.waterever.model.Plant;
import spave.levan.waterever.ui.adapters.PlantListAdapter;
import spave.levan.waterever.ui.widget.IntervalItemDecoration;
import spave.levan.waterever.utils.PhotoUtils;
import spave.levan.waterever.utils.UIUtils;
import top.zibin.luban.OnCompressListener;

/**
 * 佛祖保佑 永无BUG
 *
 * @author WangZhiYao
 * @date 2018/9/15
 */
public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    @BindView(R.id.main_Toolbar)
    Toolbar mToolbar;
    @BindView(R.id.main_RecyclerView)
    RecyclerView mPlantRecyclerView;

    private DBHelper mDBHelper;

    private List<String> mSelectedPhotoPath;

    private List<Plant> mPlantList;
    private PlantListAdapter mPlantsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        initData();
    }

    private void initData() {
        mDBHelper = new DBHelper();
        mSelectedPhotoPath = new ArrayList<>();
        mPlantList = mDBHelper.queryAllPlantsSortByTime();
        mPlantsAdapter = new PlantListAdapter(this);
        mPlantsAdapter.setPlantList(mPlantList);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        mPlantRecyclerView.setLayoutManager(gridLayoutManager);
        mPlantRecyclerView.setAdapter(mPlantsAdapter);
        mPlantRecyclerView.addItemDecoration(new IntervalItemDecoration(UIUtils.dp2px(4)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_Settings) {
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

            List<String> selectedPhotoPath = Matisse.obtainPathResult(data);

            if (!selectedPhotoPath.isEmpty()) {
                PhotoUtils.compressPhoto(this, selectedPhotoPath, new OnCompressListener() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onSuccess(File file) {
                        mSelectedPhotoPath.add(file.getAbsolutePath());

                        Plant plant = new Plant();
                        plant.setPlantId(System.currentTimeMillis());
                        plant.setCover(file.getAbsolutePath());
                        plant.setName(file.getName());
                        plant.setTime(file.lastModified());
                        mDBHelper.addPlant(plant);

                        mPlantList = mDBHelper.queryAllPlantsSortByTime();
                        mPlantsAdapter.setPlantList(mPlantList);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
            }
        }
    }
}
