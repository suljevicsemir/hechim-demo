package com.semirsuljevic.hechimdemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.hechimdemo.dashboard.api.ui.RouteDashboard
import com.semirsuljevic.foundation.api.authentication.HechimAuthentication
import com.semirsuljevic.onboarding.api.welcome.ui.email.RouteEmail
import com.semirsuljevic.onboarding.api.welcome.ui.language.RouteLanguageSelection
import com.semirsuljevic.onboarding.api.welcome.ui.onboarding.RouteOnBoarding
import com.semirsuljevic.ui.api.navigation.HechimRoute
import com.semirsuljevic.ui.api.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val navigator: Navigator,
    private val hechimAuthentication: HechimAuthentication
): ViewModel(){

    fun setupNavigation(navController: NavHostController) {
        navigator.setNavController(navController)
        navigator.setHome(RouteDashboard().path)
    }

    val startDestination : HechimRoute get() {
        if(hechimAuthentication.isAuthenticated()) {
            return RouteDashboard()
        }
        return RouteOnBoarding()
    }
}
