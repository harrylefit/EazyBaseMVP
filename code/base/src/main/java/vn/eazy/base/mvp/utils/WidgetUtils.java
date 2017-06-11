package vn.eazy.base.mvp.utils;

import android.content.Context;

/**
 * Created by cuong on 2/28/17.
 */

public class WidgetUtils {
    public static float pixelsToSp(Context context, float px) {
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return px / scaledDensity;
    }
}
