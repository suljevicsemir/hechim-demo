package com.semirsuljevic.ui.api.navigation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(
    private val navigator: Navigator
): ViewModel(){


    fun pop() {
        navigator.pop()
    }
}
