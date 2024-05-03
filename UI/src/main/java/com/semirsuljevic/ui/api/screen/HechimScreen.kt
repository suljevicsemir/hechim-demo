package com.semirsuljevic.ui.api.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HechimScreen(
    config: HechimScreenConfig = HechimScreenConfig(),
    useTopBar : Boolean = true,
    actions: @Composable (RowScope.() -> Unit)? = null,
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold (
        containerColor = config.containerColor,
        topBar = {
            if(!useTopBar) {
                return@Scaffold
            }
            HechimTopBar(
                config = config,
                actions = actions,
                navigateUp = { /*TODO*/ }
            )
        }
    ){
        content(it)
    }
}
