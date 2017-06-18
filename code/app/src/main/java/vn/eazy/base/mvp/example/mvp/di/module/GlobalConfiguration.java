package vn.eazy.base.mvp.example.mvp.di.module;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Retrofit;
import vn.eazy.base.mvp.architect.BaseModel;
import vn.eazy.base.mvp.delegate.AppDelegate;
import vn.eazy.base.mvp.di.module.AppModule;
import vn.eazy.base.mvp.di.module.ClientModule;
import vn.eazy.base.mvp.di.module.GlobalModule;
import vn.eazy.base.mvp.example.mvp.model.api.service.UserService;
import vn.eazy.base.mvp.intergration.ConfigModule;
import vn.eazy.base.mvp.intergration.IRepositoryManager;

/**
 * Created by harryle on 6/17/17.
 */

public class GlobalConfiguration implements ConfigModule {
    @Override
    public void applyOptions(Context context, GlobalModule.Builder builder) {
        builder.baseUrl("https://github.com");
        builder.retrofitConfiguration(new ClientModule.RetrofitConfiguration() {
            @Override
            public void configRetrofit(Context context, Retrofit.Builder builder) {
                Log.d("TAG","Config retrofit");
            }
        });
        builder.gsonConfiguration(new AppModule.GsonConfiguration() {
            @Override
            public void configGson(Context context, GsonBuilder builder) {
                Log.d("TAG","Config Gson");
            }
        });
    }

    @Override
    public void registerComponents(Context context, IRepositoryManager repositoryManager) {
        repositoryManager.injectRetrofitService(UserService.class);
    }

    @Override
    public void injectAppLifeCycles(Context context, List<AppDelegate.LifeCycle> lifeCycles) {
        lifeCycles.add(new AppDelegate.LifeCycle() {
            @Override
            public void onCreate(Application application) {
                Log.d("TAG", "OnCreate LifeCycle");
            }

            @Override
            public void onTerminate(Application application) {
                Log.d("TAG", "OnTerminate LifeCycle");
            }
        });
    }
}
