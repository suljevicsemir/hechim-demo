package com.semirsuljevic.onboarding.api.welcome.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.semirsuljevic.ui.api.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val navigator: Navigator
): ViewModel(){

    private val _password = mutableStateOf("")
    val password: String get() = _password.value

    fun onPasswordChange(value: String) {
        _password.value = value
    }


    fun login() {

    }
}
