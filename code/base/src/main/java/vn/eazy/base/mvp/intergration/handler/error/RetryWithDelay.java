package vn.eazy.base.mvp.intergration.handler.error;

import android.util.Log;

import org.reactivestreams.Publisher;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by harryle on 6/23/17.
 */

public class RetryWithDelay implements Function<Flowable<Throwable>, Publisher<?>> {
    public final String TAG = this.getClass().getSimpleName();
    private final int maxRetries;
    private final int retryDelaySecond;
    private int retryCount;

    public RetryWithDelay(int maxRetries, int retryDelaySecond) {
        this.maxRetries = maxRetries;
        this.retryDelaySecond = retryDelaySecond;
    }

    @Override
    public Publisher<?> apply(Flowable<Throwable> throwableFlowable) throws Exception {
        return throwableFlowable.flatMap(new Function<Throwable, Publisher<?>>() {
            @Override
            public Publisher<?> apply(Throwable throwable) throws Exception {
                if (++retryCount <= maxRetries) {
                    Log.d(TAG, "get error, it will try after  + retryDelaySecond" + " second, retry count  + retryCount");
                    return Flowable.timer(retryDelaySecond, TimeUnit.SECONDS);
                }
                return Flowable.error(throwable);
            }
        });
    }
}
