package com.hechimdemo.dashboard.api.more.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.hechimdemo.dashboard.R
import com.hechimdemo.dashboard.api.about.ui.RouteAboutUs
import com.hechimdemo.dashboard.api.appSettings.ui.RouteApplicationSettings
import com.hechimdemo.dashboard.api.legal.ui.RouteLegal
import com.hechimdemo.dashboard.api.more.viewmodel.MoreViewModel
import com.hechimdemo.dashboard.api.profile.ui.RouteProfile
import com.semirsuljevic.foundation.api.common.UiText
import com.semirsuljevic.ui.api.common.HechimListItem
import com.semirsuljevic.ui.api.common.HechimListItemConfig
import com.semirsuljevic.ui.api.navigation.HechimRoute
import com.semirsuljevic.ui.api.theme.HechimTheme

@Composable
fun DashboardMoreRoute(
    modifier: Modifier = Modifier,
    onItemTap: (HechimRoute) -> Unit,
    viewModel: MoreViewModel = hiltViewModel()
) {
   Column (
       horizontalAlignment = Alignment.CenterHorizontally
   ){
       Text(
           UiText.StringResource(R.string.settings_title).asString(),
           style = HechimTheme.fonts.pageTitleAlt,
           color = HechimTheme.colors.textDefault
       )
       HechimListItem(
           config = HechimListItemConfig(
               title = UiText.StringResource(R.string.settings_profile_title),
               description = UiText.StringResource(R.string.settings_profile_desc),
               icon = R.drawable.ic_user_profile,
               onClick = { onItemTap(RouteProfile()) }
           )
       )
       HechimListItem(
           config = HechimListItemConfig(
               title = UiText.StringResource(R.string.settings_app_settings_title),
               description = UiText.StringResource(R.string.settings_app_settings_desc),
               icon = R.drawable.ic_app_settings,
               onClick = {onItemTap.invoke(RouteApplicationSettings())}
           )
       )
       HechimListItem(
           config = HechimListItemConfig(
               title = UiText.StringResource(R.string.settings_legal_title),
               description = UiText.StringResource(R.string.settings_legal_desc),
               icon = R.drawable.ic_legal_info,
               onClick = {
                   onItemTap.invoke(RouteLegal())
               }
           )
       )
       HechimListItem(
           config = HechimListItemConfig(
               title = UiText.StringResource(R.string.settings_about_title, "FitnessPal"),
               description = UiText.StringResource(R.string.settings_about_desc),
               icon = R.drawable.ic_about_app,
               onClick = {
                   onItemTap(RouteAboutUs())
               }
           )
       )
       HechimListItem(
           config = HechimListItemConfig(
               title = UiText.StringResource(R.string.settings_log_out_title),
               description = UiText.StringResource(R.string.settings_log_out_desc),
               icon = R.drawable.ic_logout,
               onClick = {
                   viewModel.logOut()
               }
           )
       )
   }
}
