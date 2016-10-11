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

}
