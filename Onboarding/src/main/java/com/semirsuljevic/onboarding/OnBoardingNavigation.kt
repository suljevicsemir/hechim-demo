package com.semirsuljevic.onboarding

import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.semirsuljevic.onboarding.welcome.ui.OnBoardingRoute
import com.semirsuljevic.onboarding.welcome.viewmodel.OnBoardingViewModel

fun NavGraphBuilder.onBoardingNavGraph(
    viewModelStoreOwner: ViewModelStoreOwner
) {
    composable(route = "OnBoarding") {
        val onBoardingViewModel = viewModel<OnBoardingViewModel>(viewModelStoreOwner)
        OnBoardingRoute(onBoardingViewModel)
    }
}
