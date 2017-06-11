package vn.eazy.base.mvp.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;

import java.util.ArrayList;
import java.util.List;

import vn.eazy.base.mvp.R;
import vn.eazy.base.mvp.helper.ChangeTextSizeHelper;
import vn.eazy.base.mvp.helper.FontHelper;
import vn.eazy.base.mvp.utils.PreferencesUtils;
import vn.eazy.base.mvp.utils.WidgetUtils;

/**
 * Created by Harry on 12/24/16.
 */

public abstract class EazyTextView extends AppCompatTextView {
    private List<String> fontArrays;

    public EazyTextView(Context context) {
        super(context);
        init(context, null);
    }

    public EazyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public EazyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        setUpFontsArray();
        int type = 1;
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.EazyTextView);
            type = typedArray.getInt(R.styleable.EazyTextView_etv_font, 1);
            boolean enableChangeSize = typedArray.getBoolean(R.styleable.EazyTextView_etv_enableChangeSize, true);
            if (enableChangeSize) {
                float fontSizeRatio = PreferencesUtils.getFloat(getContext(), ChangeTextSizeHelper.FONT_SIZE);
                setFontSize(fontSizeRatio);
            }
            typedArray.recycle();
        }
        if (fontArrays.size() > 0) {
            FontHelper.setTypeFont(this, type, fontArrays);
        }
    }

    private void setUpFontsArray() {
        if (fontArrays == null) {
            fontArrays = new ArrayList<>();
        }
        fontArrays.clear();

        if (!TextUtils.isEmpty(createLightFont())) {
            fontArrays.add(createLightFont());
        }
        if (!TextUtils.isEmpty(createRegularFont())) {
            fontArrays.add(createRegularFont());
        }
        if (!TextUtils.isEmpty(createMediumFont())) {
            fontArrays.add(createMediumFont());
        }
        if (!TextUtils.isEmpty(createItalicFont())) {
            fontArrays.add(createItalicFont());
        }

        if (!TextUtils.isEmpty(createThinFont())) {
            fontArrays.add(createThinFont());
        }

        if (!TextUtils.isEmpty(createBoldFont())) {
            fontArrays.add(createBoldFont());
        }

        fontArrays.add(createSemiBoldFont());

        if (!TextUtils.isEmpty(createBlackFont())) {
            fontArrays.add(createBlackFont());
        }

        fontArrays.add(createBlackItalicFont());
        fontArrays.add(createBoldItalicFont());
        fontArrays.add(createRegularItalicFont());
        fontArrays.add(createUltraLightFont());
        fontArrays.add(createLightItalicFont());

        fontArrays.add(createOtherType1Font());
        fontArrays.add(createOtherType2Font());
        fontArrays.add(createOtherType3Font());
        fontArrays.add(createOtherType4Font());


        if (!TextUtils.isEmpty(createDefaultFont())) {
            fontArrays.add(createDefaultFont());
        }

    }

    protected abstract String createDefaultFont();

    protected abstract String createLightFont();

    protected abstract String createRegularFont();

    protected abstract String createMediumFont();

    protected abstract String createItalicFont();

    protected abstract String createThinFont();

    protected abstract String createBoldFont();

    protected abstract String createBlackFont();

    protected String createSemiBoldFont() {
        return createDefaultFont();
    }

    protected String createBlackItalicFont() {
        return createDefaultFont();
    }

    protected String createBoldItalicFont() {
        return createDefaultFont();
    }

    protected String createRegularItalicFont() {
        return createDefaultFont();
    }

    protected String createUltraLightFont() {
        return createDefaultFont();
    }

    protected String createLightItalicFont() {
        return createDefaultFont();
    }

    protected String createOtherType1Font() {
        return createRegularFont();
    }

    protected String createOtherType2Font() {
        return createDefaultFont();
    }

    protected String createOtherType3Font() {
        return createDefaultFont();
    }

    protected String createOtherType4Font() {
        return createDefaultFont();
    }

    public void setFontSize(float ratioSize) {
        if (ratioSize <= 0 || ratioSize == 1)
            return;
        setTextSize(TypedValue.COMPLEX_UNIT_SP, WidgetUtils.pixelsToSp(getContext(), getTextSize() * ratioSize));

    }


}
