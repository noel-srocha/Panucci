package dev.noelsrocha.panucci.ui.navigation.graphs

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import br.com.alura.panucci.ui.screens.ProductDetailsScreen
import dev.noelsrocha.panucci.sampledata.sampleProducts

private const val productDetailsRoute = "productDetails"
private const val productIdArgument = "productId"

fun NavGraphBuilder.productDetailsScreen(navController: NavHostController) {
    composable("$productDetailsRoute/$productIdArgument") { backStackEntry ->
        val id = backStackEntry.arguments?.getString(productIdArgument)
        sampleProducts.firstOrNull { product -> product.id == id }?.let {
            ProductDetailsScreen(
                product = it,
                onNavigateToCheckout = {
                    navController.navigateToCheckout()
                }
            )
        } ?: LaunchedEffect(Unit) {
            navController.navigateUp()
        }
    }
}

fun NavController.navigateToProductDetails(productId: String) {
    navigate("$productDetailsRoute/$productId")
}