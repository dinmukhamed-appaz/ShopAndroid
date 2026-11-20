package com.example.shopapp.model

data class CartItem(
    val product: Product,
    val quantity: Int = 1
) {

}