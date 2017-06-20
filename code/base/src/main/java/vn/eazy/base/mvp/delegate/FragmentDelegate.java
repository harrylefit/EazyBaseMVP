package vn.eazy.base.mvp.delegate;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;

/**
 * Created by harryle on 6/20/17.
 */

public class FragmentDelegate implements Fragment,Serializable {
    public static final String FRAGMENT_LIFECYCLE = "fragment_lifecycle";
    private IFragment iFragment;
    private android.support.v4.app.Fragment mFragment;

    public FragmentDelegate(android.support.v4.app.Fragment fragment) {
        this.iFragment = (IFragment) fragment;
        this.mFragment = fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (iFragment.useEventBus()) {
            EventBus.getDefault().register(mFragment);
        }
        iFragment.setupFragmentComponent(((App) mFragment.getActivity().getApplicationContext()).getAppComponent());
    }

    @Override
    public void onDestroy() {
        if (iFragment.useEventBus()) {
            EventBus.getDefault().unregister(mFragment);
        }
        this.mFragment = null;
        this.iFragment = null;
    }

    @Override
    public void onDestroyView() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        iFragment.initData(savedInstanceState);
    }

    @Override
    public void onDetach() {

    }

    @Override
    public void onAttach(Context context) {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onCreateView(View view, Bundle savedInstanceState) {
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }
}
