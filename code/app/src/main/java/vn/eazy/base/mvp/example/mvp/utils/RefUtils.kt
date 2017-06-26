package vn.eazy.base.mvp.example.mvp.utils

/**
 * Created by harryle on 6/20/17.
 */
fun <T : Any> put(value: T): Class<T> {
    val clazz = value.javaClass
    return clazz
}