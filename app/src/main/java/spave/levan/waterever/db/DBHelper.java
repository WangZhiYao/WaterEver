package spave.levan.waterever.db;

import io.realm.Realm;
import io.realm.RealmConfiguration;

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
}
