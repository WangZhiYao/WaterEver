package spave.levan.waterever.leancloud;

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;

import java.util.List;

/**
 * 佛祖保佑 永无BUG
 *
 * @author WangZhiYao
 * @date 2018/9/19
 */
@AVClassName("Plant")
public class AVPlant extends AVObject {

    private static final String USER = "user";
    private static final String PLANT_ID = "plantId";
    private static final String NAME = "name";
    private static final String COVER = "cover";
    private static final String TAG = "tag";
    private static final String STATUS = "status";
    private static final String GROWTH_RECORD_LIST = "growthRecordList";
    private static final String CREATED_TIME = "createdTime";

    public User getUser() {
        return getAVUser(USER);
    }

    public void setUser(User user) {
        put(USER, user);
    }

    public long getPlantId() {
        return getLong(PLANT_ID);
    }

    public void setPlantId(long plantId) {
        put(PLANT_ID, plantId);
    }

    public String getName() {
        return getString(NAME);
    }

    public void setName(String name) {
        put(NAME, name);
    }

    public AVFile getCover() {
        return getAVFile(COVER);
    }

    public void setCover(String cover) {
        put(COVER, cover);
    }

    public String getTAG() {
        return getString(TAG);
    }

    public void setTag(String tag) {
        put(TAG, tag);
    }

    public int getStatus() {
        return getInt(STATUS);
    }

    public void setStatus(int status) {
        put(STATUS, status);
    }

    public List<AVGrowthRecord> getGrowthRecordList() {
        return getList(GROWTH_RECORD_LIST, AVGrowthRecord.class);
    }

    public void setGrowthRecordList(List<AVGrowthRecord> growthRecordList) {
        put(GROWTH_RECORD_LIST, growthRecordList);
    }

    public long getCreatedTime() {
        return getLong(CREATED_TIME);
    }

    public void setCreatedTime(long createdTime) {
        put(CREATED_TIME, createdTime);
    }
}
