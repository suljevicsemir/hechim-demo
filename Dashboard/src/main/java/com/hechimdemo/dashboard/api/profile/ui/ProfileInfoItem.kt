package com.hechimdemo.dashboard.api.profile.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.semirsuljevic.foundation.api.common.UiText
import com.semirsuljevic.ui.api.theme.HechimTheme

@Composable
fun ProfileInfoItem(
    label: UiText,
    value: UiText
) {
    Column (
        modifier = Modifier
            .padding(bottom = HechimTheme.sizes.small)
            .fillMaxWidth()
            .background(
                color = HechimTheme.colors.surfaceBackground,
                shape = HechimTheme.shapes.xLargeRoundedCorner
            )
            .padding(HechimTheme.sizes.large)

    ){
        Text(
            label.asString(),
            style = HechimTheme.fonts.labelXXSmall,
            color = HechimTheme.colors.textFieldInactive
        )
        Spacer(modifier = Modifier.height(HechimTheme.sizes.xxSmall))
        Text(
            value.asString(),
            color = HechimTheme.colors.textDefault,
            style = HechimTheme.fonts.bodyEmphasized
        )
    }
}
