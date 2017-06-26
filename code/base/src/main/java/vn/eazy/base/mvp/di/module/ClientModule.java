package vn.eazy.base.mvp.di.module;

import android.app.Application;
import android.content.Context;
import android.support.annotation.Nullable;

import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import vn.eazy.base.mvp.BuildConfig;
import vn.eazy.base.mvp.intergration.handler.error.RxErrorHandler;
import vn.eazy.base.mvp.intergration.handler.error.listener.ResponseErrorListener;

/**
 * Created by harryle on 6/17/17.
 */
@Module
public class ClientModule {
    private static final int CACHE_SIZE = 10 * 1024 * 1024; //10MiB
    private static final int CONNECT_TIMEOUT = 10;
    private static final int READ_TIMEOUT = 30;
    private static final int WRITE_TIMEOUT = 60;
    //Todo Application's injected from AppModule

    @Provides
    @Singleton
    Cache provideCache(File cacheFile) {
        Cache cache = new Cache(cacheFile, CACHE_SIZE);
        return cache;
    }

    @Provides
    @Singleton
    OkHttpClient.Builder provideOkHttpBuilder() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        return builder;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Application application, OkHttpClient.Builder builder, @Nullable Cache cache, @Nullable List<Interceptor> interceptors, @Nullable OkHttpConfiguration httpConfiguration) {
        builder.cache(cache)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(new LoggingInterceptor.Builder()
                        .loggable(BuildConfig.DEBUG)
                        .setLevel(Level.BODY)
                        .request("Request")
                        .response("Response")
                        .build());

        if (interceptors != null && !interceptors.isEmpty()) {
            for (Interceptor interceptor : interceptors) {
                builder.addInterceptor(interceptor);
            }
        }

        if (httpConfiguration != null) {
            httpConfiguration.configOkHttp(application, builder);
        }

        return builder.build();
    }

    @Provides
    @Singleton
    Retrofit.Builder provideRetrofitBuilder() {
        return new Retrofit.Builder();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Application application, OkHttpClient okHttpClient, Retrofit.Builder builder, HttpUrl httpUrl, @Nullable RetrofitConfiguration configuration) {
        builder.baseUrl(httpUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient);

        if (configuration != null) {
            configuration.configRetrofit(application, builder);
        }
        return builder.build();
    }

    @Provides
    @Singleton
    RxErrorHandler provideErrorHandler(Application application, ResponseErrorListener errorListener) {
        return RxErrorHandler.builder()
                .with(application)
                .responseErrorListener(errorListener)
                .build();
    }

    public interface OkHttpConfiguration {
        void configOkHttp(Context context, OkHttpClient.Builder builder);
    }

    public interface RetrofitConfiguration {
        void configRetrofit(Context context, Retrofit.Builder builder);
    }
}
