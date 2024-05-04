package com.semirsuljevic.onboarding.api

import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.semirsuljevic.onboarding.api.welcome.ui.onboarding.OnBoardingScreen
import com.semirsuljevic.onboarding.api.welcome.ui.email.EmailScreen
import com.semirsuljevic.onboarding.api.welcome.ui.email.RouteEmail
import com.semirsuljevic.onboarding.api.welcome.ui.language.LanguageSelectionScreen
import com.semirsuljevic.onboarding.api.welcome.ui.language.RouteLanguageSelection
import com.semirsuljevic.onboarding.api.welcome.ui.login.LoginScreen
import com.semirsuljevic.onboarding.api.welcome.ui.login.RouteLogin
import com.semirsuljevic.onboarding.api.welcome.ui.onboarding.RouteOnBoarding
import com.semirsuljevic.onboarding.api.welcome.viewmodel.LanguageSelectionViewModel
import com.semirsuljevic.onboarding.api.welcome.viewmodel.LoginEmailViewModel
import com.semirsuljevic.onboarding.api.welcome.viewmodel.LoginViewModel
import com.semirsuljevic.onboarding.api.welcome.viewmodel.OnBoardingViewModel

fun NavGraphBuilder.onBoardingNavGraph(
    viewModelStoreOwner: ViewModelStoreOwner
) {
    composable(route = RouteOnBoarding().path) {
        val route: String? = it.arguments?.getString("route")
        val onBoardingViewModel = viewModel<OnBoardingViewModel>(viewModelStoreOwner)

        OnBoardingScreen(onBoardingViewModel)
    }
    composable(route = RouteLanguageSelection().path) {
        val languageSelectionViewModel = viewModel<LanguageSelectionViewModel>(viewModelStoreOwner)
        LanguageSelectionScreen(
            languageSelectionViewModel,
            onContinue = {
                languageSelectionViewModel.navigateToEmail()
            }
        )
    }
    composable(route = RouteEmail().path) {
        val loginEmailViewModel = viewModel<LoginEmailViewModel>(viewModelStoreOwner)
        EmailScreen(loginEmailViewModel)
    }
    composable(route = RouteLogin().path) {
        val loginEmailViewModel = viewModel<LoginEmailViewModel>(viewModelStoreOwner)
        val loginViewModel = viewModel<LoginViewModel>(viewModelStoreOwner)
        loginViewModel.setEmail(loginEmailViewModel.email)
        LoginScreen(loginViewModel = loginViewModel)
    }

}
