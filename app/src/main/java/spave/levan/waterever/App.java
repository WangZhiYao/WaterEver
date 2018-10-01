package spave.levan.waterever;

import android.app.Application;

import cn.bmob.v3.Bmob;

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

        Bmob.initialize(this, Constants.BMOB_APPLICATION_ID);
    }

    public static App getInstance() {
        return mInstance;
    }
}
