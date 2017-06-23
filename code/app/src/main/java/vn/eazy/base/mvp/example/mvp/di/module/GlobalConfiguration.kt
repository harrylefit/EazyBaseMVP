package vn.eazy.base.mvp.example.mvp.di.module

import android.app.Application
import android.content.Context
import android.support.v4.app.FragmentManager
import android.util.Log
import vn.eazy.base.mvp.delegate.AppDelegate
import vn.eazy.base.mvp.di.module.GlobalModule
import vn.eazy.base.mvp.example.mvp.lifecycles.LogFragmentLifeCycle
import vn.eazy.base.mvp.example.mvp.lifecycles.LogLifeCycle
import vn.eazy.base.mvp.example.mvp.model.api.service.UserService
import vn.eazy.base.mvp.intergration.ConfigModule
import vn.eazy.base.mvp.intergration.IRepositoryManager

/**
 * Created by harryle on 6/17/17.
 */

class GlobalConfiguration : ConfigModule {
    override fun applyOptions(context: Context, builder: GlobalModule.Builder) {
        builder.baseUrl("http://demo6594088.mockable.io")
        builder.retrofitConfiguration { context, builder -> Log.d("TAG", "Config retrofit") }
        builder.gsonConfiguration { context, builder -> Log.d("TAG", "Config Gson") }
        builder.okHttpConfiguration { context, builder ->
            Log.d("TAG", "Config OkHttpConfiguration")
        }
        builder.responseErrorListener { context, builder -> Log.d("TAG", "Handle Response Error") }
    }

    override fun registerComponents(context: Context, repositoryManager: IRepositoryManager) {
        repositoryManager.injectRetrofitService(UserService::class.java)
    }

    override fun injectAppLifeCycles(context: Context, lifeCycles: MutableList<AppDelegate.LifeCycle>) {
        lifeCycles.add(object : AppDelegate.LifeCycle {
            override fun onCreate(application: Application) {
                Log.d("TAG", "OnCreate LifeCycle")
            }

            override fun onTerminate(application: Application) {
                Log.d("TAG", "OnTerminate LifeCycle")
            }
        })
    }

    override fun injectFragmentLifeCycles(context: Context?, fragmentLifecycleCallbacks: MutableList<FragmentManager.FragmentLifecycleCallbacks>?) {
        fragmentLifecycleCallbacks?.add(LogFragmentLifeCycle())
    }

    override fun injectActivityLifeCycles(context: Context?, activityLifeCycles: MutableList<Application.ActivityLifecycleCallbacks>?) {
        activityLifeCycles?.add(LogLifeCycle())
    }
}
