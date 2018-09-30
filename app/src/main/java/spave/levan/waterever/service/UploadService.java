package spave.levan.waterever.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;

import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import spave.levan.waterever.db.DBHelper;
import spave.levan.waterever.model.GrowthRecord;
import spave.levan.waterever.model.Plant;
import spave.levan.waterever.utils.StringUtils;

/**
 * 佛祖保佑 永无BUG
 *
 * @author WangZhiYao
 * @date 2018/9/19
 */
public class UploadService extends Service {

    private static final String TAG = "UploadService";

    private DBHelper mDBHelper;
    private RealmResults<Plant> mPlantResults;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mDBHelper = new DBHelper();

        mPlantResults = mDBHelper.queryAllPlantsSortByTime();

        for (Plant plant : mPlantResults) {
            if (plant.isNeedUpload()) {
                Log.d(TAG, "onChange: Need Upload " + plant.getName());
                uploadData(plant);
            }
        }

        mPlantResults.addChangeListener(new RealmChangeListener<RealmResults<Plant>>() {
            @Override
            public void onChange(@NonNull RealmResults<Plant> plants) {
                for (Plant plant : plants) {
                    if (plant.isNeedUpload()) {
                        Log.d(TAG, "onChange: Need Upload " + plant.getName());
                        uploadData(plant);
                    }
                }
            }
        });
    }

    private void uploadData(Plant plant) {
        if (StringUtils.isNullOrEmpty(plant.getAvObjectId())) {
            List<GrowthRecord> growthRecordList = plant.getGrowthRecordList();
            for (GrowthRecord growthRecord : growthRecordList) {
                List<String> photoPathList = growthRecord.getPhotoPathList();

                for (String photoPath : photoPathList) {
                    if (!StringUtils.isNullOrEmpty(photoPath)) {
                        try {

                        } catch (Exception ex) {
                            Log.e(TAG, "uploadData: ", ex);
                        }
                    }
                }
            }
        }
    }
}
