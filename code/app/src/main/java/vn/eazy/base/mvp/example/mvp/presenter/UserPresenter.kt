package vn.eazy.base.mvp.example.mvp.presenter

import android.app.Application
import android.util.Log
import com.trello.rxlifecycle2.android.FragmentEvent
import com.trello.rxlifecycle2.components.support.RxFragment
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import com.trello.rxlifecycle2.kotlin.bindUntilEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

import javax.inject.Inject

import vn.eazy.base.mvp.architect.BasePresenter
import vn.eazy.base.mvp.di.scope.ActivityScope
import vn.eazy.base.mvp.example.mvp.contract.UserContract
import vn.eazy.base.mvp.utils.RxUtils

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
        mModel.getUsers()
                .compose(RxUtils.applySchedules(mView))
                .bindUntilEvent(mView as RxFragment, FragmentEvent.PAUSE)
                .subscribe({ Log.d("TAG", "data : " + it.toString()) }, { it.printStackTrace() })
    }

}
