package com.semirsuljevic.onboarding.api.welcome.ui.language

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.hechimdemo.onboarding.R
import com.semirsuljevic.onboarding.api.welcome.viewmodel.LanguageSelectionViewModel
import com.semirsuljevic.ui.api.buttons.HechimButton
import com.semirsuljevic.ui.api.screen.HechimScreen
import com.semirsuljevic.ui.api.screen.HechimScreenConfig
import com.semirsuljevic.ui.api.theme.HechimTheme

@Composable
fun LanguageSelectionRoute(
    languageSelectionViewModel: LanguageSelectionViewModel,
    onContinue: (() -> Unit)? = null
) {

    val selected = languageSelectionViewModel.selectedLocale

    println("selected locale: $selected")
    
    LanguageSelectionDialog(
        onDismiss = { languageSelectionViewModel.toggleDialog() },
        onConfirm = { languageSelectionViewModel.confirmLocaleChange() },
        visible = languageSelectionViewModel.dialogVisible,
        language = if(languageSelectionViewModel.selectedLocale == null)
            "" else stringResource(id = languageSelectionViewModel.selectedLocale!!.text)
    )
    
    HechimScreen (
        config = HechimScreenConfig(
            title = stringResource(id = R.string.language_selection_title)
        )
    ){
        Column (
            modifier = Modifier
                .padding(it)
                .padding(horizontal = HechimTheme.sizes.scaffoldHorizontal)
        ){
            Spacer(modifier = Modifier.height(HechimTheme.sizes.medium))
            Text(
                stringResource(id = R.string.language_selection_page_desc),
                color = HechimTheme.colors.textDefault,
                style = HechimTheme.fonts.bodyRegular
            )
            Spacer(modifier = Modifier.height(HechimTheme.sizes.xxLarge))
            languageSelectionViewModel.languages.forEach {model ->
                LanguageSelectionItem(
                    appLanguageModel = model,
                    onClick = {
                      languageSelectionViewModel.selectLocale(model)
                    },
                    selected = languageSelectionViewModel.savedLocale == model.locale
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            if(onContinue != null) {
                HechimButton(
                onClick = {
                    onContinue()
                },
                text = "Continue"
                )
            }
        }
    }
}
