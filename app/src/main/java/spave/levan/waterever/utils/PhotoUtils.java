package spave.levan.waterever.utils;

import android.app.Activity;
import android.content.Context;

import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;

import java.util.List;

import spave.levan.waterever.R;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * 佛祖保佑 永无BUG
 *
 * @author WangZhiYao
 * @date 2018/9/16
 */
public class PhotoUtils {

    public static void openSelector(Activity activity, int requestCode) {
        if (activity != null && requestCode > 0) {
            Matisse.from(activity)
                    .choose(MimeType.ofImage())
                    .countable(true)
                    .maxSelectable(9)
                    .thumbnailScale(0.85f)
                    .theme(R.style.MatisseStyle)
                    .imageEngine(new GlideEngine())
                    .forResult(requestCode);
        }
    }

    public static void compressPhoto(Context context, List<String> photoPathList, OnCompressListener onCompressListener) {
        if (context != null && !StringUtils.isNullOrEmpty(FileUtils.getPhotoPath()) && onCompressListener != null) {
            Luban.with(context)
                    .load(photoPathList)
                    .setTargetDir(FileUtils.getPhotoPath())
                    .setFocusAlpha(true)
                    .ignoreBy(50)
                    .filter(path -> !(StringUtils.isNullOrEmpty(path)
                            || path.toLowerCase().endsWith(".gif")))
                    .setCompressListener(onCompressListener)
                    .launch();
        }
    }
}
