package com.hechimdemo.dashboard.api.profile.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.hechimdemo.dashboard.R
import com.hechimdemo.dashboard.api.profile.viewmodel.ProfileUpdateViewModel
import com.semirsuljevic.ui.api.buttons.HechimButton
import com.semirsuljevic.ui.api.navigation.HechimRoute
import com.semirsuljevic.ui.api.screen.HechimScreen
import com.semirsuljevic.ui.api.screen.HechimScreenConfig
import com.semirsuljevic.ui.api.textfield.HechimTextField
import com.semirsuljevic.ui.api.theme.HechimTheme

class RouteLastName: HechimRoute("last_name")

@Composable
fun ProfileLastName(viewModel: ProfileUpdateViewModel = hiltViewModel()) {
    HechimScreen (
        config = HechimScreenConfig(
            title = stringResource(id = R.string.profile_last_name)
        ),
        resource = viewModel.state
    ){
        Column (
            modifier = Modifier
                .padding(it)
                .padding(HechimTheme.sizes.scaffoldHorizontal)
        ){
            Text(
                stringResource(id = R.string.last_name_headline),
                color = HechimTheme.colors.textDefault,
                style = HechimTheme.fonts.bodyRegular
            )
            Spacer(modifier = Modifier.height(HechimTheme.sizes.medium))
            HechimTextField(
                value = viewModel.lastName,
                onValueChange = { value -> viewModel.setLastName(value) },
                hint = stringResource(id = R.string.last_name_hint)
            )
            Spacer(modifier = Modifier.height(HechimTheme.sizes.xxLarge))
            HechimButton(
                onClick = { viewModel.updateName() },
                text = stringResource(id = R.string.name_update),
                enabled = viewModel.lastNameButton
            )
        }
    }
}
