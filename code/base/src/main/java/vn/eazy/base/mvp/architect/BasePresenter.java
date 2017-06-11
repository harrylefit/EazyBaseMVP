package vn.eazy.base.mvp.architect;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by harryle on 6/10/17.
 */

public class BasePresenter<M extends IModel, V extends IView> implements IPresenter {
    protected final String TAG = this.getClass().getSimpleName();
    protected CompositeDisposable mCompositeDisposable;

    protected M mModel;
    protected V mView;

    public BasePresenter(M model, V view) {
        this.mModel = model;
        this.mView = view;
        onStart();
    }

    public BasePresenter(V view) {
        this.mView = view;
        onStart();
    }

    public BasePresenter() {
        onStart();
    }

    @Override
    public void onStart() {
        if (useEventBus()) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onDestroy() {
        if (useEventBus()) {
            EventBus.getDefault().unregister(this);
        }
        unDispose();
        if (mModel != null) {
            mModel.onDestroy();
        }
        this.mModel = null;
        this.mView = null;
        this.mCompositeDisposable = null;
    }

    protected boolean useEventBus() {
        return true;
    }

    protected void addDispose(Disposable disposable) {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.add(disposable);
        }
    }

    protected void unDispose() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }
}
