package ir.aytensoft.androidtemplate.binding;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.view.View;

/**
 * Created by YoonesKh on 2017/02/08.
 */

public class TemplateState extends BaseObservable {

    ObservableInt felan = new ObservableInt(-1);
    ObservableField<String> bisar = new ObservableField<>("");

    public int show(boolean criteria) {
        return criteria ? View.VISIBLE : View.GONE;
    }

}
