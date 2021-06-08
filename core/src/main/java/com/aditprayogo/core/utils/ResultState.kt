package com.aditprayogo.core.utils

/**
 * Created by Aditiya Prayogo.
 */
sealed class ResultState<out T : Any> {
    data class Success<out T : Any>(val data : T?) : ResultState<T>()
    data class Error(val error : String?, val statusCode : Int) : ResultState<String>()
    object NetworkError : ResultState<Nothing>()
}