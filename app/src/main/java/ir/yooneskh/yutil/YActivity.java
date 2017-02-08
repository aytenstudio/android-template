package ir.yooneskh.yutil;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.mikepenz.materialdrawer.Drawer;

import ir.aytensoft.androidtemplate.R;
import ir.yooneskh.yutil.dialog.YDialoger;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Yoones on 05/08/2016.
 */

public abstract class YActivity extends AppCompatActivity {

    public final YActivity self = this;
    public Toolbar toolbar;
    public Drawer drawer;

    MaterialDialog progressDialog = null;

    public void yShowLoading(String title) {
        progressDialog = new MaterialDialog.Builder(this)
                .title(title)
                .progress(true, 0)
                .show();
    }

    public void yShowLoading(String title, String description) {
        progressDialog = new MaterialDialog.Builder(this)
                .title(title)
                .content(description)
                .progress(true, 0)
                .show();
    }

    public void yHideLoading() {
        progressDialog.hide();
        progressDialog = null;
    }

    public void ySetupToolbar() {
        toolbar = (Toolbar)findViewById(R.id._toolbar);
        setSupportActionBar(toolbar);
    }

    public void ySetTitle(String title) {
        getSupportActionBar().setTitle(getString(R.string.app_name) + " - " + title);
    }

    public void ySetup(boolean isRTL) {
        if (isRTL) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        }
    }

    public void yShowUpArrow() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/irs.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onBackPressed() {
        if (drawer != null && drawer.isDrawerOpen()) {
            drawer.closeDrawer();
        }
        else {
            super.onBackPressed();
        }
    }

}
