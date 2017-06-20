package vn.eazy.base.mvp.intergration;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import vn.eazy.base.mvp.delegate.ActivityDelegate;
import vn.eazy.base.mvp.delegate.IActivity;

/**
 * Created by harryle on 6/18/17.
 */
@Singleton
public class ActivityLifeCycle implements Application.ActivityLifecycleCallbacks {
    private Application mApplication;
    private Map<String, Object> mExtras;

    @Inject
    public ActivityLifeCycle(Application application, Map<String, Object> extras) {
        this.mApplication = application;
        this.mExtras = extras;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        if (activity instanceof IActivity && activity.getIntent() != null) {
            ActivityDelegate activityDelegate = getActivityDelegate(activity);
            if (activityDelegate == null) {
                activityDelegate = new ActivityDelegate(activity);
                activity.getIntent().putExtra(ActivityDelegate.ACTIVITY_DELEGATE, activityDelegate);
            }
            activityDelegate.onCreate(savedInstanceState);
        }
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        ActivityDelegate activityDelegate = getActivityDelegate(activity);
        if (activityDelegate != null) {
            activityDelegate.onDestroy();
            activity.getIntent().removeExtra(ActivityDelegate.ACTIVITY_DELEGATE);
        }
    }

    private ActivityDelegate getActivityDelegate(Activity activity) {
        ActivityDelegate activityDelegate = null;
        if (activity instanceof IActivity && activity.getIntent() != null) {
            activityDelegate = (ActivityDelegate) activity.getIntent()
                    .getSerializableExtra(ActivityDelegate.ACTIVITY_DELEGATE);
        }
        return activityDelegate;
    }
}
