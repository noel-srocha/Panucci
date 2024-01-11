package dev.noelsrocha.panucci.ui.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import dev.noelsrocha.panucci.sampledata.sampleProducts
import dev.noelsrocha.panucci.ui.screens.MenuListScreen

const val menuListRoute = "menu"

fun NavGraphBuilder.menuListScreen(navController: NavHostController) {
    composable(menuListRoute) {
        MenuListScreen(
            products = sampleProducts,
            onNavigateToDetails = { product ->
                navController.navigateToProductDetails(product.id)
            }
        )
    }
}

fun NavController.navigateToMenuList(navOptions: NavOptions? = null) {
    navigate(menuListRoute, navOptions)
}