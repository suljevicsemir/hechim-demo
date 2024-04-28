package com.semirsuljevic.ui.api.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.semirsuljevic.ui.api.theme.HechimTheme

@Composable
fun HechimScreen(
    config: HechimScreenConfig = HechimScreenConfig(),
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold (
        containerColor = config.containerColor,
        topBar = {

        }
    ){
        content(it)
    }
}
