package vn.eazy.base.mvp.example.mvp.presenter

import android.app.Application
import android.util.Log
import com.trello.rxlifecycle2.android.FragmentEvent
import com.trello.rxlifecycle2.components.support.RxFragment
import com.trello.rxlifecycle2.kotlin.bindUntilEvent
import io.reactivex.functions.Consumer
import vn.eazy.base.mvp.architect.BasePresenter
import vn.eazy.base.mvp.di.scope.ActivityScope
import vn.eazy.base.mvp.example.mvp.contract.UserContract
import vn.eazy.base.mvp.example.mvp.model.entity.User
import vn.eazy.base.mvp.intergration.handler.error.ErrorHandleSubscriber
import vn.eazy.base.mvp.intergration.handler.error.RetryWithDelay
import vn.eazy.base.mvp.intergration.handler.error.RxErrorHandler
import vn.eazy.base.mvp.utils.RxUtils
import javax.inject.Inject

/**
 * Created by harryle on 6/18/17.
 */
@ActivityScope
class UserPresenter @Inject
constructor(model: UserContract.Model, view: UserContract.View, handler: RxErrorHandler) : BasePresenter<UserContract.Model, UserContract.View>(model, view, handler) {

    override fun useEventBus(): Boolean {
        return false
    }


    fun getUsers() {
        mModel.getUsers()
                .retryWhen { errors -> RetryWithDelay(3, 2).apply(errors) }
                .compose(RxUtils.applySchedules(mView))
                .bindUntilEvent(mView as RxFragment, FragmentEvent.PAUSE)
                .subscribe({ Log.d("TAG", it.toString()) })
    }

}
