package com.semirsuljevic.foundation.api.common

sealed interface HechimResource <out T : Any, out U : Any>{
    data class Success<T: Any>(val date: T) : HechimResource<T, Nothing>
    data class Error<U: Any>(val response: U) : HechimResource<Nothing, U>
    data class Loading(val message: String): HechimResource<String, Nothing>
}
