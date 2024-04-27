@file:OptIn(ExperimentalFoundationApi::class)

package com.semirsuljevic.onboarding.welcome.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cmtelematics.cmtauthentication.R
import com.semirsuljevic.onboarding.welcome.viewmodel.OnBoardingViewModel
import kotlinx.coroutines.launch

@Composable
fun OnBoarding(
    onBoardingViewModel: OnBoardingViewModel = viewModel(),
    onLastScreen: () -> Unit
) {
    val pagerState = androidx.compose.foundation.pager.rememberPagerState(pageCount = {
        3
    })
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(pagerState) {
        snapshotFlow {
            pagerState.currentPage
        }.collect {
            onBoardingViewModel.setIndex(it)
        }
    }

    Scaffold (
        containerColor = MaterialTheme.colorScheme.background,
    ){
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxHeight()
                .verticalScroll(scrollState)
                .padding(it)
        ){
            Text(
                stringResource(id = R.string.onboarding_top_bar),
                modifier = Modifier.padding(bottom = 10.dp),
                color = MaterialTheme.colors.onPrimary,
                style = MaterialTheme.typography.h2,
            )
            HorizontalPager(
                state = pagerState,
            ) { page ->
                when(page) {
                    0 -> OnBoardingScreen(
                        headline = stringResource(id = R.string.onboarding_1_headline),
                        description = stringResource(id = R.string.onboarding_1_desc),
                        image = R.drawable.img_onboarding_1
                    )
                    1 -> OnBoardingScreen(
                        headline = stringResource(id = R.string.onboarding_2_headline),
                        description = stringResource(id = R.string.onboarding_2_desc),
                        image = R.drawable.img_onboarding_2
                    )
                    2 -> OnBoardingScreen(
                        headline = stringResource(id = R.string.onboarding_3_headline),
                        description = stringResource(id = R.string.onboarding_3_desc),
                        image = R.drawable.img_onboarding_3
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(start = 32.dp, end = 32.dp, bottom = 20.dp)
                    .fillMaxWidth()
                    .fillMaxHeight()
            ){
                Row {
                    PageIndexIndicator(selected = onBoardingViewModel.selectedIndex == 0)
                    PageIndexIndicator(selected = onBoardingViewModel.selectedIndex == 1)
                    PageIndexIndicator(selected = onBoardingViewModel.selectedIndex == 2)
                }
                EugenPaddedButton(
                    modifier = Modifier.width(100.dp),
                    contentPaddingValues = PaddingValues(vertical = 17.dp),
                    onClick = {
                        if(!onBoardingViewModel.onLastScreen) {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(onBoardingViewModel.nextIndex)
                            }
                        }
                        else {
                            onLastScreen.invoke()
                        }
                    },
                    text = if (!onBoardingViewModel.onLastScreen) {
                        stringResource(id = R.string.onboarding_next_button)
                    } else stringResource(
                        id = R.string.onboarding_proceed
                    )
                )
            }
        }
    }
}
