package dev.noelsrocha.panucci.ui.uistate

import dev.noelsrocha.panucci.model.Product

data class CheckoutUiState(
    val products: List<Product> = emptyList()
)