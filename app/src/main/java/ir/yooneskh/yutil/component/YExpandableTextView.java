package ir.yooneskh.yutil.component;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import ir.aytensoft.androidtemplate.R;

/**
 * Created by YoonesKh on 2016/12/10.
 */

public class YExpandableTextView extends TextView {

    private static final int DEFAULT_TRIM_LENGTH = 69;
    private static final String ELLIPSIS = "\n«" + "ادامه متن" + " ...»";

    private CharSequence originalText;
    private CharSequence trimmedText;
    private BufferType bufferType;
    private boolean trim = true;
    private int trimLength;

    public YExpandableTextView(Context context) {
        this(context, null);
    }

    public YExpandableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.trimLength = DEFAULT_TRIM_LENGTH;

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                trim = !trim;
                setText();
                requestFocusFromTouch();
            }
        });

    }

    private void setText() {
        super.setText(getDisplayableText(), bufferType);
    }

    private CharSequence getDisplayableText() {
        return trim ? trimmedText : originalText;
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        originalText = text;
        trimmedText = getTrimmedText(text);
        bufferType = type;
        setText();
    }

    private CharSequence getTrimmedText(CharSequence text) {
        if (originalText != null && originalText.length() > trimLength) {
            Spannable res = new SpannableStringBuilder(originalText, 0, trimLength + 1).append(ELLIPSIS);
            String str = res.toString();
            res.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.accent)), str.indexOf(ELLIPSIS), str.indexOf(ELLIPSIS) +  ELLIPSIS.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return res;
        } else {
            return originalText;
        }
    }

    public CharSequence getOriginalText() {
        return originalText;
    }

    public void setTrimLength(int trimLength) {
        this.trimLength = trimLength;
        trimmedText = getTrimmedText(originalText);
        setText();
    }

    public int getTrimLength() {
        return trimLength;
    }

    public void setTrim(boolean trim) {
        this.trim = trim;
    }

}
