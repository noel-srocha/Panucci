package dev.noelsrocha.panucci.ui.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import br.com.alura.panucci.ui.screens.HighlightsListScreen
import dev.noelsrocha.panucci.sampledata.sampleProducts

const val highlightsListRoute = "highlights"

fun NavGraphBuilder.highlightsListScreen(navController: NavHostController) {
    composable(highlightsListRoute) {
        HighlightsListScreen(
            products = sampleProducts,
            onNavigateToDetails = { product ->
                navController.navigateToProductDetails(product.id)
            },
            onNavigateToCheckout = {
                navController.navigateToCheckout()
            }
        )
    }
}

fun NavController.navigateToHighlightsList(navOptions: NavOptions? = null) {
    navigate(highlightsListRoute, navOptions)
}