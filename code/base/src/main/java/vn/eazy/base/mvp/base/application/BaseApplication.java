package vn.eazy.base.mvp.base.application;

import android.app.Application;

import vn.eazy.base.mvp.delegate.App;
import vn.eazy.base.mvp.delegate.AppDelegate;
import vn.eazy.base.mvp.di.component.AppComponent;

/**
 * Created by harryle on 6/17/17.
 */

public class BaseApplication extends Application implements App {
    private AppDelegate mAppDelegate;

    @Override
    public void onCreate() {
        super.onCreate();
        this.mAppDelegate = new AppDelegate(this);
        this.mAppDelegate.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        if (mAppDelegate != null) {
            mAppDelegate.onTerminal();
        }
    }

    @Override
    public AppComponent getAppComponent() {
        return mAppDelegate.getAppComponent();
    }
}
