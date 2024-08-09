package com.hechimdemo.dashboard.api.legal.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.hechimdemo.dashboard.R
import com.hechimdemo.dashboard.api.legal.viewmodel.LegalViewModel
import com.semirsuljevic.ui.api.common.HechimHtmlText
import com.semirsuljevic.ui.api.navigation.HechimRoute
import com.semirsuljevic.ui.api.screen.HechimScreen
import com.semirsuljevic.ui.api.screen.HechimScreenConfig
import com.semirsuljevic.ui.api.theme.HechimTheme

class RoutePrivacyPolicy: HechimRoute("privacy_policy")

@Composable
fun PrivacyPolicyRoute(
    legalViewModel: LegalViewModel
) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    HechimScreen (
        config = HechimScreenConfig(
            title = stringResource(id = R.string.privacy_policy_title),
            errorReset = {
                legalViewModel.getPrivacy()
            }
        ),
        resource = legalViewModel.privacyPolicy
    ){
        Column (
            modifier = Modifier
                .padding(it)
                .padding(HechimTheme.sizes.scaffoldHorizontal)
                .verticalScroll(scrollState)
        ){
            Spacer(modifier = Modifier.height(HechimTheme.sizes.xLarge))
            HechimHtmlText(
                html = legalViewModel.privacyPolicyContent,
                onHyperlinkClick = { string ->
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(string))
                    context.startActivity(intent)
                }
            )
        }
    }
}
