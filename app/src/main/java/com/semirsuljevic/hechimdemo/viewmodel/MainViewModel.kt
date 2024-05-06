package com.semirsuljevic.hechimdemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.semirsuljevic.onboarding.api.welcome.ui.email.RouteEmail
import com.semirsuljevic.onboarding.api.welcome.ui.language.RouteLanguageSelection
import com.semirsuljevic.onboarding.api.welcome.ui.onboarding.RouteOnBoarding
import com.semirsuljevic.ui.api.navigation.HechimRoute
import com.semirsuljevic.ui.api.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val navigator: Navigator
): ViewModel(){


    fun setNavigator(navController: NavHostController) {
        navigator.setNavController(navController)
    }

    val startDestination : HechimRoute get() {
        val onBoarding = RouteOnBoarding()
        return onBoarding
    }
}
