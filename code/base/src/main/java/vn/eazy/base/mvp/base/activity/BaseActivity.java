package vn.eazy.base.mvp.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import butterknife.Unbinder;
import butterknife.ButterKnife;
import vn.eazy.base.mvp.architect.IPresenter;
import vn.eazy.base.mvp.base.OnBaseActionListener;
import vn.eazy.base.mvp.delegate.IActivity;
import vn.eazy.base.mvp.di.component.AppComponent;

/**
 * Created by Harry on 12/23/16.
 */
public abstract class BaseActivity<P extends IPresenter> extends AppCompatActivity implements OnBaseActionListener, IActivity {
    @Inject
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
        mPresenter = null;
    }

}
