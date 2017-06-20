package vn.eazy.base.mvp.example.mvp.contract

import com.tbruyelle.rxpermissions.RxPermissions

import net.idik.lib.slimadapter.SlimAdapter

import io.reactivex.Flowable
import vn.eazy.base.mvp.architect.IModel
import vn.eazy.base.mvp.architect.IView
import vn.eazy.base.mvp.example.mvp.model.entity.User

/**
 * Created by harryle on 6/18/17.
 */

interface UserContract {
    interface View : IView {
        fun setAdapter(adapter: SlimAdapter)

        fun startLoadMore()

        fun endLoadMore()

        fun rxPermissions(): RxPermissions
    }

    interface Model : IModel {
        fun getUsers(): Flowable<List<User>>
    }
}
