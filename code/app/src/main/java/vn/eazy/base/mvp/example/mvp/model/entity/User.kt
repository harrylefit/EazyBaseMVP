package vn.eazy.base.mvp.example.mvp.model.entity

import vn.eazy.base.mvp.architect.BaseModel
import vn.eazy.base.mvp.example.mvp.contract.UserContract

/**
 * Created by harryle on 6/11/17.
 */
data class User(val user: Int? = -1, val title: String? = "", val body: String? = "body")