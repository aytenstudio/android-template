package ir.aytensoft.androidtemplate.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import ir.aytensoft.androidtemplate.R;
import ir.aytensoft.androidtemplate.databinding.ActivityMainBinding;
import ir.aytensoft.androidtemplate.util.Config;
import ir.yooneskh.yutil.YActivity;
import ir.yooneskh.yutil.analytic.YAnalytics;
import ir.yooneskh.yutil.appstore.YAppStore;
import ir.yooneskh.yutil.dialog.YDialoger;
import ir.yooneskh.yutil.versioning.YCommenter;
import ir.yooneskh.yutil.versioning.YUpdateable;
import ir.yooneskh.yutil.versioning.YUpdater;

public class MainActivity extends YActivity implements YUpdateable {

    ActivityMainBinding refs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        refs = DataBindingUtil.setContentView(self, R.layout.activity_main);
        ySetup(Config.isRtl);
        ySetupToolbar();
        yCreate();
    }

    private void yCreate() {

        YUpdater.check(self, this);
        YUpdater.checkRemote(self, this);

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

    @Override
    public void onBackPressed() {
        if (drawer != null && drawer.isDrawerOpen()) {
            drawer.closeDrawer();
        }
        else {
            YCommenter.takeComment(self);
        }
    }

}
