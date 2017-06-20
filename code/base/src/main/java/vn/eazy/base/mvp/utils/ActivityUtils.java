package vn.eazy.base.mvp.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


/**
 * Created by harryle on 6/19/17.
 */

public class ActivityUtils {
    public static void startActivity(Activity activity, Class targetActivity) {
        startActivity(activity, targetActivity, -1, null);
    }

    public static void startActivity(Activity activity, Class targetActivity, int requestCode) {
        startActivity(activity, targetActivity, requestCode, null);
    }

    public static void startActivity(Activity activity, Class targetActivity, Bundle data) {
        startActivity(activity, targetActivity, -1, data);
    }


    public static void startActivity(Activity activity, Class targetActivity, int requestCode, Bundle data) {
        Intent intent = new Intent(activity.getApplicationContext(), targetActivity);
        if (data != null) {
            intent.putExtras(data);
        }
        if (requestCode != -1) {
            activity.startActivityForResult(intent, requestCode);
        } else {
            activity.startActivity(intent);
        }
    }
}
