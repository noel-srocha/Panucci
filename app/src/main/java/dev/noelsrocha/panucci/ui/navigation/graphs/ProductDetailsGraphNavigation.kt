package dev.noelsrocha.panucci.ui.navigation.graphs

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.noelsrocha.panucci.ui.screens.ProductDetailsScreen
import dev.noelsrocha.panucci.ui.viewmodels.ProductDetailsViewModel

const val productDetailsRoute = "productDetails"
private const val productIdArgument = "productId"

fun NavGraphBuilder.productDetailsScreen(
    onNavigateToCheckout: () -> Unit,
    onBackStack: () -> Unit
) {
    composable("$productDetailsRoute/$productIdArgument") { backStackEntry ->
        backStackEntry.arguments?.getString(productIdArgument)?.let { id ->
            val viewModel = viewModel<ProductDetailsViewModel>()
            val uiState by viewModel.uiState.collectAsState()

            LaunchedEffect(Unit) {
                viewModel.findProductById(id)
            }

            ProductDetailsScreen(
                uiState = uiState,
                onNavigateToCheckout = onNavigateToCheckout,
                onBackStack = onBackStack,
                onTryFindProduct = { viewModel.findProductById(id) },
            )
        }
    }
}

fun NavController.navigateToProductDetails(productId: String) {
    navigate("$productDetailsRoute/$productId")
}