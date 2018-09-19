package spave.levan.waterever.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * 佛祖保佑 永无BUG
 *
 * @author WangZhiYao
 * @date 2018/9/15
 */
public class GrowthRecord extends RealmObject {

    public static final int ACTION_WATER = 0;
    public static final int ACTION_FERTILIZED = 1;
    public static final int ACTION_CHANGE_POT = 2;
    public static final int ACTION_ANTHELMINTIC = 3;
    public static final int ACTION_STERILIZED = 4;

    @PrimaryKey
    private long growthRecordId;
    private String note;
    private RealmList<Integer> actionList = new RealmList<>();
    private RealmList<String> photoPathList = new RealmList<>();
    private long createdTime;

    private String avObjectId;
    private long lastUpdateTime;
    private long lastUploadTime;

    public long getGrowthRecordId() {
        return growthRecordId;
    }

    public void setGrowthRecordId(long growthRecordId) {
        this.growthRecordId = growthRecordId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public RealmList<Integer> getActionList() {
        return actionList;
    }

    public void setActionList(RealmList<Integer> actionList) {
        this.actionList = actionList;
    }

    public RealmList<String> getPhotoPathList() {
        return photoPathList;
    }

    public void setPhotoPathList(RealmList<String> photoPathList) {
        this.photoPathList = photoPathList;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public void setAvObjectId(String avObjectId) {
        this.avObjectId = avObjectId;
    }

    public String getAvObjectId() {
        return avObjectId;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public long getLastUploadTime() {
        return lastUploadTime;
    }

    public void setLastUploadTime(long lastUploadTime) {
        this.lastUploadTime = lastUploadTime;
    }
}
