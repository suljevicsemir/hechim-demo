package com.hechimdemo.dashboard.profile

import com.semirsuljevic.foundation.api.user.model.HechimUser

object ProfileTestResponse {

    val user = HechimUser(firstName = "John", lastName = "Doe")
    val newUser = HechimUser(firstName = "JohnX", lastName = "Doe")
}
