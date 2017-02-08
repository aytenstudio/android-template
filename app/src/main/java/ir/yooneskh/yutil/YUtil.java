package ir.yooneskh.yutil;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.UUID;

import uk.co.chrisjenx.calligraphy.CalligraphyUtils;

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

    public static void changeFontInViewGroup(ViewGroup viewGroup, String fontPath) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {

            View child = viewGroup.getChildAt(i);

            if (TextView.class.isAssignableFrom(child.getClass())) {
                CalligraphyUtils.applyFontToTextView(child.getContext(), (TextView) child, fontPath);
            } else if (ViewGroup.class.isAssignableFrom(child.getClass())) {
                changeFontInViewGroup((ViewGroup) viewGroup.getChildAt(i), fontPath);
            }

        }
    }

    public static File makeFileFromInputStream(YActivity activity, InputStream inputStream) {

        File result = new File(YFileSystem.privateFolder(activity), UUID.randomUUID().toString());

        FileOutputStream outputStream = null;

        try {
            outputStream = new FileOutputStream(result);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        int read = 0;
        byte[] bytes = new byte[1024];

        try {
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;

    }

    public static boolean testLuck(int luck) {
        return new Random().nextInt(100) < luck;
    }

}
