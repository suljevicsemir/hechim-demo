package com.semirsuljevic.onboarding.welcome.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
internal fun OnBoardingScreen(
    headline: String,
    description: String,
    image: Int,
) {
    Column (
        modifier = Modifier.background(color = Color.Red)
    ){
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            modifier = Modifier.padding(bottom = 20.dp)
        )
        Column (
            modifier = Modifier.padding(horizontal = 32.dp, vertical = 10.dp)
        ){
            Text(
                text = headline,
                modifier = Modifier.padding(bottom = 20.dp),
//                style = MaterialTheme.typography.h1,
//                color = MaterialTheme.colors.onPrimary
            )
            Text(
                text = description,
//                style = MaterialTheme.typography.h4,
//                color = MaterialTheme.colors.onPrimary
            )
        }
    }
}
