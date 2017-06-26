package vn.eazy.base.mvp.di.module;

import android.app.Application;
import android.os.Build;
import android.support.annotation.Nullable;

import java.io.File;
import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import vn.eazy.base.mvp.intergration.handler.error.listener.ResponseErrorListener;
import vn.eazy.base.mvp.utils.DataHelper;

/**
 * Created by harryle on 6/17/17.
 */
@Module
public class GlobalModule {
    private File mCacheFile;
    private AppModule.GsonConfiguration mGsonConfiguration;
    private ClientModule.OkHttpConfiguration mOkHttpConfiguration;
    private ClientModule.RetrofitConfiguration mRetrofitConfiguration;
    private List<Interceptor> mInterceptors;
    private ResponseErrorListener mResponseErrorListener;
    private String baseUrl;

    private GlobalModule(Builder builder) {
        this.mCacheFile = builder.cacheFile;
        this.baseUrl = builder.baseUrl;
        this.mGsonConfiguration = builder.gsonConfiguration;
        this.mInterceptors = builder.interceptors;
        this.mRetrofitConfiguration = builder.retrofitConfiguration;
        this.mOkHttpConfiguration = builder.okHttpConfiguration;
        this.mResponseErrorListener = builder.responseErrorListener;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Provides
    @Singleton
    File provideCacheFile(Application application) {
        return mCacheFile == null ? DataHelper.getCacheFile(application) : mCacheFile;
    }

    @Provides
    @Singleton
    @Nullable
    AppModule.GsonConfiguration provideGsonConfiguartion() {
        return this.mGsonConfiguration;
    }

    @Provides
    @Singleton
    @Nullable
    List<Interceptor> provideInterceptors() {
        return mInterceptors;
    }

    @Provides
    @Singleton
    @Named("baseUrl")
    String provideBaseUrl() {
        return baseUrl;
    }

    @Provides
    @Singleton
    @Nullable
    ClientModule.RetrofitConfiguration provideRetrofitConfiguration() {
        return this.mRetrofitConfiguration;
    }

    @Provides
    @Singleton
    @Nullable
    ClientModule.OkHttpConfiguration provideOkHttpConfiguration() {
        return this.mOkHttpConfiguration;
    }

    @Provides
    @Singleton
    HttpUrl provideHttpUrl(@Named("baseUrl") String baseUrl) {
        return HttpUrl.parse(baseUrl);
    }

    @Provides
    @Singleton
    ResponseErrorListener provideErrorListener() {
        return mResponseErrorListener;
    }

    public static final class Builder {
        private File cacheFile;
        private AppModule.GsonConfiguration gsonConfiguration;
        private List<Interceptor> interceptors;
        private String baseUrl;
        private ClientModule.RetrofitConfiguration retrofitConfiguration;
        private ClientModule.OkHttpConfiguration okHttpConfiguration;
        private ResponseErrorListener responseErrorListener;

        private Builder() {

        }

        public Builder cacheFile(File cacheFile) {
            this.cacheFile = cacheFile;
            return this;
        }

        public Builder gsonConfiguration(AppModule.GsonConfiguration gsonConfiguration) {
            this.gsonConfiguration = gsonConfiguration;
            return this;
        }

        public Builder interceptors(List<Interceptor> interceptors) {
            this.interceptors = interceptors;
            return this;
        }

        public Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder retrofitConfiguration(ClientModule.RetrofitConfiguration retrofitConfiguration) {
            this.retrofitConfiguration = retrofitConfiguration;
            return this;
        }

        public Builder okHttpConfiguration(ClientModule.OkHttpConfiguration okHttpConfiguration) {
            this.okHttpConfiguration = okHttpConfiguration;
            return this;
        }

        public Builder responseErrorListener(ResponseErrorListener responseErrorListener) {
            this.responseErrorListener = responseErrorListener;
            return this;
        }

        public GlobalModule build() {
            return new GlobalModule(this);
        }
    }
}
