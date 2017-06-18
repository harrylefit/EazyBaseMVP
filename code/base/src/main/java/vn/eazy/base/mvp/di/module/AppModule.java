package vn.eazy.base.mvp.di.module;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import vn.eazy.base.mvp.intergration.IRepositoryManager;
import vn.eazy.base.mvp.intergration.RepositoryManager;

/**
 * Created by harryle on 6/17/17.
 */
@Module
public class AppModule {
    private Application mApplication;

    public AppModule(Application application) {
        this.mApplication = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    Gson provideGson(Application application, @Nullable GsonConfiguration configuration) {
        GsonBuilder builder = new GsonBuilder();
        if (configuration != null) {
            configuration.configGson(application, builder);
        }
        return builder.create();
    }

    @Provides
    @Singleton
    Handler provideHandler() {
        return new Handler();
    }

    @Provides
    @Singleton
    IRepositoryManager provideRepositoryManager(RepositoryManager repositoryManager) {
        return repositoryManager;
    }

    @Provides
    @Singleton
    Map<String, Object> provideExtras() {
        return new HashMap<>();
    }

    public interface GsonConfiguration {
        void configGson(Context context, GsonBuilder builder);
    }


}
