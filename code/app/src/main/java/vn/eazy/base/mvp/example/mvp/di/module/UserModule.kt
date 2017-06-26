package vn.eazy.base.mvp.example.mvp.di.module

import dagger.Module
import dagger.Provides
import vn.eazy.base.mvp.di.scope.ActivityScope
import vn.eazy.base.mvp.example.mvp.contract.UserContract
import vn.eazy.base.mvp.example.mvp.model.UserModel

/**
 * Created by harryle on 6/17/17.
 */
@Module
class UserModule(private val mView: UserContract.View) {

    @ActivityScope
    @Provides
    internal fun provideUserView(): UserContract.View {
        return this.mView
    }

    @ActivityScope
    @Provides
    internal fun provideUserModel(model: UserModel): UserContract.Model {
        return model
    }
}
