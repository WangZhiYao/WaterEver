package spave.levan.waterever.model;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobRelation;

/**
 * 佛祖保佑 永无BUG
 *
 * @author WangZhiYao
 * @date 2018/9/15
 */
public class Plant extends BmobObject {

    public static final int STATUS_ALIVE = 0;
    public static final int STATUS_DIED = 1;

    private User user;
    private String name;
    private BmobFile cover;
    private String tag;
    private int status;
    private BmobRelation growthRecords;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BmobFile getCover() {
        return cover;
    }

    public void setCover(BmobFile cover) {
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

    public BmobRelation getGrowthRecords() {
        return growthRecords;
    }

    public void setGrowthRecords(BmobRelation growthRecords) {
        this.growthRecords = growthRecords;
    }
}
