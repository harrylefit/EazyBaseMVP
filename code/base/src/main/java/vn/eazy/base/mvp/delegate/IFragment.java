package vn.eazy.base.mvp.delegate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import vn.eazy.base.mvp.di.component.AppComponent;

/**
 * Created by harryle on 6/20/17.
 */

public interface IFragment {
    /**
     *
     * @param appComponent
     */
    void setupFragmentComponent(AppComponent appComponent);

    /**
     *
     * @return
     */
    boolean useEventBus();

    /**
     *
     * @param savedInstanceState
     */
    void initData(Bundle savedInstanceState);

}
