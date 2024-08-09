package com.hechimdemo.dashboard.api.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hechimdemo.dashboard.api.more.ui.DashboardMoreRoute
import com.hechimdemo.dashboard.api.navigation.DashboardHomeItem
import com.hechimdemo.dashboard.api.navigation.DashboardMoreItem
import com.hechimdemo.dashboard.api.navigation.DashboardProfileItem
import com.hechimdemo.dashboard.api.navigation.DashboardWorkoutsItem
import com.hechimdemo.dashboard.api.viewmodel.DashboardViewModel
import com.semirsuljevic.onboarding.api.trapdoor.viewmodel.TrapdoorViewModel
import com.semirsuljevic.ui.api.common.ComposableLifecycle
import com.semirsuljevic.ui.api.navbar.HechimNavigationBar
import com.semirsuljevic.ui.api.navigation.HechimRoute
import com.semirsuljevic.ui.api.screen.HechimScreen
import com.semirsuljevic.ui.api.screen.HechimScreenConfig
import com.semirsuljevic.ui.api.theme.HechimTheme

class RouteDashboard:HechimRoute("dashboard")

@Composable
fun DashboardNavBar(
    navController: NavHostController = rememberNavController(),
    viewModel: DashboardViewModel,
    trapdoorViewModel: TrapdoorViewModel = hiltViewModel()
) {

    ComposableLifecycle(
        onEvent = { _, event ->
            //ON_RESUME is called every time app is usable
            //back from background or system popup dismissed (permission)
            if(event == Lifecycle.Event.ON_RESUME) {
                trapdoorViewModel.checkTrapdoorPermission()
            }
        }
    )


    HechimScreen (
        config = HechimScreenConfig(
            canNavigateBack = false
        ),
        bottomBar = {
            HechimNavigationBar(
                navBarIndex = viewModel.navBarIndex,
                onClick = { index, item ->
                    viewModel.setNavBarIndex(index)
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                items = listOf(
                    DashboardHomeItem,
                    DashboardProfileItem,
                    DashboardMoreItem,
                    DashboardWorkoutsItem
                )
            )
        }
    ){
        NavHost(
            navController = navController,
            startDestination = DashboardHomeItem.route,
            modifier = Modifier
                .padding(it)
                .padding(horizontal = HechimTheme.sizes.medium)
        ) {
            composable(DashboardHomeItem.route) {
                Column (
                    verticalArrangement = Arrangement.Center
                ){
                    Text("Home")
                }
            }
            composable(DashboardProfileItem.route) {
                Column (
                    verticalArrangement = Arrangement.Center
                ){
                    Text("Profile")
                }
            }
            composable(DashboardMoreItem.route) {
                DashboardMoreRoute(
                    onItemTap = { route ->
                        viewModel.onItemTap(route)
                    }
                )
            }
            composable(DashboardWorkoutsItem.route) {
                Column (
                    verticalArrangement = Arrangement.Center
                ){
                    Text("Workouts")
                }
            }
        }
    }
}
