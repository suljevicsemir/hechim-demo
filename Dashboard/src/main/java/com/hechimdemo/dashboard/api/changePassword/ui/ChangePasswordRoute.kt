package com.hechimdemo.dashboard.api.changePassword.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.hechimdemo.dashboard.R
import com.hechimdemo.dashboard.api.changePassword.viewmodel.ChangePasswordViewModel
import com.semirsuljevic.foundation.api.common.UiText
import com.semirsuljevic.ui.api.buttons.HechimButton
import com.semirsuljevic.ui.api.screen.HechimScreen
import com.semirsuljevic.ui.api.screen.HechimScreenConfig
import com.semirsuljevic.ui.api.textfield.HechimTextField
import com.semirsuljevic.ui.api.theme.HechimTheme

@Composable
fun ChangePasswordRoute(
    viewModel: ChangePasswordViewModel = hiltViewModel()
) {

    val scrollState = rememberScrollState()
    HechimScreen (
        config = HechimScreenConfig(
            title = UiText.StringResource(R.string.change_pass_title).asString(),
            errorReset = viewModel::resetResource
        ),
        resource = viewModel.resource
    ){
        Column (
            modifier = Modifier
                .padding(it)
                .padding(HechimTheme.sizes.scaffoldHorizontal)
        ){
            Column (
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = UiText.StringResource(R.string.change_pass_headline).asString(),
                    color = HechimTheme.colors.textDefault,
                    style = HechimTheme.fonts.bodyRegular
                )
                Spacer(modifier = Modifier.height(HechimTheme.sizes.xxLarge))
                HechimTextField(
                    value = viewModel.oldPassword.text,
                    onValueChange = viewModel::onOldChanged,
                    hint = UiText.StringResource(R.string.change_pass_old_pass_hint).asString(),
                    errorText = viewModel.oldPassword.error.asString()
                )
                HechimTextField(
                    value = viewModel.newPassword.text,
                    onValueChange = viewModel::onNewChanged,
                    hint = UiText.StringResource(R.string.change_pass_new_pass_hint).asString(),
                    errorText = viewModel.newPassword.error.asString()
                )
                HechimTextField(
                    value = viewModel.confirmedPassword.text,
                    onValueChange = viewModel::onConfirmedChanged,
                    hint = UiText.StringResource(R.string.change_pass_confirm_pass_hint).asString(),
                    errorText = viewModel.confirmedPassword.error.asString()
                )
                Spacer(modifier = Modifier.height(HechimTheme.sizes.xxxLarge))
                Image(
                    painter = painterResource(id = R.drawable.img_info_flag),
                    contentDescription = null,
                )
                Spacer(modifier = Modifier.height(HechimTheme.sizes.small))
                Text(
                    text = UiText.StringResource(R.string.change_pass_requirements).asString(),
                    color = HechimTheme.colors.textFieldInactive,
                    style = HechimTheme.fonts.bodyRegular,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.weight(1f))
            }

            HechimButton(
                onClick = viewModel::changePassword,
                text = UiText.StringResource(R.string.change_pass_button).asString(),
            )
        }
    }
}
