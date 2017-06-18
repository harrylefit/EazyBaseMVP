package vn.eazy.base.mvp.example.mvp.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by harryle on 6/11/17.
 */
object NetworkUtils {
    private var mRetrofit: Retrofit? = null

    fun getRetrofit(): Retrofit? {
        if (mRetrofit == null) {
            mRetrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl("http://demo6594088.mockable.io")
                    .build()
        }
        return mRetrofit
    }
}