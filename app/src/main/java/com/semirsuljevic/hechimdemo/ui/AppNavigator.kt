package com.semirsuljevic.hechimdemo.ui

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.semirsuljevic.hechimdemo.viewmodel.MainViewModel
import com.semirsuljevic.onboarding.api.onBoardingNavGraph

@Composable
fun AppNavigator(
    navController: NavHostController = rememberNavController(),
    viewModelStoreOwner: ViewModelStoreOwner
) {
    val context = LocalContext.current

    val mainViewModel: MainViewModel = viewModel(context as ComponentActivity)


    LaunchedEffect(Unit) {
        mainViewModel.setNavigator(navController)
    }

    NavHost(
        navController = navController,
        startDestination = "OnBoarding",
    ) {
        onBoardingNavGraph(viewModelStoreOwner)
    }
}
