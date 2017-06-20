package vn.eazy.base.mvp.delegate;

import android.os.Bundle;

import java.io.Serializable;

import butterknife.ButterKnife;
import vn.eazy.base.mvp.utils.PreConditions;

/**
 * Created by harryle on 6/18/17.
 */

public class ActivityDelegate implements Activity, Serializable {
    public static final String ACTIVITY_DELEGATE = "activity_delegate";

    private android.app.Activity mActivity;
    private IActivity iActivity;

    public ActivityDelegate(android.app.Activity activity) {
        this.mActivity = activity;
        this.iActivity = (IActivity) activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        iActivity.setupActivityComponent(((App) mActivity.getApplication()).getAppComponent());
        int layoutResId = iActivity.initView(savedInstanceState);
        if (layoutResId != 0) {
            mActivity.setContentView(layoutResId);
        } else {
            throw new RuntimeException("LayoutResID isn't illegal. Please check it again.");
        }
        iActivity.initData(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        this.iActivity = null;
        this.mActivity = null;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public void onStop() {

    }
}
