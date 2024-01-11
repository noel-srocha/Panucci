package dev.noelsrocha.panucci.ui.navigation.graphs

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import dev.noelsrocha.panucci.ui.screens.DrinksListScreen
import dev.noelsrocha.panucci.ui.viewmodels.DrinksListViewModel

const val drinksListRoute = "drinks"

fun NavGraphBuilder.drinksListScreen(navController: NavHostController) {
    composable(drinksListRoute) {
        val viewModel = viewModel<DrinksListViewModel>()
        val uiState by viewModel.uiState.collectAsState()

        DrinksListScreen(
            uiState = uiState,
            onNavigateToDetails = { product ->
                navController.navigateToProductDetails(product.id)
            }
        )
    }
}

fun NavController.navigateToDrinksList(navOptions: NavOptions? = null) {
    navigate(drinksListRoute, navOptions)
}