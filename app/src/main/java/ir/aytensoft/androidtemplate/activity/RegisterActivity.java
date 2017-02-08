package ir.aytensoft.androidtemplate.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.google.gson.JsonObject;

import ir.aytensoft.androidtemplate.R;
import ir.aytensoft.androidtemplate.databinding.ActivityRegisterBinding;
import ir.aytensoft.androidtemplate.model.User;
import ir.aytensoft.androidtemplate.util.Config;
import ir.aytensoft.androidtemplate.util.Util;
import ir.yooneskh.yutil.YActivity;
import ir.yooneskh.yutil.YNavigation;
import ir.yooneskh.yutil.YToaster;
import ir.yooneskh.yutil.analytic.YAnalytics;
import ir.yooneskh.yutil.json.YJsonBuilder;
import ir.yooneskh.yutil.json.YJsonObject;
import ir.yooneskh.yutil.network.YNetwork;
import ir.yooneskh.yutil.network.YNetworkResultProcessor;

public class RegisterActivity extends YActivity {

    ActivityRegisterBinding refs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        refs = DataBindingUtil.setContentView(self, R.layout.activity_register);
        ySetup(Config.isRtl);
        ySetupToolbar();
        yShowUpArrow();
        yCreate();
    }

    private void yCreate() {

        YAnalytics.log("Visit Page",
                "Page", "Register"
        );

        ySetTitle("ساخت حساب کاربری");

        refs.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doRegister();
            }
        });

    }

    private void doRegister() {

        String username = refs.txtUsername.getText().toString();
        String password = refs.txtPassword.getText().toString();

        if (!username.matches(".+@.+\\..+")) {
            YToaster.shortToast(self, "ایمیل وارد شده صحیح نیست");
            return;
        }

        if (password.length() < 8) {
            YToaster.shortToast(self, "طول رمز وارد شده باید حداقل 8 کاراکتر باشد");
            return;
        }

        JsonObject payload = YJsonBuilder.instantJsonObject(
                "username", username,
                "password", password
        );

        YNetwork.post(self, Util.endpoint("register"), payload, new YNetworkResultProcessor() {
            @Override
            public void process(int httpCode, String result) {

                if (Util.normalCheckExec(self, httpCode, result)) {
                    YAnalytics.log("Register",
                            "Status", false
                    ); return;
                }

                YJsonObject data = new YJsonObject(result);
                String token;
                User loggedUser;

                token = data.getString("token");
                loggedUser = User.fromJson(data.getYJsonObject("user"));

                Util.saveToken(token);
                Util.saveUser(loggedUser);

                YToaster.shortToast(self, "خوش آمدید");

                YAnalytics.log("Register",
                        "Status", true
                );

                YNavigation.launchActivityClearing(self, MainActivity.class);

            }
        });

    }

}
