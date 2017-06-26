package vn.eazy.base.mvp.example.mvp.model

import io.reactivex.Flowable
import vn.eazy.base.mvp.architect.BaseModel
import vn.eazy.base.mvp.di.scope.ActivityScope
import vn.eazy.base.mvp.example.mvp.contract.UserContract
import vn.eazy.base.mvp.example.mvp.model.api.service.UserService
import vn.eazy.base.mvp.example.mvp.model.entity.User
import vn.eazy.base.mvp.intergration.IRepositoryManager
import javax.inject.Inject

/**
 * Created by harryle on 6/11/17.
 */
@ActivityScope
class UserModel : BaseModel, UserContract.Model {
    @Inject
    constructor(repositoryManager: IRepositoryManager) : super(repositoryManager)

    companion object {
        val USER_PER_PAGE: Int = 10
    }

    override fun getUsers(): Flowable<List<User>> {
        val users: Flowable<List<User>> =
                mRepositoryManager.obtainRetrofitServices(UserService::class.java).getUsers()

        return users
    }

}