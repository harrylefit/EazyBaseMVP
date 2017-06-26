package vn.eazy.base.mvp.intergration;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
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
    private FragmentLifeCycle mFragmentLifeCycle;
    private List<FragmentManager.FragmentLifecycleCallbacks> mFragmentLifeCycles;

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

        if (activity instanceof FragmentActivity) {
            if (mFragmentLifeCycle == null) {
                mFragmentLifeCycle = new FragmentLifeCycle();
            }
            ((FragmentActivity) activity).getSupportFragmentManager().registerFragmentLifecycleCallbacks(mFragmentLifeCycle, true);
        }

        if (mFragmentLifeCycles == null) {
            mFragmentLifeCycles = new ArrayList<>();
            List<ConfigModule> configModules = (List<ConfigModule>) mExtras.get(ConfigModule.class.getSimpleName());
            for (ConfigModule module : configModules) {
                module.injectFragmentLifeCycles(mApplication, mFragmentLifeCycles);
            }
        }

        for (FragmentManager.FragmentLifecycleCallbacks lifecycleCallbacks : mFragmentLifeCycles) {
            ((FragmentActivity) activity).getSupportFragmentManager().registerFragmentLifecycleCallbacks(lifecycleCallbacks, true);
        }

    }

    @Override
    public void onActivityStarted(Activity activity) {
        ActivityDelegate delegate = getActivityDelegate(activity);
        if (delegate != null) {
            delegate.onStart();
        }
    }

    @Override
    public void onActivityResumed(Activity activity) {
        ActivityDelegate delegate = getActivityDelegate(activity);
        if (delegate != null) {
            delegate.onResume();
        }
    }

    @Override
    public void onActivityPaused(Activity activity) {
        ActivityDelegate delegate = getActivityDelegate(activity);
        if (delegate != null) {
            delegate.onPause();
        }
    }

    @Override
    public void onActivityStopped(Activity activity) {
        ActivityDelegate delegate = getActivityDelegate(activity);
        if (delegate != null) {
            delegate.onStop();
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        ActivityDelegate delegate = getActivityDelegate(activity);
        if (delegate != null) {
            delegate.onSaveInstanceState(outState);
        }
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
