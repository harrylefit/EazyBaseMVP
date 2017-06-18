package vn.eazy.base.mvp.intergration;

/**
 * Created by harryle on 6/10/17.
 */

public interface IRepositoryManager {
    /**
     * @param services
     */
    void injectRetrofitService(Class<?>... services);

    /**
     * @param service
     * @param <T>
     * @return
     */
    <T> T obtainRetrofitServices(Class<T> service);


}
