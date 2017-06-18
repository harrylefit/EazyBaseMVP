package vn.eazy.base.mvp.delegate;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import vn.eazy.base.mvp.di.component.AppComponent;
import vn.eazy.base.mvp.di.component.DaggerAppComponent;
import vn.eazy.base.mvp.di.module.AppModule;
import vn.eazy.base.mvp.di.module.ClientModule;
import vn.eazy.base.mvp.di.module.GlobalModule;
import vn.eazy.base.mvp.intergration.ActivityLifeCycle;
import vn.eazy.base.mvp.intergration.ConfigModule;
import vn.eazy.base.mvp.intergration.ManifestParser;

/**
 * Created by harryle on 6/17/17.
 */

public class AppDelegate implements App {
    private Application mApplication;
    private AppComponent mAppComponent;

//    @Inject
//    ActivityLifeCycle mActivityLifeCycle;

    private final List<ConfigModule> mConfigModules;
    private List<LifeCycle> mAppLifeCycles = new ArrayList<>();

    public AppDelegate(Application application) {
        this.mApplication = application;
        this.mConfigModules = new ManifestParser(application).parse();
        for (ConfigModule module : mConfigModules) {
            module.injectAppLifeCycles(mApplication, mAppLifeCycles);
        }
    }

    public void onCreate() {
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(mApplication))
                .clientModule(new ClientModule())
                .globalModule(getGlobalModule(mApplication, mConfigModules))
                .build();

        mAppComponent.inject(this);

        mAppComponent.extras().put(ConfigModule.class.getSimpleName(), mConfigModules);

        for (ConfigModule module : mConfigModules) {
            module.registerComponents(mApplication, mAppComponent.getIRepositoryManager());
        }

//        mApplication.registerActivityLifecycleCallbacks(mActivityLifeCycle);

        for (LifeCycle lifeCycle : mAppLifeCycles) {
            lifeCycle.onCreate(mApplication);
        }


    }

    public void onTerminal() {
        if (mAppLifeCycles != null) {
            for (LifeCycle lifeCycle : mAppLifeCycles) {
                lifeCycle.onTerminate(mApplication);
            }
        }

//        if (mActivityLifeCycle != null) {
//            mApplication.unregisterActivityLifecycleCallbacks(mActivityLifeCycle);
//        }

        this.mAppLifeCycles = null;
//        this.mActivityLifeCycle = null;
        this.mAppComponent = null;
        this.mApplication = null;


    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    public interface LifeCycle {
        void onCreate(Application application);

        void onTerminate(Application application);
    }

    private GlobalModule getGlobalModule(Application context, List<ConfigModule> modules) {
        GlobalModule.Builder builder = GlobalModule
                .builder();

        for (ConfigModule module : modules) {
            module.applyOptions(context, builder);
        }
        return builder.build();
    }

}
