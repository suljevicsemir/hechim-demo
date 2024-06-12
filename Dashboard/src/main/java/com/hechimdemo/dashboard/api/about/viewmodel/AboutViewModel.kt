package com.hechimdemo.dashboard.api.about.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semirsuljevic.foundation.api.common.HechimResource
import com.semirsuljevic.foundation.api.settings.HechimSettings
import com.semirsuljevic.foundation.api.settings.model.AboutUsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AboutViewModel @Inject constructor(
    private val hechimSettings: HechimSettings
) :ViewModel() {

    private val _about = mutableStateOf<HechimResource<AboutUsResponse>>(HechimResource.Loading())
    val about get() = _about.value

    val aboutContent get(): String {
        return when(_about.value) {
            is HechimResource.Success -> {
                (_about.value as HechimResource.Success).data.content
            }
            else -> {
                ""
            }
        }
    }

    init {
        getAboutContent()
    }


    fun getAboutContent() {
        viewModelScope.launch {
            launch {
                hechimSettings.aboutUsFlow.collectLatest {
                    _about.value = it
                }
            }
            hechimSettings.getAboutUs()
        }
    }

}
