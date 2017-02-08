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

        result.typeface("irs-medium.ttf", "irs-light.ttf");

        return result;

    }

    public static MaterialDialog twoChoice(Context context, String title, String body,  String positiveTitle, final Runnable onPositive, String negativeTitle, final Runnable onNegative) {

        MaterialDialog.Builder result = baseBuilder(context);

        result.title(title);
        result.content(body);

        result.positiveText(positiveTitle);
        result.negativeText(negativeTitle);

        if (onPositive != null) {
            result.onPositive(new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog materialDialog, @NonNull DialogAction dialogAction) {
                    onPositive.run();
                }
            });
        }

        if (onNegative != null) {
            result.onNegative(new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog materialDialog, @NonNull DialogAction dialogAction) {
                    onNegative.run();
                }
            });
        }

        return result.show();

    }

    public static MaterialDialog threeChoice(Context context, String title, String body,  String positiveTitle, final Runnable onPositive, String negativeTitle, final Runnable onNegative, String neutralTitle, final Runnable onNeutral) {

        MaterialDialog.Builder result = baseBuilder(context);

        result.title(title);
        result.content(body);

        result.positiveText(positiveTitle);
        result.negativeText(negativeTitle);
        result.neutralText(neutralTitle);

        if (onPositive != null) {
            result.onPositive(new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog materialDialog, @NonNull DialogAction dialogAction) {
                    onPositive.run();
                }
            });
        }

        if (onNegative != null) {
            result.onNegative(new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog materialDialog, @NonNull DialogAction dialogAction) {
                    onNegative.run();
                }
            });
        }

        if (onNeutral != null) {
            result.onNeutral(new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog materialDialog, @NonNull DialogAction dialogAction) {
                    onNeutral.run();
                }
            });
        }

        return result.show();

    }

    public static void yesNo(Context context, String title, String body, final Runnable onYes, final Runnable onNo) {
        twoChoice(context, title, body, "آره", onYes, "نه", onNo);
    }

}
