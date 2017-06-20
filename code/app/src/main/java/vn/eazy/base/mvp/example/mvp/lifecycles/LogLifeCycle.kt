package vn.eazy.base.mvp.example.mvp.lifecycles

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log

/**
 * Created by harryle on 6/20/17.
 */
class LogLifeCycle : Application.ActivityLifecycleCallbacks {
    override fun onActivityPaused(activity: Activity?) {

    }

    override fun onActivityResumed(activity: Activity?) {
    }

    override fun onActivityStarted(activity: Activity?) {
    }

    override fun onActivityDestroyed(activity: Activity?) {
    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
    }

    override fun onActivityStopped(activity: Activity?) {
    }

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        Log.d("TAG", "onActivityCreated Log")
    }

}