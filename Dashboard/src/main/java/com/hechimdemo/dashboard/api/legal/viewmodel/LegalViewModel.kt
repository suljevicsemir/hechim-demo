package com.hechimdemo.dashboard.api.legal.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hechimdemo.dashboard.api.legal.ui.RoutePrivacyPolicy
import com.hechimdemo.dashboard.api.legal.ui.RouteTerms
import com.semirsuljevic.foundation.api.common.HechimResource
import com.semirsuljevic.foundation.api.settings.HechimSettings
import com.semirsuljevic.foundation.api.settings.model.PrivacyPolicyResponse
import com.semirsuljevic.foundation.api.settings.model.TermsOfUseResponse
import com.semirsuljevic.ui.api.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LegalViewModel @Inject constructor(
    private val hechimSettings: HechimSettings,
    private val navigator: Navigator
): ViewModel(){

    private val _terms = mutableStateOf<HechimResource<TermsOfUseResponse>>(HechimResource.Loading())
    val terms get() = _terms.value
    val termsContent get() : String {
        return when(_terms.value) {
            is HechimResource.Success -> {
                (_terms.value as HechimResource.Success).data.content
            }
            else -> {""}
        }
    }

    private val _privacyPolicy = mutableStateOf<HechimResource<PrivacyPolicyResponse>>(HechimResource.Loading())

    val privacyPolicy get() = _privacyPolicy.value

    val privacyPolicyContent get() : String {
        return when(_privacyPolicy.value) {
            is HechimResource.Success -> {
                (_privacyPolicy.value as HechimResource.Success).data.content
            }
            else -> {""}
        }
    }


    fun getPrivacy() {
        viewModelScope.launch {
            launch {
                hechimSettings.privacyPolicyFlow.collectLatest {
                    _privacyPolicy.value = it
                }
            }
            hechimSettings.getPrivacyPolicy()
        }
    }

    fun getTerms() {
        viewModelScope.launch {
            launch {
                hechimSettings.termsFlow.collectLatest {
                    _terms.value = it
                }
            }
            hechimSettings.getTermsOfUse()
        }
    }

    fun navigateToTerms() {
        navigator.navigate(RouteTerms())
        getTerms()
    }
    fun navigateToPrivacy() {
        navigator.navigate(RoutePrivacyPolicy())
        getPrivacy()
    }
}
