package ir.aytensoft.androidtemplate.activity;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;

import ir.aytensoft.androidtemplate.R;
import ir.aytensoft.androidtemplate.databinding.ActivitySplashBinding;
import ir.aytensoft.androidtemplate.util.Config;
import ir.yooneskh.yutil.YActivity;
import ir.yooneskh.yutil.YNavigation;
import ir.yooneskh.yutil.YToaster;
import ir.yooneskh.yutil.analytic.FabricCrashlytics;
import ir.yooneskh.yutil.analytic.YAnalytics;
import ir.yooneskh.yutil.appstore.YAppStore;
import ir.yooneskh.yutil.appstore.YBazaarStore;
import ir.yooneskh.yutil.database.YDatabase;
import ir.yooneskh.yutil.database.YHawkDatabase;

public class SplashActivity extends YActivity {

    ActivitySplashBinding refs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        refs = DataBindingUtil.setContentView(self, R.layout.activity_splash);
//        ySetupToolbar();
        ySetup(Config.isRtl);
        yCreate();
    }

    private void yCreate() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                goToNext();
            }
        }, 2085);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        // configurations

        YDatabase.database = new YHawkDatabase();
        YAppStore.appStore = new YBazaarStore();
        YAnalytics.analytic = new FabricCrashlytics();

        YDatabase.init(self);
        YAnalytics.init(self, "4d21e1acd5474eb7dd83f25c59fc0105"); // this key is for amplitude, crashlytics key is in manifest

    }

    private void goToNext() {
//        YNavigation.launchActivityFinishing(self, MainActivity.class);
//        YToaster.shortToast(self, "To heavens and beyond ... ^_^");
        YNavigation.launchActivityClearing(self, EditProfileActivity.class);
    }

}
