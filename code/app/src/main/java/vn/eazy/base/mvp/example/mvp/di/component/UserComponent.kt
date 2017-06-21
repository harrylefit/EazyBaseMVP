package vn.eazy.base.mvp.example.mvp.di.component

import dagger.Component
import vn.eazy.base.mvp.di.component.AppComponent
import vn.eazy.base.mvp.di.scope.ActivityScope
import vn.eazy.base.mvp.example.mvp.di.module.UserModule
import vn.eazy.base.mvp.example.mvp.ui.DataFragment
import vn.eazy.base.mvp.example.mvp.ui.MainActivity

/**
 * Created by harryle on 6/17/17.
 */
@ActivityScope
@Component(modules = arrayOf(UserModule::class), dependencies = arrayOf(AppComponent::class))
interface UserComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(dataFragment: DataFragment)
}
