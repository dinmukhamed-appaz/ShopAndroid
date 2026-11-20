package com.example.shopapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopapp.model.CartItem
import com.example.shopapp.repository.CartRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

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

    fun onIncrease(productId: Int) = CartRepository.increase(productId)

    fun onDecrease(productId: Int) = CartRepository.decrease(productId)

    fun onRemove(productId: Int) = CartRepository.remove(productId)

    fun onCheckout() = CartRepository.clear()
}