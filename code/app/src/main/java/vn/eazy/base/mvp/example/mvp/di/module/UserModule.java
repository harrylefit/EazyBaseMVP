package vn.eazy.base.mvp.example.mvp.di.module;

import dagger.Module;
import dagger.Provides;
import vn.eazy.base.mvp.di.scope.ActivityScope;
import vn.eazy.base.mvp.example.mvp.contract.UserContract;
import vn.eazy.base.mvp.example.mvp.model.UserModel;

/**
 * Created by harryle on 6/17/17.
 */
@Module
public class UserModule {
    private UserContract.View mView;

    public UserModule(UserContract.View view) {
        this.mView = view;
    }

    @ActivityScope
    @Provides
    UserContract.View provideUserView() {
        return this.mView;
    }

    @ActivityScope
    @Provides
    UserContract.Model provideUserModel(UserModel model) {
        return model;
    }
}
