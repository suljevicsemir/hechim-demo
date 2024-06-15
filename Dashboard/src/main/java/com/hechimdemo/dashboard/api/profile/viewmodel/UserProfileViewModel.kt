package com.hechimdemo.dashboard.api.profile.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semirsuljevic.foundation.api.user.model.HechimUser
import com.semirsuljevic.foundation.api.common.HechimResource
import com.semirsuljevic.foundation.api.user.Profile
import com.semirsuljevic.foundation.api.user.ProfileProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val profileProvider: ProfileProvider,
    private val profile: Profile
):ViewModel(){

    private val _user = mutableStateOf<HechimResource<HechimUser>>(HechimResource.Loading())
    val user get() = _user.value

    val userContent get(): HechimUser {
        if(_user.value is HechimResource.Success) {
            return (_user.value as HechimResource.Success).data
        }
        return HechimUser(email = "")
    }



    fun collectProfile() {
        viewModelScope.launch {
            launch {
                profileProvider.profileFlow.collectLatest {
                    _user.value = HechimResource.Success(it)
                }
            }
            profile.getUser()

        }
    }


}
