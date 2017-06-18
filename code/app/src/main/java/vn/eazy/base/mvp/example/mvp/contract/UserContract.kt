package vn.eazy.base.mvp.example.mvp.contract

import com.tbruyelle.rxpermissions.RxPermissions
import io.reactivex.Flowable
import net.idik.lib.slimadapter.SlimAdapter
import vn.eazy.base.mvp.architect.IModel
import vn.eazy.base.mvp.architect.IView
import vn.eazy.base.mvp.example.mvp.model.entity.User

/**
 * Created by harryle on 6/11/17.
 */
interface UserContract {
    interface View : IView {
        fun setAdapter(slimAdapter: SlimAdapter)
        fun startLoadMore()
        fun endLoadMore()

        fun getRxPermissions(): RxPermissions
    }

    interface Model : IModel {
        fun getUsers(): Flowable<List<User>>
    }
}