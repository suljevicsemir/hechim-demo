package com.hechimdemo.dashboard.api.viewmodel

import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(

):ViewModel(){

    private val _navBarIndex = mutableIntStateOf(0)
    val navBarIndex get() = _navBarIndex.value

    fun setNavBarIndex(value: Int) {
        _navBarIndex.value = value
    }

}
