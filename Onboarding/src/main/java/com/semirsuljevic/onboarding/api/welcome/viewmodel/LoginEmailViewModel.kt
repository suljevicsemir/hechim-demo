package com.semirsuljevic.onboarding.api.welcome.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.semirsuljevic.ui.api.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginEmailViewModel @Inject constructor(
    private val navigator: Navigator
): ViewModel(){
    private val _email = mutableStateOf("")
    val email: String get() = _email.value

    fun onEmailChange(value: String) {
        _email.value = value
    }

    /**
        Checks email for authentication.
        If email doesn't exist - redirect to create password page where account will be created.
        If email exists - redirect to password page.
     */
    fun checkEmail() {
        //email doesn't exists
        //navigator.navigate("CreatePassword")
        //email exists
        //navigator.navigate("Password")
    }

    fun navigate(route: String) {
        navigator.navigate(route)
    }
}
