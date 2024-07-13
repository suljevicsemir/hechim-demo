package com.hechimdemo.dashboard.api.appSettings.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.hechimdemo.dashboard.R
import com.hechimdemo.dashboard.api.changePassword.ui.RouteChangePassword
import com.semirsuljevic.foundation.api.common.UiText
import com.semirsuljevic.onboarding.api.welcome.ui.language.RouteLanguageSelection
import com.semirsuljevic.ui.api.common.HechimListItem
import com.semirsuljevic.ui.api.common.HechimListItemConfig
import com.semirsuljevic.ui.api.navigation.HechimRoute
import com.semirsuljevic.ui.api.navigation.NavigationViewModel
import com.semirsuljevic.ui.api.screen.HechimScreen
import com.semirsuljevic.ui.api.screen.HechimScreenConfig
import com.semirsuljevic.ui.api.theme.HechimTheme


class RouteApplicationSettings: HechimRoute("application_settings")

@Composable
fun ApplicationSettings(
    navigationViewModel: NavigationViewModel = hiltViewModel()
) {
    HechimScreen (
        config = HechimScreenConfig(
            title = stringResource(id = R.string.app_settings_page_title)
        )
    ){
        Column(
            modifier = Modifier
                .padding(it)
                .padding(HechimTheme.sizes.scaffoldHorizontal)
        ) {
            HechimListItem(
                config = HechimListItemConfig(
                    title = UiText.StringResource(R.string.app_settings_change_pass_title),
                    description = UiText.StringResource(R.string.app_settings_change_pass_desc),
                    onClick = { navigationViewModel.navigateTo(RouteChangePassword()) },
                    icon = R.drawable.ic_change_password
                )
            )
            HechimListItem(
                config = HechimListItemConfig(
                    title = UiText.StringResource(R.string.app_settings_biometrics_title),
                    description = UiText.StringResource(R.string.app_settings_biometrics_desc),
                    onClick = {

                    },
                    icon = R.drawable.ic_biometrics
                )
            )
            HechimListItem(
                config = HechimListItemConfig(
                    title = UiText.StringResource(R.string.app_settings_language_title),
                    description = UiText.StringResource(R.string.app_settings_language_desc),
                    onClick = { navigationViewModel.navigateTo(RouteLanguageSelection())},
                    icon = R.drawable.ic_language_selection
                )
            )
        }
    }
}
