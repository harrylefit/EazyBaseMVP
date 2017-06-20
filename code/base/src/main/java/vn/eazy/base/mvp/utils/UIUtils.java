package vn.eazy.base.mvp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Message;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.security.MessageDigest;

/**
 * Created by harryle on 6/19/17.
 */

public class UIUtils {
    static public Toast mToast;

    public static int dip2px(Context context, float dpValue) {
        final float scale = getResources(context).getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);

    }

    public static Resources getResources(Context context) {
        return context.getResources();
    }

    public static String[] getStringArray(Context context, int id) {
        return getResources(context).getStringArray(id);
    }

    public static int pix2dip(Context context, int pix) {
        final float densityDpi = getResources(context).getDisplayMetrics().density;
        return (int) (pix / densityDpi + 0.5f);
    }

    public static int getDimens(Context context, int homePicHeight) {
        return (int) getResources(context).getDimension(homePicHeight);
    }

    public static float getDimens(Context context, String dimenNmae) {
        return getResources(context).getDimension(getResources(context).getIdentifier(dimenNmae, "dimen", context.getPackageName()));
    }

    public static String getString(Context context, int stringID) {
        return getResources(context).getString(stringID);
    }

    public static String getString(Context context, String strName) {
        return getString(context, getResources(context).getIdentifier(strName, "string", context.getPackageName()));
    }

    public static View inflate(Context context, int detailScreen) {
        return View.inflate(context, detailScreen, null);
    }

    public static void makeText(Context context, String string) {
        if (mToast == null) {
            mToast = Toast.makeText(context, string, Toast.LENGTH_SHORT);
        }
        mToast.setText(string);
        mToast.show();
    }

    public static Drawable getDrawablebyResource(Context context, int rID) {
        return getResources(context).getDrawable(rID);
    }

    public static int getScreenWidth(Context context) {
        return getResources(context).getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeidth(Context context) {
        return getResources(context).getDisplayMetrics().heightPixels;
    }

    public static int getColor(Context context, int rid) {
        return getResources(context).getColor(rid);
    }

    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        return false;
    }

    public static String encodeToMD5(String string) {
        byte[] hash = new byte[0];
        try {
            hash = MessageDigest.getInstance("MD5").digest(
                    string.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) {
                hex.append("0");
            }
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }

    public static void statuInScreen(Activity activity) {
        WindowManager.LayoutParams attrs = activity.getWindow().getAttributes();
        attrs.flags &= ~WindowManager.LayoutParams.FLAG_FULLSCREEN;
        activity.getWindow().setAttributes(attrs);
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    public static void configRecycleView(final RecyclerView recyclerView
            , RecyclerView.LayoutManager layoutManager) {
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

}

