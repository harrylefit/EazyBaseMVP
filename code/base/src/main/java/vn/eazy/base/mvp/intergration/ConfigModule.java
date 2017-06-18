package vn.eazy.base.mvp.intergration;

import android.content.Context;

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

}
