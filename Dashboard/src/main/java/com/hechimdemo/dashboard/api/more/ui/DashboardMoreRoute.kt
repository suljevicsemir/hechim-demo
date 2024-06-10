package com.hechimdemo.dashboard.api.more.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.hechimdemo.dashboard.R
import com.semirsuljevic.foundation.api.common.UiText
import com.semirsuljevic.ui.api.common.HechimListItem
import com.semirsuljevic.ui.api.common.HechimListItemConfig

@Composable
fun DashboardMoreRoute(
    modifier: Modifier = Modifier
) {
   Column {
       HechimListItem(
           config = HechimListItemConfig(
               title = UiText.StringResource(R.string.settings_profile_title),
               description = UiText.StringResource(R.string.settings_profile_desc),
               icon = R.drawable.ic_user_profile,
               onClick = {

               }
           )
       )
       HechimListItem(
           config = HechimListItemConfig(
               title = UiText.StringResource(R.string.settings_app_settings_title),
               description = UiText.StringResource(R.string.settings_app_settings_desc),
               icon = R.drawable.ic_app_settings,
               onClick = {

               }
           )
       )
       HechimListItem(
           config = HechimListItemConfig(
               title = UiText.StringResource(R.string.settings_legal_title),
               description = UiText.StringResource(R.string.settings_legal_desc),
               icon = R.drawable.ic_legal_info,
               onClick = {

               }
           )
       )
       HechimListItem(
           config = HechimListItemConfig(
               title = UiText.StringResource(R.string.settings_about_title),
               description = UiText.StringResource(R.string.settings_about_desc),
               icon = R.drawable.ic_about_app,
               onClick = {

               }
           )
       )
       HechimListItem(
           config = HechimListItemConfig(
               title = UiText.StringResource(R.string.settings_log_out_title),
               description = UiText.StringResource(R.string.settings_log_out_desc),
               icon = R.drawable.ic_logout,
               onClick = {

               }
           )
       )
   }
}
