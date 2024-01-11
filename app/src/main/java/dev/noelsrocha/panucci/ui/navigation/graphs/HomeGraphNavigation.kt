package dev.noelsrocha.panucci.ui.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navOptions
import androidx.navigation.navigation
import dev.noelsrocha.panucci.ui.components.BottomAppBarItem

internal const val homeGraphRoute = "home"

fun NavGraphBuilder.homeGraph(navController: NavHostController) {
    navigation(startDestination = highlightsListRoute, route = homeGraphRoute) {
        highlightsListScreen(navController)
        menuListScreen(navController)
        drinksListScreen(navController)
    }
}

fun NavController.navigateToHomeGraph() {
    navigate(homeGraphRoute)
}

fun NavController.navigateSingleTopWithPopUpTo(
    item: BottomAppBarItem
) {
    val (route, navigate) = when (item) {
        BottomAppBarItem.HighlightsList -> Pair(highlightsListRoute, ::navigateToHighlightsList)
        BottomAppBarItem.DrinksList -> Pair(drinksListRoute, ::navigateToDrinksList)
        BottomAppBarItem.MenuList -> Pair(menuListRoute, ::navigateToMenuList)
    }

    val navOptions = navOptions {
        launchSingleTop = true
        popUpTo(route)
    }

    navigate(navOptions)
}