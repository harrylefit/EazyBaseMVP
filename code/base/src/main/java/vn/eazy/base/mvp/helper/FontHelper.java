package vn.eazy.base.mvp.helper;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Harry on 12/24/16.
 */

public class FontHelper {
    private static final String TAG = FontHelper.class.getSimpleName();

    private static HashMap<String, Typeface> fonts = new HashMap<>();
    public static final int LIGHT_FONT = 0;
    public static final int REGULAR_FONT = 1;
    public static final int MEDIUM_FONT = 2;
    public static final int ITALIC_FONT = 3;
    public static final int THIN_FONT = 4;
    public static final int BOLD_FONT = 5;
    public static final int SEMI_BOLD_FONT = 6;
    public static final int BLACK_FONT = 7;
    public static final int BLACK_ITALIC_FONT = 8;
    public static final int BOLD_ITALIC_FONT = 9;
    public static final int REGULAR_ITALIC_FONT = 10;
    public static final int ULTRA_ITALIC_FONT = 11;
    public static final int LIGHT_ITALIC_FONT = 12;
    public static final int OTHER_TYPE_1_FONT = 13;
    public static final int OTHER_TYPE_2_FONT = 14;
    public static final int OTHER_TYPE_3_FONT = 15;
    public static final int OTHER_TYPE_4_FONT = 16;



    public static Typeface getFont(String font, Context context) {
        Typeface typeface = fonts.get(font);
        if (typeface == null) {
            typeface = Typeface.createFromAsset(context.getAssets(), font);
            fonts.put(font, typeface);
        }
        return typeface;
    }

    public static void setFont(TextView textView, String font) {
        try {
            Typeface typeface = getFont(font, textView.getContext());
            textView.setTypeface(typeface);
        } catch (Exception ex) {
            Log.i(TAG, "setFont: " + ex.getMessage());
        }
    }

    public static void setTypeFont(TextView textView, int type, List<String> fontsArrays) {
        setFont(textView, fontsArrays.get(type));
    }
}
