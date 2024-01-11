package dev.noelsrocha.panucci.ui.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import dev.noelsrocha.panucci.sampledata.sampleProducts
import dev.noelsrocha.panucci.ui.screens.DrinksListScreen

const val drinksListRoute = "drinks"

fun NavGraphBuilder.drinksListScreen(navController: NavHostController) {
    composable(drinksListRoute) {
        DrinksListScreen(
            products = sampleProducts,
            onNavigateToDetails = { product ->
                navController.navigateToProductDetails(product.id)
            }
        )
    }
}

fun NavController.navigateToDrinksList(navOptions: NavOptions? = null) {
    navigate(drinksListRoute, navOptions)
}