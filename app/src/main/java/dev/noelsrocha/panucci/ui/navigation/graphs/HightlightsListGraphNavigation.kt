package dev.noelsrocha.panucci.ui.navigation.graphs

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import dev.noelsrocha.panucci.model.Product
import dev.noelsrocha.panucci.ui.screens.HighlightsListScreen
import dev.noelsrocha.panucci.ui.viewmodels.HighlightsListViewModel

internal const val highlightsListRoute = "highlights"

fun NavGraphBuilder.highlightsListScreen(
    onNavigateToDetails: (Product) -> Unit,
    onNavigateToCheckout: () -> Unit
) {
    composable(highlightsListRoute) {
        val viewModel = viewModel<HighlightsListViewModel>()
        val uiState by viewModel.uiState.collectAsState()

        HighlightsListScreen(
            uiState = uiState,
            onProductClick = onNavigateToDetails,
            onOrderClick = onNavigateToCheckout
        )
    }
}

fun NavController.navigateToHighlightsList(navOptions: NavOptions? = null) {
    navigate(highlightsListRoute, navOptions)
}