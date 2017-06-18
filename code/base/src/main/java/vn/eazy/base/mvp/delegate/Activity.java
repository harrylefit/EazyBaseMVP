package vn.eazy.base.mvp.delegate;

import android.os.Bundle;

/**
 * Created by harryle on 6/18/17.
 */

public interface Activity {
    /**
     * @param savedInstanceState
     */
    void onCreate(Bundle savedInstanceState);

    /**
     *
     */
    void onDestroy();
}
