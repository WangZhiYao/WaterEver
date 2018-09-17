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
    private long time;

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

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
