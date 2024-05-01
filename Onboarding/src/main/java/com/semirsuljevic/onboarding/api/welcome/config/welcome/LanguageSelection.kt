package com.semirsuljevic.onboarding.api.welcome.config.welcome

import kotlinx.serialization.Serializable

@Serializable
data class AppSettings(
    val locale: AppLocale? = null,
    val isFirstRun: Boolean = true
)

data class AppLanguageModel(
    val image: Int,
    val text: String,
    val locale: AppLocale,
    val selected: Boolean,
)


enum class AppLocale(val locale: String) {
    English("en"),
    French("fr"),
    Unsupported("")
}

fun String?.toAppLocale() : AppLocale{
    return when(this) {
        "fr" -> AppLocale.French
        else -> AppLocale.English
    }
}
