package com.hechimdemo.dashboard.api.more.viewmodel

import androidx.lifecycle.ViewModel
import com.hechimdemo.dashboard.R
import com.semirsuljevic.foundation.api.authentication.HechimAuthentication
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoreViewModel @Inject constructor(
    private val hechimAuthentication: HechimAuthentication,
): ViewModel(){


    fun testFlow() {

    }

    fun string() {
        R.string.dashboard_item_more


    }
}
