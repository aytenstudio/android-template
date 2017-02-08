package ir.aytensoft.androidtemplate.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dd.processbutton.iml.ActionProcessButton;
import com.koushikdutta.async.future.Future;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import ir.aytensoft.androidtemplate.R;
import ir.aytensoft.androidtemplate.databinding.ActivityEditProfileBinding;
import ir.aytensoft.androidtemplate.model.User;
import ir.aytensoft.androidtemplate.util.Config;
import ir.aytensoft.androidtemplate.util.Util;
import ir.yooneskh.yutil.YActivity;
import ir.yooneskh.yutil.YToaster;
import ir.yooneskh.yutil.YUtil;
import ir.yooneskh.yutil.analytic.YAnalytics;
import ir.yooneskh.yutil.json.YJsonBuilder;
import ir.yooneskh.yutil.json.YJsonObject;
import ir.yooneskh.yutil.network.YNetwork;
import ir.yooneskh.yutil.network.YNetworkFileUploadCallback;
import ir.yooneskh.yutil.network.YNetworkResultProcessor;

public class EditProfileActivity extends YActivity {

    ActivityEditProfileBinding refs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        refs = DataBindingUtil.setContentView(self, R.layout.activity_edit_profile);
        ySetup(Config.isRtl);
        ySetupToolbar();
        yShowUpArrow();
        yCreate();
    }

    User loggedUser;

    File profilePic, coverPic;
    Future uploadProfilePicFuture, uploadCoverPicFuture;

    boolean hasChosenProfilePic = false;

    private void yCreate() {

        ySetTitle("ویرایش مشخصات");

        YAnalytics.log("Visit Page",
                "Page", "Edit Profile"
        );

        refs.btnUpdateBasics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateBasics();
            }
        });

        refs.btnUpdateProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseProfilePic();
            }
        });

        refs.btnUpdateProfileCoverPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseCoverPic();
            }
        });

        loggedUser = Util.getUser();

        refs.txtFname.setText(loggedUser.getFname());
        refs.txtLname.setText(loggedUser.getLname());

    }

    private void updateCoverPhoto() {

        if (coverPic == null) {
            YToaster.shortToast(self, "عکس انتخاب نشده است");
            return;
        }

        if (uploadCoverPicFuture != null) {
            uploadCoverPicFuture.cancel(true);
            uploadCoverPicFuture = null;
        }

        uploadCoverPicFuture = YNetwork.uploadFile(self, Util.endpointTokened("user/info/cover"), "profile_cover_picture", coverPic, new YNetworkFileUploadCallback() {

            @Override
            public void onProgress(long progress, long total) {
                refs.btnUpdateProfileCoverPic.setProgress( (int)((progress * 100 - 1) / (total)) );
            }

            @Override
            public void onSuccess(int httpCode, String result) {

                if (Util.authCheckExec(self, httpCode, result)) {
                    YAnalytics.log("Profile Edit",
                            "Type", "Profile Cover",
                            "Status", false
                    ); return;
                }

                Log.d("313 upload res", result);

                YJsonObject data = new YJsonObject(result);

                if (data.getBoolean("status")) {

                    YToaster.shortToast(self, "تصویر پس زمینه پروفایل با موفقیت ثبت شد");
                    refs.btnUpdateProfileCoverPic.setProgress(100);

                    YAnalytics.log("Profile Edit",
                            "Type", "Profile Cover",
                            "Status", true
                    );

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            self.finish();
                        }
                    }, 1069);

                }
                else {

                    YToaster.shortToast(self, data.getString("error_message_persian"));
                    refs.btnUpdateProfileCoverPic.setProgress(-1);

                    YAnalytics.log("Profile Edit",
                            "Type", "Profile Cover",
                            "Status", false
                    );

                }

            }

            @Override
            public void onFail(int httpCode, Exception e) {

                Log.e("313 upload error", ":: " + e.getMessage());

                YToaster.shortToast(self, "مشکلی رخ داده است. لطفا دوباره تلاش کنید");
                refs.btnUpdateProfileCoverPic.setProgress(-1);

                YAnalytics.log("Profile Edit",
                        "Type", "Profile Cover",
                        "Status", false
                );

                e.printStackTrace();

            }

        });

    }

    private void updateProfilePic() {

        if (profilePic == null) {
            YToaster.shortToast(self, "عکس انتخاب نشده است");
            return;
        }

        if (uploadProfilePicFuture != null) {
            uploadProfilePicFuture.cancel(true);
            uploadProfilePicFuture = null;
        }

        uploadProfilePicFuture = YNetwork.uploadFile(self, Util.endpointTokened("user/info/picture"), "profile_picture", profilePic, new YNetworkFileUploadCallback() {

            @Override
            public void onProgress(long progress, long total) {
                refs.btnUpdateProfilePic.setProgress( (int)((progress * 100 - 1) / (total)) );
            }

            @Override
            public void onSuccess(int httpCode, String result) {

                if (Util.authCheckExec(self, httpCode, result)) {

                    YAnalytics.log("Profile Edit",
                            "Type", "Profile Picture",
                            "Status", false
                    );

                    return;

                }

                Log.d("313 upload res", result);

                YJsonObject data = new YJsonObject(result);

                if (data.getBoolean("status")) {

                    YToaster.shortToast(self, "تصویر پروفایل با موفقیت ثبت شد");

                    refs.btnUpdateProfilePic.setProgress(100);

                    YAnalytics.log("Profile Edit",
                            "Type", "Profile Picture",
                            "Status", true
                    );

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            self.finish();
                        }
                    }, 1069);

                }
                else {

                    refs.btnUpdateProfilePic.setProgress(-1);

                    YAnalytics.log("Profile Edit",
                            "Type", "Profile Picture",
                            "Status", false
                    );

                    YToaster.shortToast(self, data.getString("error_message_persian"));

                }

            }

            @Override
            public void onFail(int httpCode, Exception e) {

                Log.e("313 upload error", ":: " + e.getMessage());

                YToaster.shortToast(self, "مشکلی رخ داده است. لطفا دوباره تلاش کنید");

                YAnalytics.log("Profile Edit",
                        "Type", "Profile Picture",
                        "Status", false
                );

                refs.btnUpdateProfilePic.setProgress(-1);

                e.printStackTrace();

            }

        });

    }

    private void updateBasics() {

        String fname = refs.txtFname.getText().toString();
        String lname = refs.txtLname.getText().toString();

        YNetwork.post(
                self,
                Util.endpointTokened("user/info/basic"),
                YJsonBuilder.instantJsonObject(
                    "first_name", fname,
                    "last_name", lname
                ),
                new YNetworkResultProcessor() {
                    @Override
                    public void process(int httpCode, String result) {

                        if (Util.authCheckExec(self, httpCode, result)) {

                            YAnalytics.log("Profile Edit",
                                    "Type", "Basics",
                                    "Status", false
                            );

                            return;

                        }

                        if (httpCode != 200 || result == null) {

                            YToaster.shortToast(self, "مشکلی پیش آمده. لطفا دوباره تلاش کنید");

                            YAnalytics.log("Profile Edit",
                                    "Type", "Basics",
                                    "Status", false
                            );

                            return;

                        }

                        if (new YJsonObject(result).getBoolean("status")) {

                            YToaster.shortToast(self, "اطلاعات با موفقیت ثبت شد");

                            YAnalytics.log("Profile Edit",
                                    "Type", "Basics",
                                    "Status", true
                            );

                            self.finish();

                        }
                        else {

                            YToaster.shortToast(self, "مشکلی در ثبت اطلاعات پیش آمد. دوباره تلاش کنید");

                            YAnalytics.log("Profile Edit",
                                    "Type", "Basics",
                                    "Status", false
                            );

                        }

                    }
                }
        );

    }

    private void chooseCoverPic() {
        hasChosenProfilePic = false;
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, 85);
    }

    private void chooseProfilePic() {
        hasChosenProfilePic = true;
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, 69);
    }

    public void onFileSelection(@NonNull File file) {
        if (hasChosenProfilePic) {
            profilePic = file;
            updateProfilePic();
        }
        else {
            coverPic = file;
            updateCoverPhoto();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        if (resultCode == RESULT_OK) {

            Uri selectedImage = imageReturnedIntent.getData();

            InputStream inputStream = null;

            try {
                inputStream = getContentResolver().openInputStream(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return;
            }

            File file = YUtil.makeFileFromInputStream(self, inputStream);

            onFileSelection(file);

        }

    }

}
