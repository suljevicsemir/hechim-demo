package com.semirsuljevic.foundation.api.authentication

import com.semirsuljevic.foundation.api.authentication.model.HechimUser
import com.semirsuljevic.foundation.api.common.HechimResource

interface HechimAuthentication {

    suspend fun checkEmail(email: String): Boolean
    suspend fun login(email: String, password: String): HechimResource<HechimUser>
    suspend fun register(email: String, password: String): HechimResource<HechimUser>
}
