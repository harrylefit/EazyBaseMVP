package vn.eazy.base.mvp.example

import android.os.Bundle
import vn.eazy.base.mvp.base.activity.BaseMainActivity
import vn.eazy.base.mvp.toolbar.ToolbarHelper

class MainActivity : BaseMainActivity<ToolbarHelper>() {
    override fun setUpViewsAndData() {
    }

    override fun getLayoutId(): Int {
        return vn.eazy.base.mvp.R.layout.activity_main_default
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
//        toolbarHelper.showLeftButton(false)
    }
}
