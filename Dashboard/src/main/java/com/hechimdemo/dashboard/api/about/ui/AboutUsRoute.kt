package com.hechimdemo.dashboard.api.about.ui

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
import androidx.hilt.navigation.compose.hiltViewModel
import com.hechimdemo.dashboard.R
import com.hechimdemo.dashboard.api.about.viewmodel.AboutViewModel
import com.semirsuljevic.ui.api.common.HechimHtmlText
import com.semirsuljevic.ui.api.navigation.HechimRoute
import com.semirsuljevic.ui.api.screen.HechimScreen
import com.semirsuljevic.ui.api.screen.HechimScreenConfig
import com.semirsuljevic.ui.api.theme.HechimTheme

class RouteAboutUs:HechimRoute("about_us")

@Composable
fun AboutUsRoute(
    viewModel: AboutViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    HechimScreen (
        config = HechimScreenConfig(
            title = stringResource(id = R.string.about_page_title),
            errorReset = {
                viewModel.getAboutContent()
            }
        ),
        resource = viewModel.about
    ){
        Column (
            modifier = Modifier
                .padding(it)
                .padding(HechimTheme.sizes.scaffoldHorizontal)
                .verticalScroll(scrollState)
        ){
            Image(
                painter = painterResource(id = R.drawable.img_about),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.height(HechimTheme.sizes.xLarge))
            HechimHtmlText(
                html = viewModel.aboutContent,
                onHyperlinkClick = {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
                    context.startActivity(intent)
                }
            )
        }
    }
}
