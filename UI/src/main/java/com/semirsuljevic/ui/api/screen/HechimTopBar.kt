package com.semirsuljevic.ui.api.screen

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.semirsuljevic.ui.api.theme.HechimTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HechimTopBar(
    config: HechimScreenConfig,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    colors: TopAppBarColors = TopAppBarDefaults.mediumTopAppBarColors(
        containerColor = HechimTheme.colors.backgroundDefault,
    ),
) {
    CenterAlignedTopAppBar(
        title = {
            if(config.title != null) {
                Text(
                    config.title,
                    color = HechimTheme.colors.textDefault,
                    //style = CmtFont.titleRegular
                )
            }
        },
        colors = colors,
        modifier = modifier,
        navigationIcon = {
//            if (config.canNavigateBack) {
//                IconButton(onClick = navigateUp) {
//                    Image(
//                        painter = painterResource(id = R.drawable.arrow_left),
//                        contentDescription = ""
//                    )
//                }
//            }
        }
    )
}
