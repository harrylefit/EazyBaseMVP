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

    /**
     *
     */
    void onStart();

    /**
     *
     */
    void onResume();

    /**
     *
     */
    void onPause();

    /**
     * @param outState
     */
    void onSaveInstanceState(Bundle outState);

    /**
     *
     */
    void onStop();
}
