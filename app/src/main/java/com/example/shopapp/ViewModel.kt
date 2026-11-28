package com.example.shopapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopapp.model.CartItem
import com.example.shopapp.model.Product
import com.example.shopapp.repository.CartRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

data class CartUiState(
    val items: List<CartItem> = emptyList(),
    val totalPrice: Double = 0.0,
    val totalCount: Int = 0
) {
    val isEmpty: Boolean get() = items.isEmpty()
}

class CartViewModel : ViewModel() {

    val uiState: StateFlow<CartUiState> = CartRepository.items
        .map { items ->
            CartUiState(
                items = items,
                totalPrice = items.sumOf { it.totalPrice },
                totalCount = items.sumOf { it.quantity }
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = CartUiState()
        )

    fun onIncrease(productId: Int) {
        viewModelScope.launch { CartRepository.increase(productId) }
    }

    fun onDecrease(productId: Int) {
        viewModelScope.launch { CartRepository.decrease(productId) }
    }

    fun onRemove(productId: Int) {
        viewModelScope.launch { CartRepository.remove(productId) }
    }

    fun onCheckout() {
        viewModelScope.launch { CartRepository.clear() }
    }

    fun onAddToCart(product: Product) {
        viewModelScope.launch { CartRepository.add(product) }
    }
}