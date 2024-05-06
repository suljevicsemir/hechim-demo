package com.semirsuljevic.onboarding.api.welcome.viewmodel

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semirsuljevic.onboarding.api.welcome.config.welcome.OnBoardingConstants
import com.semirsuljevic.onboarding.api.welcome.ui.language.RouteLanguageSelection
import com.semirsuljevic.ui.api.navigation.HechimRoute
import com.semirsuljevic.ui.api.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val navigator: Navigator
) : ViewModel() {

    private var _selectedIndex = mutableIntStateOf(0)

    fun setIndex(index: Int) {
        if(index >= items.size) {
            return
        }
        _selectedIndex.intValue = index
    }

    val onLastScreen: Boolean get () = _selectedIndex.intValue == 2

    val selectedIndex : Int get() = _selectedIndex.intValue


    val items get() = OnBoardingConstants.onBoardingItems

    @OptIn(ExperimentalFoundationApi::class)
    suspend fun onButtonClick(pagerState: PagerState) {
        withContext(viewModelScope.coroutineContext) {
            if(_selectedIndex.intValue != items.size - 1) {
                pagerState.animateScrollToPage(_selectedIndex.intValue + 1)
                return@withContext
            }
            println("tvoj route je: ${_route}")
            if(_route == null) {
                navigator.navigate(RouteLanguageSelection())
                return@withContext
            }
            navigator.pop()
        }
    }

    /** Returns pager screen info for currently selected index. */
    fun pagerItem(index: Int) = items[index]

    private var _route: String? = null

    fun setRoute(value: String?) {
        _route = value
    }

}
