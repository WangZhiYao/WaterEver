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
@AVClassName("GrowthRecord")
public class AVGrowthRecord extends AVObject {

    private static final String GROWTH_RECORD_ID = "growthRecordId";
    private static final String NOTE = "note";
    private static final String ACTION_LIST = "actionList";
    private static final String PHOTO_PATH_LIST = "photoPathList";
    private static final String CREATED_TIME = "createdTime";

    public long getGrowthRecordId() {
        return getLong(GROWTH_RECORD_ID);
    }

    public void setGrowthRecordId(long growthRecordId) {
        put(GROWTH_RECORD_ID, growthRecordId);
    }

    public String getNote() {
        return getString(NOTE);
    }

    public void setNote(String note) {
        put(NOTE, note);
    }

    public List<Integer> getActionList() {
        return getList(ACTION_LIST);
    }

    public void setActionList(List<Integer> actionList) {
        put(ACTION_LIST, actionList);
    }

    public List<AVFile> getPhotoPathList() {
        return getList(PHOTO_PATH_LIST);
    }

    public void setPhotoPathList(List<AVFile> photoPathList) {
        put(PHOTO_PATH_LIST, photoPathList);
    }

    public long getCreatedTime() {
        return getLong(CREATED_TIME);
    }

    public void setCreatedTime(long createdTime) {
        put(CREATED_TIME, createdTime);
    }
}
