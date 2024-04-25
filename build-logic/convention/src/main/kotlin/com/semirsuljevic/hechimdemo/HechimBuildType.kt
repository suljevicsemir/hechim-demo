package com.semirsuljevic.hechimdemo

enum class HechimBuildType(val applicationIdSuffix:String? = null) {
    DEBUG(".debug"),
    BETA(".qa"),
    STAGING(".staging"),
    RELEASE
}
