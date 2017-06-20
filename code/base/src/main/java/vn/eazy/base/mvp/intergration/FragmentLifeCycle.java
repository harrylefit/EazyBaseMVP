package vn.eazy.base.mvp.intergration;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import javax.inject.Singleton;

import vn.eazy.base.mvp.delegate.FragmentDelegate;
import vn.eazy.base.mvp.delegate.IFragment;

/**
 * Created by harryle on 6/20/17.
 */
public class FragmentLifeCycle extends FragmentManager.FragmentLifecycleCallbacks {
    @Override
    public void onFragmentCreated(FragmentManager fm, Fragment f, Bundle savedInstanceState) {
        super.onFragmentCreated(fm, f, savedInstanceState);
        Log.d("TAG", "onFragmentCreated life cycle");
    }

    @Override
    public void onFragmentActivityCreated(FragmentManager fm, Fragment f, Bundle savedInstanceState) {
        super.onFragmentActivityCreated(fm, f, savedInstanceState);
        FragmentDelegate delegate = getFragmentDelegate(f);
        if (delegate != null) {
            delegate.onActivityCreated(savedInstanceState);
        }
    }

    @Override
    public void onFragmentAttached(FragmentManager fm, Fragment f, Context context) {
        super.onFragmentAttached(fm, f, context);
        if (f instanceof IFragment && f.getArguments() != null) {
            FragmentDelegate delegate = getFragmentDelegate(f);
            if (delegate == null) {
                delegate = new FragmentDelegate(f);
                f.getArguments().putSerializable(FragmentDelegate.FRAGMENT_LIFECYCLE, delegate);
            }
            delegate.onAttach(context);
        }
    }

    @Override
    public void onFragmentResumed(FragmentManager fm, Fragment f) {
        super.onFragmentResumed(fm, f);
        FragmentDelegate delegate = getFragmentDelegate(f);
        if (delegate != null) {
            delegate.onResume();
        }
    }

    @Override
    public void onFragmentStarted(FragmentManager fm, Fragment f) {
        super.onFragmentStarted(fm, f);
        FragmentDelegate delegate = getFragmentDelegate(f);
        if (delegate != null) {
            delegate.onStart();
        }
    }

    @Override
    public void onFragmentPaused(FragmentManager fm, Fragment f) {
        super.onFragmentPaused(fm, f);
        FragmentDelegate delegate = getFragmentDelegate(f);
        if (delegate != null) {
            delegate.onPause();
        }
    }

    @Override
    public void onFragmentDetached(FragmentManager fm, Fragment f) {
        super.onFragmentDetached(fm, f);
        FragmentDelegate delegate = getFragmentDelegate(f);
        if (delegate != null) {
            delegate.onDetach();
        }
    }

    @Override
    public void onFragmentDestroyed(FragmentManager fm, Fragment f) {
        super.onFragmentDestroyed(fm, f);
        FragmentDelegate delegate = getFragmentDelegate(f);
        if (delegate != null) {
            delegate.onDestroy();
        }
    }

    @Override
    public void onFragmentViewDestroyed(FragmentManager fm, Fragment f) {
        super.onFragmentViewDestroyed(fm, f);
        FragmentDelegate delegate = getFragmentDelegate(f);
        if (delegate != null) {
            delegate.onDestroyView();
        }
    }

    private FragmentDelegate getFragmentDelegate(Fragment fragment) {
        if (fragment instanceof IFragment) {
            return (FragmentDelegate) fragment.getArguments().getSerializable(FragmentDelegate.FRAGMENT_LIFECYCLE);
        }
        return null;
    }
}
