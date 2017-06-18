package vn.eazy.base.mvp.example.mvp.model.api.service

import io.reactivex.Flowable
import retrofit2.http.GET
import vn.eazy.base.mvp.example.mvp.model.entity.User

/**
 * Created by harryle on 6/11/17.
 */

interface UserService {
    @GET("/users")
    fun getUsers(): Flowable<List<User>>

}
