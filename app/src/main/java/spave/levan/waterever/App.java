package spave.levan.waterever;

import android.app.Application;

import io.realm.Realm;

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
    }

    public static App getInstance() {
        return mInstance;
    }
}
