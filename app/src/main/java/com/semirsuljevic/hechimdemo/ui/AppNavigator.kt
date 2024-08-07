package com.semirsuljevic.hechimdemo.ui

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.hechimdemo.dashboard.api.navigation.dashboardNavGraph
import com.semirsuljevic.hechimdemo.viewmodel.MainViewModel
import com.semirsuljevic.onboarding.api.permissions.navigation.permissionsNavGraph
import com.semirsuljevic.onboarding.api.permissions.viewmodel.PermissionViewModel
import com.semirsuljevic.onboarding.api.trapdoor.viewmodel.TrapdoorViewModel
import com.semirsuljevic.onboarding.api.welcome.navigation.onBoardingNavGraph
import com.semirsuljevic.onboarding.internal.trapdoor.TrapdoorScreen
import com.semirsuljevic.ui.api.common.ComposableLifecycle

@Composable
fun AppNavigator(
    navController: NavHostController = rememberNavController(),
    viewModelStoreOwner: ViewModelStoreOwner
) {
    val context = LocalContext.current
    val trapdoorViewModel : TrapdoorViewModel = viewModel(context as ComponentActivity)



    val mainViewModel: MainViewModel = viewModel(context)
    val permissionsViewModel: PermissionViewModel = viewModel(context)

    ComposableLifecycle { _, event ->
        //ON_RESUME is triggered: cold start, warm start
        //view model checks if app should check trapdoor (permissions must be finished at least once)
        if(event == Lifecycle.Event.ON_RESUME) {
            trapdoorViewModel.checkTrapdoorPermission()
        }
        if(event == Lifecycle.Event.ON_CREATE) {
            permissionsViewModel.startPermissions()
        }
    }

    LaunchedEffect(Unit) {
        mainViewModel.setupNavigation(navController)
        mainViewModel.checkProfileTrapdoor()
    }

    NavHost(
        navController = navController,
        startDestination = mainViewModel.startDestination.path,
    ) {
        onBoardingNavGraph(viewModelStoreOwner)
        permissionsNavGraph(viewModelStoreOwner)
        dashboardNavGraph(
            viewModelStoreOwner,
        )
    }

    TrapdoorScreen(
        config = trapdoorViewModel.trapdoorConfig,
        trapdoorViewModel = trapdoorViewModel
    )

}
