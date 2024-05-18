package com.hechimdemo.dashboard.api.navigation

import com.hechimdemo.dashboard.R
import com.semirsuljevic.foundation.api.common.UiText
import com.semirsuljevic.ui.api.navbar.HechimNavigationBarItem

data object DashboardHomeItem: HechimNavigationBarItem(
    label = UiText.StringResource(R.string.dashboard_item_home),
    icon = R.drawable.dashboard_home,
    route = "Dashboard"
)

data object DashboardProfileItem: HechimNavigationBarItem(
    label = UiText.StringResource(R.string.dashboard_item_profile),
    icon = R.drawable.dashboard_profile,
    route = "Profile"
)

data object DashboardMoreItem: HechimNavigationBarItem(
    label = UiText.StringResource(R.string.dashboard_item_more),
    icon = R.drawable.dashboard_more,
    route = "More"
)

data object DashboardWorkoutsItem: HechimNavigationBarItem(
    label = UiText.StringResource(R.string.dashboard_item_workouts),
    icon = R.drawable.dashboard_trips,
    route = "Workouts"
)
