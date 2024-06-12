package com.hechimdemo.dashboard.api.navigation

import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hechimdemo.dashboard.api.about.ui.AboutUsRoute
import com.hechimdemo.dashboard.api.about.ui.RouteAboutUs
import com.hechimdemo.dashboard.api.ui.DashboardNavBar
import com.hechimdemo.dashboard.api.ui.RouteDashboard
import com.hechimdemo.dashboard.api.viewmodel.DashboardViewModel

fun NavGraphBuilder.dashboardNavGraph(
    viewModelStoreOwner: ViewModelStoreOwner,
    trapdoorCallback: () -> Unit
) {
    composable(route = RouteDashboard().path) {
        val viewModel = viewModel<DashboardViewModel>(viewModelStoreOwner)
        DashboardNavBar(
            viewModel = viewModel,
            trapdoorCallback = trapdoorCallback
        )
    }
    composable(route = RouteAboutUs().path) {
        AboutUsRoute()
    }

}
