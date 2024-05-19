package com.semirsuljevic.onboarding.api.welcome.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semirsuljevic.foundation.api.authentication.HechimAuthentication
import com.semirsuljevic.foundation.api.authentication.model.HechimUser
import com.semirsuljevic.foundation.api.common.HechimResource
import com.semirsuljevic.onboarding.api.welcome.ui.onboarding.RouteOnBoardingPop
import com.semirsuljevic.ui.api.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val navigator: Navigator,
    private val hechimAuthentication: HechimAuthentication
): ViewModel(){
    private val _resource = mutableStateOf<HechimResource<HechimUser>>(HechimResource.Nothing(""))
    val resource get() = _resource.value

    private val _password = mutableStateOf("")
    val password: String get() = _password.value

    private var _email: String = ""
    val email: String get() = _email

    fun setEmail(value: String) {
        _email = value
    }

    fun onPasswordChange(value: String) {
        _password.value = value
    }

    fun login() {
        viewModelScope.launch {
            _resource.value = HechimResource.Loading("Loggin you in")
            delay(1000)
            _resource.value = hechimAuthentication.login(
                email = _email,
                password = _password.value
            )
            if(_resource.value is HechimResource.Success) {
                navigator.navigateHome()
            }
        }
    }

    fun resetState() {
        _resource.value = HechimResource.Nothing()
    }

    fun navigate() {
        navigator.navigate(RouteOnBoardingPop())
    }
}
