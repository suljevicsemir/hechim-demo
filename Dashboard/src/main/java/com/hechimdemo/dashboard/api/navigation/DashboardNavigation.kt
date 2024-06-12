package com.hechimdemo.dashboard.api.navigation

import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hechimdemo.dashboard.api.about.ui.AboutUsRoute
import com.hechimdemo.dashboard.api.about.ui.RouteAboutUs
import com.hechimdemo.dashboard.api.legal.ui.LegalRoute
import com.hechimdemo.dashboard.api.legal.ui.PrivacyPolicyRoute
import com.hechimdemo.dashboard.api.legal.ui.RouteLegal
import com.hechimdemo.dashboard.api.legal.ui.RoutePrivacyPolicy
import com.hechimdemo.dashboard.api.legal.ui.RouteTerms
import com.hechimdemo.dashboard.api.legal.ui.TermsRoute
import com.hechimdemo.dashboard.api.legal.viewmodel.LegalViewModel
import com.hechimdemo.dashboard.api.ui.DashboardNavBar
import com.hechimdemo.dashboard.api.ui.RouteDashboard
import com.hechimdemo.dashboard.api.viewmodel.DashboardViewModel

fun NavGraphBuilder.dashboardNavGraph(
    viewModelStoreOwner: ViewModelStoreOwner,
) {
    composable(route = RouteDashboard().path) {
        val viewModel = viewModel<DashboardViewModel>(viewModelStoreOwner)
        DashboardNavBar(
            viewModel = viewModel
        )
    }
    composable(route = RouteAboutUs().path) {
        AboutUsRoute()
    }

    composable(route = RouteTerms().path) {
        val legalViewModel = viewModel<LegalViewModel>(viewModelStoreOwner)
        TermsRoute(legalViewModel = legalViewModel)
    }

    composable(route = RoutePrivacyPolicy().path) {
        val legalViewModel = viewModel<LegalViewModel>(viewModelStoreOwner)
        PrivacyPolicyRoute(legalViewModel = legalViewModel)
    }

    composable(route = RouteLegal().path) {
        val legalViewModel = viewModel<LegalViewModel>(viewModelStoreOwner)
        LegalRoute(legalViewModel = legalViewModel)
    }

}
