package ir.yooneskh.yutil;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created by Yoones on 3/26/2016.
 */
public class YFileSystem {

    public static File privateFolder(Context context) {
        return context.getFilesDir();
    }

    public static File publicFolder() {
        return Environment.getExternalStorageDirectory();
    }

    public static File createExternalDirectory(String path) {

        File base = YFileSystem.publicFolder();

        String[] parts = path.split("/");

        for (String part : parts) {
            base = new File(base, part);
            if (!base.exists()) if (!base.mkdir()) return null;
        }

        return base;

    }

}
