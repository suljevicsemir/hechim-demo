package com.semirsuljevic.onboarding.api.welcome.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.semirsuljevic.onboarding.api.welcome.config.welcome.AppLanguageModel
import com.semirsuljevic.onboarding.api.welcome.config.welcome.AppLocale
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LanguageSelectionViewModel @Inject constructor(

): ViewModel() {

    private val _dialogVisible = mutableStateOf(false)

    private val selectedLocale = mutableStateOf(AppLocale.English)
    val savedLocale = mutableStateOf(AppLocale.English)

    val dialogLanguageString = mutableStateOf("")

    init {
//        selectedLocale.value = secureSharedPref.getStringValue(SecureSharedPref.locale).toAppLocale()
//        savedLocale.value = secureSharedPref.getStringValue(SecureSharedPref.locale).toAppLocale()
    }

    fun selectLocale(appLanguageModel: AppLanguageModel) {
        dialogLanguageString.value = appLanguageModel.text
        selectedLocale.value = appLanguageModel.locale
    }

    fun toggleDialog() {
        _dialogVisible.value = !_dialogVisible.value
    }

    fun confirmLocaleChange() {
        //secureSharedPref.storeStringValue(SecureSharedPref.locale, selectedLocale.value.locale)
        savedLocale.value = selectedLocale.value
    }

    val dialogVisible: Boolean get() = _dialogVisible.value

}
