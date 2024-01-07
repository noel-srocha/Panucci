package dev.noelsrocha.panucci.ui.navigation

sealed class AppDestination(val route: String) {
    data object Highlight : AppDestination("highlight")
    data object Menu : AppDestination("menu")
    data object Drinks : AppDestination("drinks")
    data object ProductDetails : AppDestination("productDetails")
    data object Checkout : AppDestination("checkout")
}