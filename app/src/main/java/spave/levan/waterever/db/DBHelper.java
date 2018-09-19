package spave.levan.waterever.db;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.Sort;
import spave.levan.waterever.model.GrowthRecord;
import spave.levan.waterever.model.Plant;
import spave.levan.waterever.model.Tag;

/**
 * 佛祖保佑 永无BUG
 *
 * @author WangZhiYao
 * @date 2018/9/15
 */
public class DBHelper {

    private Realm mRealm;
    private static final String REALM_NAME = "WaterEver.realm";

    public DBHelper() {
        mRealm = Realm.getInstance(new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .name(REALM_NAME)
                .build());
    }

    public void addTag(Tag tag) {
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(tag);
        mRealm.commitTransaction();
    }

    public void addPlant(Plant plant) {
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(plant);
        mRealm.commitTransaction();
    }

    public Plant queryPlantById(long plantId) {
        return mRealm.where(Plant.class)
                .equalTo("plantId", plantId)
                .findFirst();
    }

    public RealmResults<Plant> queryAllPlantsSortByTime() {
        return mRealm.where(Plant.class)
                .sort("createdTime", Sort.DESCENDING)
                .findAll();
    }

    public RealmResults<Plant> queryPlantsByTagSortByTime(String tag) {
        return mRealm.where(Plant.class)
                .equalTo("tag", tag)
                .sort("createdTime", Sort.DESCENDING)
                .findAll();
    }

    public void updatePlantName(long plantId, String name) {
        mRealm.executeTransaction(realm -> {
            Plant plant = realm.where(Plant.class)
                    .equalTo("plantId", plantId)
                    .findFirst();

            if (plant != null) {
                plant.setName(name);
            }
        });
    }

    public void updatePlantCover(long plantId, String cover) {
        mRealm.executeTransaction(realm -> {
            Plant plant = realm.where(Plant.class)
                    .equalTo("plantId", plantId)
                    .findFirst();

            if (plant != null) {
                plant.setCover(cover);
            }
        });
    }

    public void updatePlantTag(long plantId, String tag) {
        mRealm.executeTransaction(realm -> {
            Plant plant = realm.where(Plant.class)
                    .equalTo("plantId", plantId)
                    .findFirst();

            if (plant != null) {
                plant.setTag(tag);
            }
        });
    }

    public void updatePlantStatus(long plantId, int status) {
        mRealm.executeTransaction(realm -> {
            Plant plant = realm.where(Plant.class)
                    .equalTo("plantId", plantId)
                    .findFirst();

            if (plant != null) {
                plant.setStatus(status);
            }
        });
    }

    public void deletePlant(long plantId) {
        mRealm.executeTransaction(realm -> {
            Plant plant = realm.where(Plant.class)
                    .equalTo("plantId", plantId)
                    .findFirst();

            if (plant != null) {
                plant.deleteFromRealm();
            }
        });
    }

    public void addGrowthRecord(long plantId, GrowthRecord growthRecord) {
        mRealm.executeTransaction(realm -> {
            Plant plant = realm.where(Plant.class)
                    .equalTo("plantId", plantId)
                    .findFirst();

            if (plant != null) {
                plant.getGrowthRecordList().add(growthRecord);
            }
        });
    }

    public void updateGrowthRecordNote(long growthRecordId, String note) {
        mRealm.executeTransaction(realm -> {
            GrowthRecord growthRecord = realm.where(GrowthRecord.class)
                    .equalTo("growthRecordId", growthRecordId)
                    .findFirst();

            if (growthRecord != null) {
                growthRecord.setNote(note);
            }
        });
    }

    public void updateGrowthRecordAction(long growthRecordId, List<Integer> actionList) {
        mRealm.executeTransaction(realm -> {
            GrowthRecord growthRecord = realm.where(GrowthRecord.class)
                    .equalTo("growthRecordId", growthRecordId)
                    .findFirst();

            if (growthRecord != null) {
                RealmList<Integer> actionRealmList = new RealmList<>();
                actionRealmList.addAll(actionList);
                growthRecord.setActionList(actionRealmList);
            }
        });
    }

    public void updateGrowthRecordPhotoPath(long growthRecordId, List<String> photoPathList) {
        mRealm.executeTransaction(realm -> {
            GrowthRecord growthRecord = realm.where(GrowthRecord.class)
                    .equalTo("growthRecordId", growthRecordId)
                    .findFirst();

            if (growthRecord != null) {
                RealmList<String> photoPathRealmList = new RealmList<>();
                photoPathRealmList.addAll(photoPathList);
                growthRecord.setPhotoPathList(photoPathRealmList);
            }
        });
    }

    public void deleteGrowthRecord(long growthRecordId) {
        mRealm.executeTransaction(realm -> {
            GrowthRecord growthRecord = realm.where(GrowthRecord.class)
                    .equalTo("growthRecordId", growthRecordId)
                    .findFirst();

            if (growthRecord != null) {
                growthRecord.deleteFromRealm();
            }
        });
    }
}
