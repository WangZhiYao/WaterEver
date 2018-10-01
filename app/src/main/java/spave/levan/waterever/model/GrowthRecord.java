package spave.levan.waterever.model;

import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobRelation;

/**
 * 佛祖保佑 永无BUG
 *
 * @author WangZhiYao
 * @date 2018/9/15
 */
public class GrowthRecord extends BmobObject {

    public static final int ACTION_WATER = 0;
    public static final int ACTION_FERTILIZED = 1;
    public static final int ACTION_CHANGE_POT = 2;
    public static final int ACTION_ANTHELMINTIC = 3;
    public static final int ACTION_STERILIZED = 4;

    private String note;
    private List<Integer> actions;
    private BmobRelation photos;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<Integer> getActions() {
        return actions;
    }

    public void setActions(List<Integer> actions) {
        this.actions = actions;
    }

    public BmobRelation getPhotos() {
        return photos;
    }

    public void setPhotos(BmobRelation plantPhotos) {
        this.photos = plantPhotos;
    }
}
