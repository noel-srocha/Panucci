package dev.noelsrocha.panucci.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import dev.noelsrocha.panucci.ui.navigation.graphs.checkoutScreen
import dev.noelsrocha.panucci.ui.navigation.graphs.homeGraph
import dev.noelsrocha.panucci.ui.navigation.graphs.homeGraphRoute
import dev.noelsrocha.panucci.ui.navigation.graphs.navigateToCheckout
import dev.noelsrocha.panucci.ui.navigation.graphs.navigateToProductDetails
import dev.noelsrocha.panucci.ui.navigation.graphs.productDetailsScreen

@Composable
fun PanucciNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = homeGraphRoute
    ) {
        homeGraph(
            onNavigateToCheckout = { navController.navigateToCheckout() },
            onNavigateToProductDetails = { product -> navController.navigateToProductDetails(product.id) }
        )
        productDetailsScreen(
            onBackStack = { navController.navigateUp() },
            onNavigateToCheckout = { navController.navigateToCheckout() }
        )
        checkoutScreen(
            onPopBackStack = {
                navController
                    .currentBackStackEntry
                    ?.savedStateHandle
                    ?.set("order_done", "Order completed! 👌")
                navController.navigateUp()
            }
        )
    }
}