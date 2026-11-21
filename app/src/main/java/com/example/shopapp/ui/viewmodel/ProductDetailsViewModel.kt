package com.example.shopapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.shopapp.model.Product
import com.example.shopapp.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.shopapp.repository.CartRepository

data class ProductDetailsUiState(
    val isLoading: Boolean = true,
    val product: Product? = null,
    val error: String? = null
)

class ProductDetailsViewModel(
    productId: Int
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        ProductDetailsUiState()
    )

    val uiState: StateFlow<ProductDetailsUiState> = _uiState

    init {
        val product = ProductRepository.getProductById(productId)

        _uiState.value = if (product != null) {
            ProductDetailsUiState(
                isLoading = false,
                product = product
            )
        } else {
            ProductDetailsUiState(
                isLoading = false,
                error = "Product not found"
            )
        }
    }

    fun onAddToCart() {
        val currentProduct = _uiState.value.product

        if (currentProduct != null) {
            CartRepository.add(currentProduct)
        }
    }
}