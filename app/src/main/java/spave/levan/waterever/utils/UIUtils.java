package spave.levan.waterever.utils;

import android.util.TypedValue;

import spave.levan.waterever.App;

/**
 * 佛祖保佑 永无BUG
 *
 * @author WangZhiYao
 * @date 2018/9/17
 */
public class UIUtils {

    public static int dp2px(int dpVal) {
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, App.getInstance().getResources().getDisplayMetrics()));
    }

    public static int sp2px(int spVal) {
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, App.getInstance().getResources().getDisplayMetrics()));
    }
}
