package vn.eazy.base.mvp.utils;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle2.components.support.RxFragment;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import vn.eazy.base.mvp.architect.IView;

/**
 * Created by harryle on 6/21/17.
 */

public class RxUtils {
    public static LifecycleProvider bindToLifeCycle(IView view) {
        if (view instanceof RxAppCompatActivity) {
            return (LifecycleProvider<ActivityEvent>) view;
        } else if (view instanceof RxFragment) {
            return (LifecycleProvider<Fragment>) view;
        } else {
            throw new IllegalArgumentException("Unable find fragment or activity properly");
        }
    }

    public static <T> FlowableTransformer<T, T> applySchedules(final IView view) {
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(Flowable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .doOnSubscribe(new Consumer<Subscription>() {
                            @Override
                            public void accept(Subscription subscription) throws Exception {
                                view.showLoading();
                            }
                        })
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnTerminate(new Action() {
                            @Override
                            public void run() throws Exception {
                                view.hideLoading();
                            }
                        });
            }
        };
    }
}
