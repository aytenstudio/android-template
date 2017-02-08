package ir.aytensoft.androidtemplate.fragment;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ir.aytensoft.androidtemplate.R;
import ir.aytensoft.androidtemplate.activity.MainActivity;
import ir.aytensoft.androidtemplate.activity.RegisterActivity;
import ir.aytensoft.androidtemplate.model.User;
import ir.aytensoft.androidtemplate.util.Util;
import ir.yooneskh.yutil.YActivity;
import ir.yooneskh.yutil.YFragment;
import ir.yooneskh.yutil.YNavigation;
import ir.yooneskh.yutil.YToaster;
import ir.yooneskh.yutil.analytic.YAnalytics;
import ir.yooneskh.yutil.json.YJsonBuilder;
import ir.yooneskh.yutil.json.YJsonObject;
import ir.yooneskh.yutil.network.YNetwork;
import ir.yooneskh.yutil.network.YNetworkResultProcessor;

/**
 * Created by YoonesKh on 2016/10/30.
 */
public class LoginFragment extends YFragment {

    public LoginFragment(YActivity parent, Integer layoutResId) {
        super(parent, layoutResId);
    }

    TextView txtUsername, txtPassword;
    Button btnLogin, btnRegister;

    @Override
    public void yOnCreate() {

        ySetupRefs();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogin();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doRegistering();
            }
        });

    }

    private void doLogin() {
        YNetwork.post(
                parent,
                Util.endpoint("login"),
                YJsonBuilder.instantJsonObject(
                    "username", txtUsername.getText().toString(),
                    "password", txtPassword.getText().toString()
                ),
                new YNetworkResultProcessor() {
                    @Override
                    public void process(int httpCode, String result) {

                        if (Util.normalCheckExec(parent, httpCode, result)) {

                            YToaster.longToast(parent, "اطلاعات وارد شده صحیح نیست");

                            YAnalytics.log("Login",
                                    "Status", "Fail"
                            );

                            return;

                        }

                        YJsonObject data = new YJsonObject(result);

                        Util.saveToken(data.getString("token"));
                        Util.saveUser(User.fromJson(data.getYJsonObject("user")));

                        YToaster.shortToast(parent, "خوش آمدید");

                        YAnalytics.log("Login",
                                "Status", "Success"
                        );

                        YNavigation.launchActivityClearing(parent, MainActivity.class);

                    }
                }
        );
    }

    private void doRegistering() {
        YNavigation.launchActivity(parent, RegisterActivity.class);
    }

    private void ySetupRefs() {
        txtUsername = (TextView) yFindViewById(R.id.txt_username);
        txtPassword = (TextView) yFindViewById(R.id.txt_password);
        btnLogin    = (Button)yFindViewById(R.id.btn_login);
        btnRegister = (Button)yFindViewById(R.id.btn_register);
    }

}
