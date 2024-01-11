package dev.noelsrocha.panucci.ui.navigation.graphs

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import dev.noelsrocha.panucci.ui.screens.MenuListScreen
import dev.noelsrocha.panucci.ui.viewmodels.MenuListViewModel

const val menuListRoute = "menu"

fun NavGraphBuilder.menuListScreen(navController: NavHostController) {
    composable(menuListRoute) {
        val viewModel = viewModel<MenuListViewModel>()
        val uiState by viewModel.uiState.collectAsState()

        MenuListScreen(
            uiState = uiState,
            onNavigateToDetails = { product ->
                navController.navigateToProductDetails(product.id)
            }
        )
    }
}

fun NavController.navigateToMenuList(navOptions: NavOptions? = null) {
    navigate(menuListRoute, navOptions)
}