package com.hechimdemo.dashboard.api.legal.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.hechimdemo.dashboard.R
import com.hechimdemo.dashboard.api.legal.viewmodel.LegalViewModel
import com.semirsuljevic.foundation.api.common.UiText
import com.semirsuljevic.ui.api.common.HechimListItem
import com.semirsuljevic.ui.api.common.HechimListItemConfig
import com.semirsuljevic.ui.api.navigation.HechimRoute
import com.semirsuljevic.ui.api.screen.HechimScreen
import com.semirsuljevic.ui.api.screen.HechimScreenConfig
import com.semirsuljevic.ui.api.theme.HechimTheme

class RouteLegal:HechimRoute("legal")

@Composable
fun LegalRoute(
    legalViewModel: LegalViewModel
) {
    HechimScreen (
        config = HechimScreenConfig(
            title = stringResource(id = R.string.legal_page_title)
        )
    ){
        Column (
            modifier = Modifier
                .padding(it)
                .padding(HechimTheme.sizes.scaffoldHorizontal)
        ){
            HechimListItem(config = HechimListItemConfig(
                title = UiText.StringResource(R.string.legal_page_terms_title),
                description = UiText.StringResource(R.string.legal_page_terms_desc),
                onClick = { legalViewModel.navigateToTerms() },
                icon = R.drawable.ic_legal_info)
            )
            HechimListItem(config = HechimListItemConfig(
                title = UiText.StringResource(R.string.legal_page_privacy_title),
                description = UiText.StringResource(R.string.legal_page_privacy_desc),
                onClick = { legalViewModel.navigateToPrivacy() },
                icon = R.drawable.ic_legal_info)
            )
        }
    }
}
