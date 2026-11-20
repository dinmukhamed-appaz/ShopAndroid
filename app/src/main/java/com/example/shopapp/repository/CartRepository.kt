package com.example.shopapp.repository

import com.example.shopapp.model.CartItem
import com.example.shopapp.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

object CartRepository {

    private val _items = MutableStateFlow<List<CartItem>>(emptyList())
    val items: StateFlow<List<CartItem>> = _items.asStateFlow()

    fun add(product: Product) {
        _items.update { list ->
            if (list.any { it.product.id == product.id }) {
                list.map {
                    if (it.product.id == product.id) it.copy(quantity = it.quantity + 1) else it
                }
            } else {
                list + CartItem(product)
            }
        }
    }

    fun increase(productId: Int) {
        val newList = _items.value.toMutableList()
        for (i in newList.indices) {
            val item = newList[i]

            if (item.product.id == productId) {
                newList[i] = item.copy(quantity = item.quantity + 1)
                break
            }
        }

        _items.value = newList
    }

    fun decrease(productId: Int) {
        val newList = _items.value.toMutableList()
        for (i in newList.indices) {
            val item = newList[i]

            if (item.product.id == productId) {
                if (item.quantity > 1) {
                    newList[i] = item.copy(quantity = item.quantity - 1)
                } else {
                    newList.removeAt(i)
                }
                break
            }
        }

        _items.value = newList
    }
}