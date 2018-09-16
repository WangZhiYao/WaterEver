package spave.levan.waterever.utils;

/**
 * 佛祖保佑 永无BUG
 *
 * @author WangZhiYao
 * @date 2018/9/16
 */
public class StringUtils {

    public static boolean isNullOrEmpty(String strValue) {
        return strValue == null || strValue.isEmpty() || strValue.trim().isEmpty();
    }
}
