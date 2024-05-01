package com.semirsuljevic.hechimdemo.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.semirsuljevic.onboarding.api.onBoardingNavGraph

@Composable
fun AppNavigator(
    navController: NavHostController = rememberNavController()
) {
    val context = LocalContext.current
    val viewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {}

    NavHost(
        navController = navController,
        startDestination = "OnBoarding",
    ) {
        onBoardingNavGraph(viewModelStoreOwner)
    }
}
