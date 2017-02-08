package ir.aytensoft.androidtemplate.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.aytensoft.androidtemplate.R;
import ir.aytensoft.androidtemplate.databinding.ActivityZtemplateBinding;
import ir.aytensoft.androidtemplate.util.Config;
import ir.yooneskh.yutil.YActivity;

public class ZTemplateActivity extends YActivity {

    ActivityZtemplateBinding refs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        refs = DataBindingUtil.setContentView(self, R.layout.activity_ztemplate);
        ySetup(Config.isRtl);
        ySetupToolbar();
        yCreate();
    }

    @BindView(R.id.txtTest)
    TextView txtTest;

    private void yCreate() {

        ButterKnife.bind(self);

        refs.txtTest.setText("felan");
        txtTest.setText("bisar");

    }

}
