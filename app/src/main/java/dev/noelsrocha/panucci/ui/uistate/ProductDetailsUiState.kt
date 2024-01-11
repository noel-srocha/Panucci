package dev.noelsrocha.panucci.ui.uistate

import dev.noelsrocha.panucci.model.Product

sealed class ProductDetailsUiState {
    data object Loading : ProductDetailsUiState()
    data object Failure : ProductDetailsUiState()
    class Success(val product: Product) : ProductDetailsUiState()
}