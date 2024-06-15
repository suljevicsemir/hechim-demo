package com.hechimdemo.dashboard.api.profile.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import com.hechimdemo.dashboard.R
import com.hechimdemo.dashboard.api.profile.viewmodel.UserProfileViewModel
import com.semirsuljevic.foundation.api.common.HechimResource
import com.semirsuljevic.foundation.api.common.UiText
import com.semirsuljevic.ui.api.navigation.HechimRoute
import com.semirsuljevic.ui.api.screen.HechimScreen
import com.semirsuljevic.ui.api.screen.HechimScreenConfig
import com.semirsuljevic.ui.api.theme.HechimTheme

class RouteProfile: HechimRoute("profile")

@Composable
fun ProfileRoute(
    modifier: Modifier = Modifier,
    userProfileViewModel: UserProfileViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit){
        userProfileViewModel.collectProfile()
    }

    HechimScreen (
        config = HechimScreenConfig(
            title = stringResource(id = R.string.profile_page_title)
        ),
        resource = userProfileViewModel.user
    ){
        Column (
            modifier = Modifier
                .padding(it)
                .padding(HechimTheme.sizes.scaffoldHorizontal),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Spacer(modifier = Modifier.height(HechimTheme.sizes.xxLarge))
            Image(painter = painterResource(id = R.drawable.ic_user_profile), contentDescription = null)
            Spacer(modifier = Modifier.height(HechimTheme.sizes.medium))
            Text(
                userProfileViewModel.userContent.email,
                color = HechimTheme.colors.textDefault,
                style = HechimTheme.fonts.regularTitle,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )
            Spacer(modifier = Modifier.height(HechimTheme.sizes.large))
            ProfileInfoItem(
                label = UiText.StringResource(R.string.profile_first_name),
                value = UiText.StringValue(userProfileViewModel.userContent.firstName)
            )
            ProfileInfoItem(
                label = UiText.StringResource(R.string.profile_last_name),
                value = UiText.StringValue(userProfileViewModel.userContent.lastName)
            )

        }
    }
}
