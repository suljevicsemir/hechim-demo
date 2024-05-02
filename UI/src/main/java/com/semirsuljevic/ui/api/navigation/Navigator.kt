package com.semirsuljevic.ui.api.navigation

import androidx.navigation.NavHostController

interface Navigator {

    fun setNavController(navController: NavHostController)
    fun navigate(route: String)
    fun pop()
}
