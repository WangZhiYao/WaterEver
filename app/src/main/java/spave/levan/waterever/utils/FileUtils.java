package spave.levan.waterever.utils;

import android.os.Environment;

import java.io.File;

/**
 * 佛祖保佑 永无BUG
 *
 * @author WangZhiYao
 * @date 2018/9/16
 */
public class FileUtils {

    private static boolean isSdCardExist() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    private static String getSdCardPath() {
        if (isSdCardExist()) {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        }

        return "";
    }

    private static boolean isPathExist(String path) {
        if (!StringUtils.isNullOrEmpty(path)) {
            File file = new File(path);
            return file.exists() || file.mkdir();
        }

        return false;
    }

    public static String getPhotoPath() {
        if (!StringUtils.isNullOrEmpty(getSdCardPath())) {
            String path = getSdCardPath() + File.separator
                    + "WaterEver";
            if (isPathExist(path)) {
                return path;
            }
        }

        return "";
    }
}
