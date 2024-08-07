package com.hechimdemo.dashboard.api.more.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semirsuljevic.foundation.api.authentication.HechimAuthentication
import com.semirsuljevic.onboarding.api.welcome.ui.onboarding.RouteOnBoarding
import com.semirsuljevic.ui.api.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoreViewModel @Inject constructor(
    private val hechimAuthentication: HechimAuthentication,
    private val navigator: Navigator
): ViewModel(){

    fun logOut() {
        viewModelScope.launch {
            hechimAuthentication.logOut()
            navigator.navigateAndRemove(RouteOnBoarding())
        }
    }
}
