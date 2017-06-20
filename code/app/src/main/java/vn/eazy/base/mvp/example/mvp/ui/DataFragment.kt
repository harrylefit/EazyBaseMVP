package vn.eazy.base.mvp.example.mvp.ui

import android.os.Bundle
import vn.eazy.base.mvp.base.fragment.BaseMainFragment
import vn.eazy.base.mvp.example.R

/**
 * Created by harryle on 6/20/17.
 */
class DataFragment : BaseMainFragment() {
    companion object {
        fun newInstance(data: Bundle?): DataFragment {
            val dataFragment: DataFragment = DataFragment();
            dataFragment.arguments = data
            return dataFragment
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_data
    }
}