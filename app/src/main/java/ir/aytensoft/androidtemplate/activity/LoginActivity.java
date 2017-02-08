package ir.aytensoft.androidtemplate.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.aytensoft.androidtemplate.R;
import ir.aytensoft.androidtemplate.databinding.ActivityLoginBinding;
import ir.aytensoft.androidtemplate.databinding.ActivityZtemplateBinding;
import ir.aytensoft.androidtemplate.fragment.LoginFragment;
import ir.aytensoft.androidtemplate.util.Config;
import ir.yooneskh.yutil.YActivity;
import ir.yooneskh.yutil.component.YPagerAdapter;

public class LoginActivity extends YActivity {

    ActivityLoginBinding refs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        refs = DataBindingUtil.setContentView(self, R.layout.activity_login);
        ySetup(Config.isRtl);
        ySetupToolbar();
        yShowUpArrow();
        yCreate();
    }

    private void yCreate() {

        refs.viewPager.setAdapter(new YPagerAdapter(getSupportFragmentManager(),
                new YPagerAdapter.YPagerElement(LoginFragment.class, self, R.layout.fragment_login)
        ));

    }

}
