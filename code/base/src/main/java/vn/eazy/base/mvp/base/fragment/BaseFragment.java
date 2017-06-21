package vn.eazy.base.mvp.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle2.components.support.RxFragment;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import vn.eazy.base.mvp.architect.IPresenter;
import vn.eazy.base.mvp.base.CallbackObject;
import vn.eazy.base.mvp.base.activity.BaseActivity;
import vn.eazy.base.mvp.base.activity.BaseMainActivity;
import vn.eazy.base.mvp.delegate.IFragment;
import vn.eazy.base.mvp.helper.FragmentHelper;

/**
 * Created by Harry on 12/23/16.
 */

public abstract class BaseFragment<P extends IPresenter> extends RxFragment implements IFragment {
    @Inject
    public P mPresenter;

    protected View rootView;
    private Unbinder unbinder;
    private OnCallbackListener callbackListener;

    public interface OnCallbackListener {
        void onCallback(CallbackObject callbackObject);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        preInitLayout();
        rootView = LayoutInflater.from(getContext()).inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    public void preInitLayout() {
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindView();
        bindMenu();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unBindView();
        unBindMenu();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
        mPresenter = null;
    }

    public abstract int getLayoutId();

    public void bindView() {
    }

    public void unBindView() {
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    public void bindMenu() {
    }

    public void unBindMenu() {
    }

    public BaseActivity getBaseActivity() {
        if (getActivity() instanceof BaseActivity) {
            return (BaseActivity) getActivity();
        } else {
            throw new NullPointerException("Can't cast this activity to BaseActivity");
        }
    }

    public OnCallbackListener getCallbackListener() {
        return callbackListener;
    }

    public void setCallbackListener(OnCallbackListener callbackListener) {
        this.callbackListener = callbackListener;
    }

    public FragmentHelper getFragmentHelper() {
        if (getBaseActivity() instanceof BaseMainActivity) {
            return ((BaseMainActivity) getBaseActivity()).fragmentHelper;
        } else {
            throw new NullPointerException("Can't find Fragment Helper");
        }
    }

}
