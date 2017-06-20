package vn.eazy.base.mvp.delegate;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

/**
 * Created by harryle on 6/20/17.
 */

public interface Fragment {
    void onCreate(Bundle savedInstanceState);

    void onDestroy();

    void onDestroyView();

    void onResume();

    void onPause();

    void onActivityCreated(Bundle savedInstanceState);

    void onDetach();

    void onAttach(Context context);

    void onStart();

    void onCreateView(View view, Bundle savedInstanceState);

    void onSaveInstanceState(Bundle outState);
}
