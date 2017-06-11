package vn.eazy.base.mvp.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.Unbinder;
import butterknife.ButterKnife;
import vn.eazy.base.mvp.base.OnBaseActionListener;

/**
 * Created by Harry on 12/23/16.
 */
public abstract class BaseActivity extends AppCompatActivity implements OnBaseActionListener {
    public Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        bindView();
        setUpViewsAndData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unBindView();
    }

    public void bindView() {
        unbinder = ButterKnife.bind(this);
    }

    public void unBindView() {
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    public abstract void setUpViewsAndData();

    public abstract int getLayoutId();

}
