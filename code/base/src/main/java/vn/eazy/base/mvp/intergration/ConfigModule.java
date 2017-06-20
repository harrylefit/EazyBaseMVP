package vn.eazy.base.mvp.intergration;

import android.app.Application;
import android.content.Context;
import android.support.v4.app.FragmentManager;

import java.util.List;

import vn.eazy.base.mvp.delegate.AppDelegate;
import vn.eazy.base.mvp.di.module.GlobalModule;

/**
 * Created by harryle on 6/17/17.
 */

public interface ConfigModule {
    /**
     * @param context
     * @param builder
     */
    void applyOptions(Context context, GlobalModule.Builder builder);

    /**
     * @param context
     * @param repositoryManager
     */
    void registerComponents(Context context, IRepositoryManager repositoryManager);

    /**
     * @param context
     * @param lifeCycles
     */
    void injectAppLifeCycles(Context context, List<AppDelegate.LifeCycle> lifeCycles);

    /**
     * @param context
     * @param activityLifeCycles
     */
    void injectActivityLifeCycles(Context context, List<Application.ActivityLifecycleCallbacks> activityLifeCycles);

    /**
     * @param context
     * @param fragmentLifecycleCallbacks
     */
    void injectFragmentLifeCycles(Context context, List<FragmentManager.FragmentLifecycleCallbacks> fragmentLifecycleCallbacks);
}
