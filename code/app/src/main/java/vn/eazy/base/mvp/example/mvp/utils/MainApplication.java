package vn.eazy.base.mvp.example.mvp.utils;

import android.content.Context;
import android.support.multidex.MultiDex;

import vn.eazy.base.mvp.base.application.BaseApplication;

/**
 * Created by harryle on 6/20/17.
 */

public class MainApplication extends BaseApplication {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
