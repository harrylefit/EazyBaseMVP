package vn.eazy.base.mvp.example.mvp.di.module

import android.app.Application
import android.content.Context
import android.util.Log

import com.google.gson.GsonBuilder

import retrofit2.Retrofit
import vn.eazy.base.mvp.architect.BaseModel
import vn.eazy.base.mvp.delegate.AppDelegate
import vn.eazy.base.mvp.di.module.AppModule
import vn.eazy.base.mvp.di.module.ClientModule
import vn.eazy.base.mvp.di.module.GlobalModule
import vn.eazy.base.mvp.example.mvp.model.api.service.UserService
import vn.eazy.base.mvp.intergration.ConfigModule
import vn.eazy.base.mvp.intergration.IRepositoryManager

/**
 * Created by harryle on 6/17/17.
 */

class GlobalConfiguration : ConfigModule {
    override fun applyOptions(context: Context, builder: GlobalModule.Builder) {
        builder.baseUrl("https://github.com")
        builder.retrofitConfiguration { context, builder -> Log.d("TAG", "Config retrofit") }
        builder.gsonConfiguration { context, builder -> Log.d("TAG", "Config Gson") }
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
}
