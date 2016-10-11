package ir.yooneskh.yutil;

import android.content.Context;

/**
 * Created by Yoones on 11/22/2015.
 */
public class YClipboard {

    public static void put(Context context, String body) {
        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) context.getSystemService(context.CLIPBOARD_SERVICE);
            clipboard.setText(body);
        } else {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText("فال روز", body);
            clipboard.setPrimaryClip(clip);
        }
    }

}
