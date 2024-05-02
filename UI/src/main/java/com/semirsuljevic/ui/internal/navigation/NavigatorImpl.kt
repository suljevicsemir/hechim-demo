package com.semirsuljevic.ui.internal.navigation

import androidx.navigation.NavHostController
import com.semirsuljevic.ui.api.navigation.Navigator
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigatorImpl @Inject constructor(

): Navigator {
    private lateinit var navigator: NavHostController
    override fun setNavController(navController: NavHostController) {
        navigator = navController
    }

    override fun navigate(route: String) {
        navigator.navigate(route)
    }

    override fun pop() {
        navigator.navigateUp()
    }
}
