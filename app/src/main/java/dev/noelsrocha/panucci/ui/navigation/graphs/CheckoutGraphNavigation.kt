package dev.noelsrocha.panucci.ui.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import dev.noelsrocha.panucci.sampledata.sampleProducts
import dev.noelsrocha.panucci.ui.screens.CheckoutScreen

private const val checkoutRoute = "checkout"

fun NavGraphBuilder.checkoutScreen(navController: NavHostController) {
    composable(checkoutRoute) {
        CheckoutScreen(
            products = sampleProducts,
            onPopbackStack = {
                navController.navigateUp()
            }
        )
    }
}

fun NavController.navigateToCheckout() {
    navigate(checkoutRoute)
}