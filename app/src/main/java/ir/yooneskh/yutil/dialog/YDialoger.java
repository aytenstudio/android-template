package ir.yooneskh.yutil.dialog;

import android.content.Context;
import android.support.annotation.NonNull;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;

/**
 * Created by YoonesKh on 2016/10/11.
 */
public class YDialoger {

    private static MaterialDialog.Builder baseBuilder(Context context) {
        MaterialDialog.Builder result = new MaterialDialog.Builder(context);
        result.titleGravity(GravityEnum.END);
        result.contentGravity(GravityEnum.END);
        result.buttonsGravity(GravityEnum.END);
        result.typeface("irs.ttf", "irs.ttf");
        return result;
    }

    private static MaterialDialog.Builder baseBuilserYesNo(Context context) {
        MaterialDialog.Builder result = baseBuilder(context);
        result.positiveText("آره");
        result.negativeText("نه");
        return result;
    }

    public static void yesNo(Context context, String title, String body, final Runnable onYes, final Runnable onNo) {

        MaterialDialog.Builder result = baseBuilserYesNo(context);

        result.title(title);
        result.content(body);

        if (onYes != null) {
            result.onPositive(new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog materialDialog, @NonNull DialogAction dialogAction) {
                    onYes.run();
                }
            });
        }

        if (onNo != null) {
            result.onNegative(new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog materialDialog, @NonNull DialogAction dialogAction) {
                    onNo.run();
                }
            });
        }

        result.show();

    }

}
