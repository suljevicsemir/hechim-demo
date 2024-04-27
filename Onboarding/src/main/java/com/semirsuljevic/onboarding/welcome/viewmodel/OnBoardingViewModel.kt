package com.semirsuljevic.onboarding.welcome.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class OnBoardingViewModel: ViewModel() {

    private val x = mutableStateListOf<String>()
}
