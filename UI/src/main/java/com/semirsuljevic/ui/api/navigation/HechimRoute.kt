package com.semirsuljevic.ui.api.navigation

abstract class HechimRoute(val path: String, private var finalPath: String = "") {

    val final get() = finalPath


    fun addArgument(argument: String?) {
        if(argument == null) {
            return
        }
        finalPath = path.plus("/$argument")
    }
}
