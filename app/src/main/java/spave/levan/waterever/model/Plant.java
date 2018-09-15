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
public class Plant extends RealmObject {

    public static final int STATUS_ALIVE = 0;
    public static final int STATUS_DIED = 1;

    @PrimaryKey
    private long plantId;
    private String name;
    private String cover;
    private String tag;
    private int status;
    private RealmList<GrowthRecord> growthRecordList;
    private long time;

    public long getPlantId() {
        return plantId;
    }

    public void setPlantId(long plantId) {
        this.plantId = plantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public RealmList<GrowthRecord> getGrowthRecordList() {
        return growthRecordList;
    }

    public void setGrowthRecordList(RealmList<GrowthRecord> growthRecordList) {
        this.growthRecordList = growthRecordList;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
