package spave.levan.waterever.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * 佛祖保佑 永无BUG
 *
 * @author WangZhiYao
 * @date 2018/9/16
 */
public class Tag extends RealmObject {

    @PrimaryKey
    private String tag;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
