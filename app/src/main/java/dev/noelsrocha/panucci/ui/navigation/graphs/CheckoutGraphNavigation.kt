package dev.noelsrocha.panucci.ui.navigation.graphs

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.noelsrocha.panucci.ui.screens.CheckoutScreen
import dev.noelsrocha.panucci.ui.viewmodels.CheckoutViewModel

internal const val checkoutRoute = "checkout"

fun NavGraphBuilder.checkoutScreen(onPopBackStack: () -> Unit) {
    composable(checkoutRoute) {
        val viewModel = viewModel<CheckoutViewModel>()
        val uiState by viewModel.uiState.collectAsState()

        CheckoutScreen(uiState = uiState, onOrderClick = onPopBackStack)
    }
}

fun NavController.navigateToCheckout() {
    navigate(checkoutRoute)
}