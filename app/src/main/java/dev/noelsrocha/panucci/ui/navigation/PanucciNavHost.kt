package dev.noelsrocha.panucci.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import dev.noelsrocha.panucci.ui.navigation.graphs.checkoutScreen
import dev.noelsrocha.panucci.ui.navigation.graphs.homeGraph
import dev.noelsrocha.panucci.ui.navigation.graphs.homeGraphRoute
import dev.noelsrocha.panucci.ui.navigation.graphs.productDetailsScreen

@Composable
fun PanucciNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = homeGraphRoute
    ) {
        homeGraph(navController)
        productDetailsScreen(navController)
        checkoutScreen(navController)
    }
}