package vn.eazy.base.mvp.example.mvp.lifecycles

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.util.Log

/**
 * Created by harryle on 6/20/17.
 */

class LogFragmentLifeCycle : FragmentManager.FragmentLifecycleCallbacks() {
    override fun onFragmentAttached(fm: FragmentManager?, f: Fragment?, context: Context?) {
        super.onFragmentAttached(fm, f, context)
        Log.d("TAG", "onFragmentAttached Log LifeCycle")
    }

    override fun onFragmentCreated(fm: FragmentManager?, f: Fragment?, savedInstanceState: Bundle?) {
        super.onFragmentCreated(fm, f, savedInstanceState)
    }
}
