package vn.eazy.base.mvp.intergration;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.internal.Preconditions;
import retrofit2.Retrofit;
import vn.eazy.base.mvp.utils.PreConditions;

/**
 * Created by harryle on 6/17/17.
 */
@Singleton
public class RepositoryManager implements IRepositoryManager {
    private Retrofit mRetrofit;

    private final Map<String, Object> mRetrofitServiceCache = new LinkedHashMap<>();

    @Inject
    public RepositoryManager(Retrofit retrofit) {
        this.mRetrofit = retrofit;
    }

    @Override
    public void injectRetrofitService(Class<?>... services) {
        for (Class<?> service : services) {
            if (!mRetrofitServiceCache.containsKey(service.getSimpleName())) {
                mRetrofitServiceCache.put(service.getSimpleName(), service);
            }
        }
    }

    @Override
    public <T> T obtainRetrofitServices(Class<T> service) {
        PreConditions.checkState(mRetrofitServiceCache.containsKey(service.getSimpleName()),
                "Unable find " + service.getSimpleName() + ",first call injectRetrofitServices it in ConfigModule");
        return (T) mRetrofitServiceCache.get(service.getSimpleName());
    }
}
