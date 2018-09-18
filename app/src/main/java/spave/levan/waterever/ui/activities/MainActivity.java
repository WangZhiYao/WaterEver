package spave.levan.waterever.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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
import spave.levan.waterever.ui.widget.AddNewPlantDialogView;
import spave.levan.waterever.utils.PhotoUtils;
import top.zibin.luban.OnCompressListener;

/**
 * 佛祖保佑 永无BUG
 *
 * @author WangZhiYao
 * @date 2018/9/15
 */
public class MainActivity extends BaseActivity implements AddNewPlantDialogView.OnAddNewPlantClickListener {

    private static final String TAG = "MainActivity";

    @BindView(R.id.main_Toolbar)
    Toolbar mToolbar;
    @BindView(R.id.main_RecyclerView)
    RecyclerView mPlantRecyclerView;

    private DBHelper mDBHelper;
    private List<String> mSelectedPhotoPath;
    private List<Plant> mPlantList;

    private AlertDialog mAddNewPlantDialog;

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
    }

    private void showAddNewPlantDialog(List<String> selectedPhotoPath) {
        AddNewPlantDialogView addNewPlantDialogView = new AddNewPlantDialogView(this);
        addNewPlantDialogView.setPhotoList(selectedPhotoPath);
        addNewPlantDialogView.setOnAddNewPlantClickListener(this);

        mAddNewPlantDialog = new AlertDialog.Builder(this)
                .setView(addNewPlantDialogView)
                .create();
        mAddNewPlantDialog.show();
    }

    @Override
    public void onAddNewPlantClick(String plantName, List<String> photoPathList) {
        PhotoUtils.compressPhoto(this, photoPathList, new OnCompressListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(File file) {
                mSelectedPhotoPath.add(file.getAbsolutePath());

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }
}
