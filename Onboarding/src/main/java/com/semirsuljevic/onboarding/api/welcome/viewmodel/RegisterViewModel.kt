package com.semirsuljevic.onboarding.api.welcome.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semirsuljevic.foundation.api.authentication.CredentialsValidator
import com.semirsuljevic.foundation.api.authentication.HechimAuthentication
import com.semirsuljevic.foundation.api.authentication.model.HechimUser
import com.semirsuljevic.foundation.api.common.HechimResource
import com.semirsuljevic.ui.api.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val navigator: Navigator,
    private val authentication: HechimAuthentication,
    private val credentialsValidator: CredentialsValidator
): ViewModel(){

    private val _resource = mutableStateOf<HechimResource<HechimUser>>(HechimResource.Nothing(""))
    val resource = _resource.value

    private val _password = mutableStateOf("")
    val password: String = _password.value

    private val _confirmPassword = mutableStateOf("")
    val confirmPassword: String = _confirmPassword.value

    private var _email: String = ""
    fun setEmail(value: String) {
        _email = value
    }

    fun setPassword(value: String) {
        _password.value = value
    }

    fun setConfirmPassword(value: String) {
        _confirmPassword.value = value
    }

    fun register() {
        if(!validatePasswords()) {
            return
        }
        viewModelScope.launch {
            _resource.value = HechimResource.Loading("")
            authentication.register(
                email = _email,
                password = _password.value
            )
        }
    }

    private fun validatePasswords(): Boolean{
        return credentialsValidator.validatePassword(_password.value)
            && credentialsValidator.validateConfirmPassword(
            _password.value,
            _confirmPassword.value
        )
    }

}
