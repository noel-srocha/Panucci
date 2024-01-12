package dev.noelsrocha.panucci.ui.navigation.graphs

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import dev.noelsrocha.panucci.model.Product
import dev.noelsrocha.panucci.ui.navigation.baseURI
import dev.noelsrocha.panucci.ui.screens.DrinksListScreen
import dev.noelsrocha.panucci.ui.viewmodels.DrinksListViewModel

internal const val drinksListRoute = "drinks"

fun NavGraphBuilder.drinksListScreen(onNavigateToDetails: (Product) -> Unit) {
    composable(
        drinksListRoute,
        deepLinks = listOf(navDeepLink { uriPattern = "$baseURI/$drinksListRoute" })
    ) {
        val viewModel = viewModel<DrinksListViewModel>()
        val uiState by viewModel.uiState.collectAsState()

        DrinksListScreen(
            uiState = uiState,
            onProductClick = onNavigateToDetails
        )
    }
}

fun NavController.navigateToDrinksList(navOptions: NavOptions? = null) {
    navigate(drinksListRoute, navOptions)
}