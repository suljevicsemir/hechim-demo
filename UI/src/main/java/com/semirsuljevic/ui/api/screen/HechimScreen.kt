package com.semirsuljevic.ui.api.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.semirsuljevic.foundation.api.common.HechimResource
import com.semirsuljevic.ui.R
import com.semirsuljevic.ui.api.buttons.HechimButton
import com.semirsuljevic.ui.api.theme.HechimTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HechimScreen(
    config: HechimScreenConfig = HechimScreenConfig(),
    useTopBar : Boolean = true,
    resource: HechimResource<Any>? = null,
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

    AnimatedVisibility(visible = resource is HechimResource.Error) {
        Column(
            modifier = Modifier
                .padding(horizontal = HechimTheme.sizes.scaffoldHorizontal)
                .fillMaxSize()
                .background(
                    HechimTheme.colors.backgroundDefault
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(0.1f))
            Image(painter = painterResource(id = R.drawable.img_nothing_found), contentDescription = "Error")
            Spacer(modifier = Modifier.height(HechimTheme.sizes.xxLarge))
            if(resource is HechimResource.Error) {
                Text(
                    resource.error.message,
                    style = HechimTheme.fonts.bodyLarge,
                    color = HechimTheme.colors.textDefault
                )
            }

            Spacer(modifier = Modifier.weight(1f))
            HechimButton(
                onClick = {
                    config.errorReset?.invoke()
                },
                text = "Retry strategy"
            )
            Spacer(modifier = Modifier.height(HechimTheme.sizes.xLarge))
        }
    }

}
