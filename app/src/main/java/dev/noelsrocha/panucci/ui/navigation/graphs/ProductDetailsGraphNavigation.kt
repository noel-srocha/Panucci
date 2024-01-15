package dev.noelsrocha.panucci.ui.navigation.graphs

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import dev.noelsrocha.panucci.ui.navigation.baseURI
import dev.noelsrocha.panucci.ui.screens.ProductDetailsScreen
import dev.noelsrocha.panucci.ui.viewmodels.ProductDetailsViewModel

internal const val productDetailsRoute = "productDetails"
internal const val productIdArgument = "productId"
internal const val productPromoCodeArgument = "promoCode"

fun NavGraphBuilder.productDetailsScreen(
    onNavigateToCheckout: () -> Unit,
    onBackStack: () -> Unit
) {
    composable(
        "$productDetailsRoute/{$productIdArgument}",
        deepLinks = listOf(
            navDeepLink {
                uriPattern = "$baseURI/$productDetailsRoute/{$productIdArgument}?$productPromoCodeArgument={$productPromoCodeArgument}"
            }
        )
    ) { backStackEntry ->
        backStackEntry.arguments?.getString(productIdArgument)?.let { id ->
            val viewModel = viewModel<ProductDetailsViewModel>(factory = ProductDetailsViewModel.Factory)
            val uiState by viewModel.uiState.collectAsState()

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