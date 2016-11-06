package ir.yooneskh.yutil;

/**
 * Created by YoonesKh on 2016/11/03.
 */
public class YUtil {

    public static boolean anyNull(Object... objects) {

        for (Object object : objects) {
            if (object == null) return true;
        }

        return false;

    }

}
