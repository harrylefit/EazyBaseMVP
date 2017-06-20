package vn.eazy.base.mvp.example.mvp.ui

import android.os.Bundle
import com.google.gson.Gson
import com.tbruyelle.rxpermissions.RxPermissions
import net.idik.lib.slimadapter.SlimAdapter
import vn.eazy.base.mvp.base.activity.BaseMainActivity
import vn.eazy.base.mvp.di.component.AppComponent
import vn.eazy.base.mvp.example.R
import vn.eazy.base.mvp.example.mvp.contract.UserContract
import vn.eazy.base.mvp.example.mvp.di.component.DaggerUserComponent
import vn.eazy.base.mvp.example.mvp.di.module.UserModule
import vn.eazy.base.mvp.example.mvp.presenter.UserPresenter
import vn.eazy.base.mvp.toolbar.ToolbarHelper
import javax.inject.Inject

class MainActivity : BaseMainActivity<UserPresenter, ToolbarHelper>(), UserContract.View {
    @Inject
    lateinit var gson: Gson

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
        return RxPermissions(this)
    }

    override fun initData(savedInstanceState: Bundle?) {
        mPresenter.getUsers()

        fragmentHelper.replaceFragment(DataFragment.newInstance(null))
    }

    override fun onColorOfToolbar(): Int {
        return R.color.colorPrimaryDark
    }

    override fun onImageForLeftButtonToolbar(): Int {
        return R.drawable.abc_btn_check_to_on_mtrl_000
    }

    override fun useFragmentState(): Boolean {
        return false
    }

    override fun showMenu(isShow: Boolean) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toolbarHelper.setTitle("Harry Le")
        toolbarHelper.showLeftButton(false)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return vn.eazy.base.mvp.R.layout.activity_main_default
    }

    override fun setupActivityComponent(appComponent: AppComponent?) {
        DaggerUserComponent.builder()
                .appComponent(appComponent)
                .userModule(UserModule(this))
                .build()
                .inject(this)
    }

}
