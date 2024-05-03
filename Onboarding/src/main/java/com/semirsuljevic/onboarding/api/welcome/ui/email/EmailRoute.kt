package com.semirsuljevic.onboarding.api.welcome.ui.email

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.hechimdemo.onboarding.R
import com.semirsuljevic.onboarding.api.welcome.viewmodel.LoginEmailViewModel
import com.semirsuljevic.ui.api.buttons.HechimButton
import com.semirsuljevic.ui.api.buttons.HechimIconButton
import com.semirsuljevic.ui.api.paging.PageIndexIndicator
import com.semirsuljevic.ui.api.screen.HechimScreen
import com.semirsuljevic.ui.api.screen.HechimScreenConfig
import com.semirsuljevic.ui.api.textfield.HechimTextField
import com.semirsuljevic.ui.api.theme.HechimTheme

@Composable
fun EmailRoute(loginEmailViewModel: LoginEmailViewModel) {
    HechimScreen (
        config = HechimScreenConfig(
            canNavigateBack = false,
            title = stringResource(id = R.string.login_username_input_title)
        ),
        actions = {
            HechimIconButton(
                icon = R.drawable.ic_help,
                onClick = {
                    loginEmailViewModel.navigate("OnBoarding")
                }
            )
        }
    ){
        Column (
            modifier = Modifier
                .padding(it)
                .padding(horizontal = HechimTheme.sizes.xLarge)
                .padding(bottom = HechimTheme.sizes.xxLarge)
                .fillMaxSize()
        ){
            Spacer(modifier = Modifier.weight(0.2f))
            Row {
                PageIndexIndicator(selected = true)
                PageIndexIndicator(selected = false)
            }
            Spacer(modifier = Modifier.height(HechimTheme.sizes.xLarge))
            Text(
                stringResource(id = R.string.login_username_input_headline),
                color = HechimTheme.colors.textDefault,
                style = HechimTheme.fonts.bodyLargeNoSpacing
            )
            Spacer(modifier = Modifier.height(HechimTheme.sizes.xLarge))
            Text(
                stringResource(id = R.string.login_username_input_desc),
                color = HechimTheme.colors.textDefault,
                style = HechimTheme.fonts.bodyRegular
            )
            Spacer(modifier = Modifier.weight(0.1f))
            HechimTextField(
                value = loginEmailViewModel.email,
                onValueChange = { email ->
                    loginEmailViewModel.onEmailChange(email)
                },
                hint = stringResource(id = R.string.login_username_input_hint)
            )
            Spacer(modifier = Modifier.weight(1f))
            HechimButton(
                onClick = {
                  loginEmailViewModel.navigate("Password")
                },
                text = stringResource(id = R.string.login_username_input_button)
            )
        }
    }
}




@Preview
@Composable
private fun EmailRoutePreview() {
    EmailRoute(loginEmailViewModel = hiltViewModel())
}
