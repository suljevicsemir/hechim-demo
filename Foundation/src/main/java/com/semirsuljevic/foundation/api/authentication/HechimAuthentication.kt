package com.semirsuljevic.foundation.api.authentication

interface HechimAuthentication {

    suspend fun checkEmail(email: String): Boolean
    suspend fun login(email: String, password: String)
    suspend fun register(email: String, password: String)
}
