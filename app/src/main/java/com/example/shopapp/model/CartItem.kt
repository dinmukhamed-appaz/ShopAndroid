package com.example.shopapp.model

data class CartItem(
    val product: Product,
    val quantity: Int = 1
) {
    val unitPrice: Double get() = product.price.replace("$", "").toDoubleOrNull() ?: 0.0
}