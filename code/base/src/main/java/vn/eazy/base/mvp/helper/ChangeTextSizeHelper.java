package vn.eazy.base.mvp.helper;

import android.content.Context;

import vn.eazy.base.mvp.utils.PreferencesUtils;


/**
 * Created by cuong on 2/27/17.
 */

public class ChangeTextSizeHelper  {
    public static String FONT_SIZE = "font size";
    public static void setTextSizeRatio(Context context,float sizeRatio) {
        PreferencesUtils.putFloat(context, FONT_SIZE, sizeRatio);
    }



    public static float getTextSizeRatio(Context context) {
        return PreferencesUtils.getFloat(context, FONT_SIZE);
    }


}
