package vn.eazy.base.mvp.example.mvp.presenter

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import vn.eazy.base.mvp.architect.BasePresenter
import vn.eazy.base.mvp.example.mvp.contract.UserContract
import vn.eazy.base.mvp.example.mvp.model.api.service.UserService
import vn.eazy.base.mvp.example.mvp.network.NetworkUtils

/**
 * Created by harryle on 6/11/17.
 */
class UserPresenter(view: UserContract.View) : BasePresenter<UserContract.Model, UserContract.View>(view) {
    fun requestUsers() {
        val userService = NetworkUtils.getRetrofit()!!.create(UserService::class.java)
        userService.getUsers().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("Tag", "Data :" + it.toString())
                }) { it.printStackTrace() }
    }

    override fun useEventBus(): Boolean {
        return false
    }
}