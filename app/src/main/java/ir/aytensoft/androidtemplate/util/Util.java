package ir.aytensoft.androidtemplate.util;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import ir.aytensoft.androidtemplate.R;
import ir.aytensoft.androidtemplate.activity.MainActivity;
import ir.aytensoft.androidtemplate.model.User;
import ir.yooneskh.yutil.YActivity;
import ir.yooneskh.yutil.YNavigation;
import ir.yooneskh.yutil.YSocial;
import ir.yooneskh.yutil.YToaster;
import ir.yooneskh.yutil.analytic.YAnalytics;
import ir.yooneskh.yutil.appstore.YAppStore;
import ir.yooneskh.yutil.database.YDatabase;
import ir.yooneskh.yutil.database.YMemory;
import ir.yooneskh.yutil.json.YJsonObject;

/**
 * Created by YoonesKh on 2016/11/19.
 */
public class Util {

    public static String endpoint(String path) {
        return "http://sarzaminsher.yooneskh.ir/api/" + path;
    }

    public static String endpointTokened(String path) {
        return "http://sarzaminsher.yooneskh.ir/api/" + path + "?api_token=" + Util.getToken();
    }

    public static void saveUser(User user) {
        YDatabase.put("logged-user", user);
    }

    public static User getUser() {
        return YDatabase.get("logged-user", new User());
    }

    public static void deleteUser() {
        YDatabase.remove("logged-user");
    }

    public static boolean authCheckExec(YActivity activity, int httpCode, String result) {
        if (httpCode == 401) {
            YToaster.shortToast(activity, "لطفا دوباره به اکانت خود وارد شوید");
            Util.deleteToken();
            YNavigation.launchActivityClearing(activity, MainActivity.class);
            YMemory.put("should-login", true);
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean normalCheckExec(YActivity activity, int httpCode, String result) {

        YJsonObject data = new YJsonObject(result);

        if (httpCode != 200 || result == null || data == null) {
            YToaster.shortToast(activity, "مشکلی در ارتباط پیش آمده است. لطفا دوباره تلاش کنید");
            return true;
        }

        if (!data.getBoolean("status")) {
            YToaster.shortToast(activity, data.getString("error_message_persian"));
            return true;
        }

        return false;

    }

    public static String getToken() {
        return YDatabase.get("token", "no-token");
    }

    public static void saveToken(String token) {
        YDatabase.put("token", token);
    }

    public static void deleteToken() {
        YDatabase.remove("token");
    }

    public static boolean isLoggedIn() {
        return YDatabase.contains("token");
    }

    public static void makeDoubleTapable(final Context context, View view, final Runnable runnable) {

        final GestureDetector gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                runnable.run();
                return true;
            }
            @Override
            public void onLongPress(MotionEvent e) {
                super.onLongPress(e);
            }
            @Override
            public boolean onDoubleTapEvent(MotionEvent e) {
                return true;
            }
            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }
        });

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });

    }

    public static Drawer makeDrawer(final YActivity activity, Toolbar toolbar) {

        DrawerBuilder builder = new DrawerBuilder().withActivity(activity);

        builder.withToolbar(toolbar);
        builder.withAccountHeader(makeDrawerHeader(activity));

        builder.addDrawerItems(
            drawerPrimaryItem().withName("درباره ما").withIcon(R.drawable.itm_gl_abtus).withTag("about-us"),
            drawerPrimaryItem().withName("ارتباط با ما").withIcon(R.drawable.itm_contact2).withTag("contact-us"),
            drawerPrimaryItem().withName("امتیاز به برنامه").withIcon(R.drawable.itm_rate2).withTag("rate"),
            drawerPrimaryItem().withName("برنامه های دیگر").withIcon(R.drawable.itm_others2).withTag("other-apps"),
            new DividerDrawerItem(),
            drawerPrimaryItem().withName("کانال تلگرام").withIcon(R.drawable.itm_mnu_telegram).withTag("telegram"),
            drawerPrimaryItem().withName("صفحه اینستاگرام").withIcon(R.drawable.itm_mnu_insta).withTag("instagram"),
            new DividerDrawerItem(),
            drawerPrimaryItem().withName("فرستادن فایل برنامه").withIcon(R.drawable.mnu_itm_pic_sendfile).withTag("send-file"),
            drawerPrimaryItem().withName("فرستادن لینک برنامه").withIcon(R.drawable.mnu_itm_pic_sendlink).withTag("send-link"),
            new DividerDrawerItem(),
            drawerPrimaryItem().withName("خروج").withIcon(R.drawable.itm_gl_exit).withTag("exit")
        );

        builder.withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                Util.processDrawerItemSelect(activity, drawerItem.getTag().toString());
                return false;
            }
        });

        builder.withSelectedItem(-1);

        return builder.build();

    }

    private static AccountHeader makeDrawerHeader(YActivity activity) {

        AccountHeaderBuilder builder = new AccountHeaderBuilder();

        builder.withActivity(activity);

        builder.addProfiles(
                new ProfileDrawerItem().withIcon(activity.getResources().getDrawable(R.mipmap.ic_launcher))
        );

        return builder.build();

    }

    private static void processDrawerItemSelect(YActivity activity, String tag) {
        switch (tag) {
            case "about-us":
//                YNavigation.launchActivity(activity, AboutUsActivity.class);
                break;
            case "contact-us":
                YSocial.openTelegramId(activity, "sarzamin_admin");
                YAnalytics.log("Social",
                        "Action", "Contact Us"
                );
                break;
            case "send-poem":
                YSocial.openTelegramId(activity, "SarzaminappBot");
                YAnalytics.log("Social",
                        "Action", "Send Poem"
                );
                break;
            case "rate":
                YAppStore.showCommenting(activity);
                YAnalytics.log("App Store",
                        "Action", "Commenting"
                );
                break;
            case "other-apps":
                YAppStore.showCollection(activity);
                YAnalytics.log("App Store",
                        "Action", "Collection"
                );
                break;
            case "telegram":
                YSocial.openTelegramId(activity, "sarzamin_sheer");
                YAnalytics.log("Social",
                        "Action", "Telegram Channel"
                );
                break;
            case "instagram":
                YSocial.openInstagramId(activity, "sarzamin_sheer");
                YAnalytics.log("Social",
                        "Action", "Instagram Page"
                );
                break;
            case "send-file":
                YSocial.shareAppAPK(activity);
                YAnalytics.log("Social",
                        "Action", "Share APK"
                );
                break;
            case "send-link":
                YSocial.shareSimpleTextGeneral(activity, "لینک دانلود «سرزمین شعر»", Util.makeShareText(activity), "به اشتراک بگذارید");
                YAnalytics.log("Social",
                        "Action", "Share Text"
                );
                break;
        }
    }

    private static String makeShareText(Activity activity) {
        return  "دانلود نرم افزار «سرزمین شعر»"+ "\n\n" +
                "مجموعه اشعار و عکسنوشته های شاعران معاصر ایرانی" + "\n\n" +
                YAppStore.getLink(activity);
    }

    private static PrimaryDrawerItem drawerPrimaryItem() {
        return new PrimaryDrawerItem();
    }

    private static SecondaryDrawerItem drawerSecondaryItem() {
        return new SecondaryDrawerItem();
    }

}
