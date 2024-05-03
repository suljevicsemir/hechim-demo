package com.semirsuljevic.onboarding.api

import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.semirsuljevic.onboarding.api.welcome.ui.OnBoardingRoute
import com.semirsuljevic.onboarding.api.welcome.ui.language.LanguageSelectionRoute
import com.semirsuljevic.onboarding.api.welcome.viewmodel.LanguageSelectionViewModel
import com.semirsuljevic.onboarding.api.welcome.viewmodel.OnBoardingViewModel

fun NavGraphBuilder.onBoardingNavGraph(
    viewModelStoreOwner: ViewModelStoreOwner
) {
    composable(route = "OnBoarding") {
        val onBoardingViewModel = viewModel<OnBoardingViewModel>(viewModelStoreOwner)
        OnBoardingRoute(onBoardingViewModel)
    }
    composable(route = "Language") {
        val languageSelectionViewModel = viewModel<LanguageSelectionViewModel>(viewModelStoreOwner)
        LanguageSelectionRoute(
            languageSelectionViewModel,
            onContinue = {
            }
        )
    }

}
