package com.semirsuljevic.ui.api.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.semirsuljevic.ui.api.common.HechimResource
import com.semirsuljevic.ui.api.theme.HechimTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HechimScreen(
    config: HechimScreenConfig = HechimScreenConfig(),
    useTopBar : Boolean = true,
    resource: HechimResource<String, Int>? = null,
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


    AnimatedVisibility(visible = resource is HechimResource.Loading) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    HechimTheme.colors.backgroundDefault
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                color = HechimTheme.colors.primary

            )
        }
    }

}
