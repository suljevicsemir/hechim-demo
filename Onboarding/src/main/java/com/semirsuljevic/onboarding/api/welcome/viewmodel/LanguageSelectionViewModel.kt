package com.semirsuljevic.onboarding.api.welcome.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import com.hechimdemo.onboarding.R
import com.semirsuljevic.onboarding.api.welcome.config.welcome.AppLanguageModel
import com.semirsuljevic.onboarding.api.welcome.config.welcome.AppLocale
import com.semirsuljevic.onboarding.api.welcome.config.welcome.toAppLocale
import com.semirsuljevic.storage.api.secure.SecureStorage
import com.semirsuljevic.ui.api.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LanguageSelectionViewModel @Inject constructor(
    private val secureStorage: SecureStorage,
    private val navigator: Navigator
): ViewModel() {

    private val _dialogVisible = mutableStateOf(false)

    val selectedLocale = mutableStateOf<AppLanguageModel?>(null)

    val savedLocale = mutableStateOf(AppLocale.English)

    init {
        savedLocale.value = secureStorage.getStringValue(SecureStorage.locale).toAppLocale()
    }

    fun selectLocale(appLanguageModel: AppLanguageModel) {
        if(savedLocale.value == appLanguageModel.locale) {
            return
        }
        selectedLocale.value = appLanguageModel
        toggleDialog()
    }

    fun toggleDialog() {
        _dialogVisible.value = !_dialogVisible.value
    }

    fun confirmLocaleChange() {
        if(selectedLocale.value == null) {
            return
        }
        savedLocale.value = selectedLocale.value!!.locale
        secureStorage.storeStringValue(SecureStorage.locale, savedLocale.value.locale)
        toggleDialog()
    }

    val dialogVisible: Boolean get() = _dialogVisible.value

    val languages = listOf(
        AppLanguageModel(
            image = R.drawable.ic_language_fr,
            text = R.string.app_language_fr,
            locale = AppLocale.French,
        ),
        AppLanguageModel(
            image = R.drawable.ic_language_en,
            text = R.string.app_language_en,
            locale = AppLocale.English
        ),
        AppLanguageModel(
            image = R.drawable.ic_language_de,
            text = R.string.app_language_de,
            locale = AppLocale.Unsupported
        )
    )

    fun proceed() {
        navigator.navigate("")
    }

}
