package ir.aytensoft.androidtemplate;

import android.os.Handler;
import android.os.Bundle;

import ir.yooneskh.yutil.YActivity;
import ir.yooneskh.yutil.YAnalytics;
import ir.yooneskh.yutil.YToaster;
import ir.yooneskh.yutil.appstore.YAppStore;
import ir.yooneskh.yutil.appstore.YBazaarStore;
import ir.yooneskh.yutil.database.YDatabase;
import ir.yooneskh.yutil.dialog.YDialoger;
import ir.yooneskh.yutil.versioning.YUpdateable;
import ir.yooneskh.yutil.versioning.YUpdater;

public class SplashActivity extends YActivity implements YUpdateable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ySetupToolbar();
        yCreate();
    }

    private void yCreate() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                goToNext();
            }
        }, 3085);

        // configurations

        YDatabase.init(self);
        YAnalytics.init(self, "4d21e1acd5474eb7dd83f25c59fc0105");

        YAppStore.appStore = new YBazaarStore();

        YUpdater.check(self, this);
        YUpdater.checkRemote(self, this);

    }

    private void goToNext() {
//        YNavigation.launchActivityFinishing(self, MainActivity.class);
        YToaster.shortToast(self, "To heavens and beyond ... ^_^");
    }

    @Override
    public void onFreshInstall() {
        YAnalytics.log("Install");
    }

    @Override
    public void onUpdate() {
        YAnalytics.log("Update");
    }

    @Override
    public void hasUpdate() {

        YDialoger.yesNo(
                self,
                "نسخه جدید",
                "نسخه جدیدی از برنامه موجود هست. می خواهید به روز رسانی را انجام دهید؟",
                new Runnable() {
                    @Override
                    public void run() {
                        YAppStore.showSelf(self);
                    }
                },
                null
        );

    }

}
