package com.hechimdemo.dashboard.api.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hechimdemo.dashboard.api.navigation.DashboardHomeItem
import com.hechimdemo.dashboard.api.navigation.DashboardMoreItem
import com.hechimdemo.dashboard.api.navigation.DashboardProfileItem
import com.hechimdemo.dashboard.api.navigation.DashboardWorkoutsItem
import com.hechimdemo.dashboard.api.viewmodel.DashboardViewModel
import com.semirsuljevic.ui.api.navbar.HechimNavigationBar
import com.semirsuljevic.ui.api.navigation.HechimRoute
import com.semirsuljevic.ui.api.screen.HechimScreen
import com.semirsuljevic.ui.api.theme.HechimTheme

class RouteDashboard:HechimRoute("dashboard")

@Composable
fun DashboardNavBar(
    navController: NavHostController = rememberNavController(),
    viewModel: DashboardViewModel
) {

    HechimScreen (
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
                .padding(HechimTheme.sizes.medium)
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
                Column (
                    verticalArrangement = Arrangement.Center
                ){
                    Text("More")
                }
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
