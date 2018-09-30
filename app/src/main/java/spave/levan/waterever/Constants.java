package spave.levan.waterever;

import android.Manifest;

/**
 * 佛祖保佑 永无BUG
 *
 * @author WangZhiYao
 * @date 2018/9/16
 */
public class Constants {

    public static final String[] PERMISSIONS = {Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    public static final int REQUEST_CODE_SELECT_PHOTO = 9502;

    public static final int REQUEST_CODE_EXTERNAL_STORAGE = 9603;

    public static final int REQUEST_CODE_PHOTO_VIEW = 9704;

    public static final int REQUEST_CODE_SIGN_UP = 9805;

    public static final String EXTRA_PHOTO_PATH_LIST = "PHOTO_PATH_LIST";

    public static final String EXTRA_PHOTO_POSITION = "PHOTO_POSITION";

    public static final String EXTRA_USERNAME = "USERNAME";

    public static final String EXTRA_PASSWORD = "PASSWORD";

    public static final String BMOB_APPLICATION_ID = "e64f695cad2b1be802e019d0f03d23d2";
}
