package com.semirsuljevic.ui.api.common

sealed interface HechimResource <out T : Any, out U : Any>{
    data class Success<T: Any>(val date: T) : HechimResource<T, kotlin.Nothing>
    data class Error<U: Any>(val response: U) : HechimResource<kotlin.Nothing, U>
    data class Loading(val message: String): HechimResource<String, kotlin.Nothing>
    data class Nothing(val message: String): HechimResource<kotlin.Nothing, kotlin.Nothing>
}
