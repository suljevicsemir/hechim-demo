package com.semirsuljevic.hechimdemo

enum class HechimBrandFlavor (val dimension: HechimFlavorDimension, val applicationId: String? = null) {
    dw(HechimFlavorDimension.version,"com.semirsuljevic.hechim.dev"),
    dweu(HechimFlavorDimension.version,"ba.semirsuljevic.hechim")
}
