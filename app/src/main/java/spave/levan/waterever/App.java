package spave.levan.waterever;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;

import io.realm.Realm;
import spave.levan.waterever.leancloud.AVGrowthRecord;
import spave.levan.waterever.leancloud.AVPlant;
import spave.levan.waterever.leancloud.User;

/**
 * 佛祖保佑 永无BUG
 *
 * @author WangZhiYao
 * @date 2018/9/15
 */
public class App extends Application {

    private static App mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;

        Realm.init(this);

        AVUser.registerSubclass(User.class);
        AVUser.alwaysUseSubUserClass(User.class);

        AVObject.registerSubclass(AVPlant.class);
        AVObject.registerSubclass(AVGrowthRecord.class);

        AVOSCloud.initialize(this, Constants.LEANCLOUD_APP_ID, Constants.LEANCLOUD_APP_KEY);
        AVOSCloud.setDebugLogEnabled(BuildConfig.DEBUG);
    }

    public static App getInstance() {
        return mInstance;
    }
}
