package ir.aytensoft.androidtemplate.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import ir.aytensoft.androidtemplate.R;
import ir.aytensoft.androidtemplate.activity.EditProfileActivity;
import ir.aytensoft.androidtemplate.activity.MainActivity;
import ir.aytensoft.androidtemplate.model.User;
import ir.aytensoft.androidtemplate.util.Util;
import ir.yooneskh.yutil.YActivity;
import ir.yooneskh.yutil.YFragment;
import ir.yooneskh.yutil.YGlide;
import ir.yooneskh.yutil.YNavigation;
import ir.yooneskh.yutil.json.YJsonObject;
import ir.yooneskh.yutil.network.YNetwork;
import ir.yooneskh.yutil.network.YNetworkResultProcessor;

/**
 * Created by YoonesKh on 2016/10/14.
 */
public class ProfileFragment extends YFragment {

    TextView txtName;
    Button btnEditProfile;
    Button btnLogout;
    ImageView imgCoverPhoto;
    ImageView imgProfilePicture;

    public ProfileFragment(YActivity parent, Integer layoutResId) {
        super(parent, layoutResId);
    }

    @Override
    public void yOnCreate() {

        txtName = (TextView)yFindViewById(R.id.lblName);
        btnEditProfile = (Button)yFindViewById(R.id.btn_edit_profile);
        btnLogout = (Button)yFindViewById(R.id.btn_logout);
        imgCoverPhoto = (ImageView)yFindViewById(R.id.profile_wallpaper);
        imgProfilePicture = (ImageView)yFindViewById(R.id.profile_user_photo);

        User loggedUser = Util.getUser();

        loadUserInfo(loggedUser);

        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YNavigation.launchActivity(parent, EditProfileActivity.class);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.deleteToken();
                YNavigation.launchActivityClearing(parent, MainActivity.class);
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        refreshUserInfo();
    }

    private void refreshUserInfo() {
        YNetwork.get(parent, Util.endpointTokened("user/me"), new YNetworkResultProcessor() {
            @Override
            public void process(int httpCode, String result) {

                if (Util.authCheckExec(parent, httpCode, result) || result == null) return;

                loadUserInfo(User.fromJson(new YJsonObject(result)));

            }
        });
    }

    private void loadUserInfo(User user) {

        Util.saveUser(user);

        txtName.setText(user.getFname() + " " + user.getLname());

        YGlide.with(parent, user.getPicturePath())
                .into(imgProfilePicture);

        YGlide.with(parent, user.getCoverPhotoPath())
                .into(imgCoverPhoto);

    }

}
