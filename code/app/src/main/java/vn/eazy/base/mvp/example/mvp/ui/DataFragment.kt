package vn.eazy.base.mvp.example.mvp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.tbruyelle.rxpermissions.RxPermissions
import net.idik.lib.slimadapter.SlimAdapter
import vn.eazy.base.mvp.base.fragment.BaseMainFragment
import vn.eazy.base.mvp.di.component.AppComponent
import vn.eazy.base.mvp.example.R
import vn.eazy.base.mvp.example.mvp.contract.UserContract
import vn.eazy.base.mvp.example.mvp.di.component.DaggerUserComponent
import vn.eazy.base.mvp.example.mvp.di.module.UserModule
import vn.eazy.base.mvp.example.mvp.presenter.UserPresenter
import javax.inject.Inject

/**
 * Created by harryle on 6/20/17.
 */
class DataFragment : BaseMainFragment<UserPresenter>(), UserContract.View {
    @Inject
    lateinit var gson :Gson
    companion object {
        fun newInstance(data: Bundle?): DataFragment {
            val dataFragment: DataFragment = DataFragment()
            if (data != null) {
                dataFragment.arguments = data
            } else {
                dataFragment.arguments = Bundle()
            }
            return dataFragment
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_data
    }

    override fun setupFragmentComponent(appComponent: AppComponent?) {
        DaggerUserComponent.builder()
                .appComponent(appComponent)
                .userModule(UserModule(this))
                .build()
                .inject(this)
    }

    override fun useEventBus(): Boolean {
        return false
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter.getUsers()
    }

    override fun initData(savedInstanceState: Bundle?) {

    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showMessage(message: String?) {
    }

    override fun setAdapter(adapter: SlimAdapter) {
    }

    override fun startLoadMore() {
    }

    override fun endLoadMore() {
    }

    override fun rxPermissions(): RxPermissions {
        return RxPermissions(activity)
    }

}