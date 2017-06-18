package vn.eazy.base.mvp.example.mvp.ui

import android.content.Intent
import android.os.Bundle
import com.tbruyelle.rxpermissions.RxPermissions
import net.idik.lib.slimadapter.SlimAdapter
import vn.eazy.base.mvp.base.activity.BaseMainActivity
import vn.eazy.base.mvp.di.component.AppComponent
import vn.eazy.base.mvp.example.R
import vn.eazy.base.mvp.example.mvp.di.component.DaggerUserComponent
import vn.eazy.base.mvp.example.mvp.di.module.UserModule
import vn.eazy.base.mvp.example.mvp.presenter.UserPresenter
import vn.eazy.base.mvp.toolbar.ToolbarHelper

class MainActivity : BaseMainActivity<UserPresenter, ToolbarHelper>() {
    override fun initData(savedInstanceState: Bundle?) {

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

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showMessage(message: String?) {

    }

    override fun launchActivity(intent: Intent?) {

    }

    override fun killMySelf() {

    }

    override fun setAdapter(slimAdapter: SlimAdapter) {
    }

    override fun startLoadMore() {

    }

    override fun endLoadMore() {

    }

    override fun getRxPermissions(): RxPermissions {
        return RxPermissions(this)
    }

    override fun setupActivityComponent(appComponent: AppComponent?) {
        DaggerUserComponent.builder()
                .appComponent(appComponent)
                .userModule(UserModule(this))
                .build()
                .inject(this)
    }


}
