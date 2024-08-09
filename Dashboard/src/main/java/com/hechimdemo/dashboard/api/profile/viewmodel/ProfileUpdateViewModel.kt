package com.hechimdemo.dashboard.api.profile.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semirsuljevic.foundation.api.common.HechimResource
import com.semirsuljevic.foundation.api.user.Profile
import com.semirsuljevic.foundation.api.user.ProfileProvider
import com.semirsuljevic.foundation.api.user.model.HechimUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileUpdateViewModel @Inject constructor(
    private val profile: Profile,
    private val profileProvider: ProfileProvider
):ViewModel(){

    private val _user = mutableStateOf(HechimUser())

    /** Text field's value for the user's first name */
    private val _firstName = mutableStateOf("")
    val firstName get() = _firstName.value

    /** Text field's value for the user's last name */
    private val _lastName = mutableStateOf("")
    val lastName get() = _lastName.value

    private val _state = mutableStateOf<HechimResource<Boolean>>(HechimResource.Nothing())
    val state get() = _state.value

    init {
        collectFlow()
    }

    /** Compares saved user to edited one. If values are different, button to update
      is enabled. Otherwise it's disabled. (first name and last name) */
    val firstNameButton get() = _user.value.firstName != _firstName.value
    val lastNameButton get() = _user.value.lastName != _lastName.value

    /* Collect's user from data store. Sets value to _user
    so we can compare it edited values. */
    private fun collectFlow() {
        viewModelScope.launch {
            profileProvider.profileFlow.collectLatest {
                _user.value = it
                _firstName.value = _user.value.firstName
                _lastName.value = _user.value.lastName
            }
        }

    }

    /** Updates user, method will not be available unless the name changed. Used
     * by first name and last name. */
    fun updateName() {
        viewModelScope.launch {
            _state.value = HechimResource.Loading()
            delay(1000)
            _state.value = profile.updateName(
                firstName = _firstName.value,
                lastName = _lastName.value
            )
        }
    }

    fun setFirstName(value: String) {
        _firstName.value = value
    }

    fun setLastName(value: String) {
        _lastName.value = value
    }



}
