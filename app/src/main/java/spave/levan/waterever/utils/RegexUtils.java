package spave.levan.waterever.utils;

/**
 * 佛祖保佑 永无BUG
 *
 * @author WangZhiYao
 * @date 2018/9/30
 */
public class RegexUtils {

    private static final String REGEX_EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

    private static final String REGEX_PASSWORD = "^([A-Z]|[a-z]|[0-9]|[`~!@#$%^&*()+=|{}':;',\\\\\\\\[\\\\\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“'。，、？]){6,16}$";

    public static boolean isEmail(String strVal) {
        return match(strVal, REGEX_EMAIL);
    }

    public static boolean isPassword(String strVal) {
        return match(strVal, REGEX_PASSWORD);
    }

    private static boolean match(String strVal, String regex) {
        return !StringUtils.isNullOrEmpty(strVal) && strVal.matches(regex);
    }
}
