package dev.noelsrocha.panucci.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.noelsrocha.panucci.daos.ProductDao
import dev.noelsrocha.panucci.ui.uistate.ProductDetailsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductDetailsViewModel(
    private val dao: ProductDao = ProductDao()
) : ViewModel() {

    private val _uiState = MutableStateFlow<ProductDetailsUiState>(ProductDetailsUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun findProductById(id: String) {
        _uiState.update { ProductDetailsUiState.Loading }

        viewModelScope.launch {
            val dataState = dao.findById(id)?.let { product ->
                ProductDetailsUiState.Success(product)
            } ?: ProductDetailsUiState.Failure

            _uiState.update { dataState }
        }
    }

}