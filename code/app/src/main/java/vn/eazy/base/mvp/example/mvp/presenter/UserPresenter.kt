package vn.eazy.base.mvp.example.mvp.presenter

import android.app.Application
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

import javax.inject.Inject

import vn.eazy.base.mvp.architect.BasePresenter
import vn.eazy.base.mvp.di.scope.ActivityScope
import vn.eazy.base.mvp.example.mvp.contract.UserContract

/**
 * Created by harryle on 6/18/17.
 */
@ActivityScope
class UserPresenter @Inject
constructor(model: UserContract.Model, view: UserContract.View, val application: Application) : BasePresenter<UserContract.Model, UserContract.View>(model, view) {

    override fun useEventBus(): Boolean {
        return false
    }

    fun getUsers() {
        mModel.getUsers().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ Log.d("TAG", "data : " + it.toString()) }, { it.printStackTrace() })
    }

}
