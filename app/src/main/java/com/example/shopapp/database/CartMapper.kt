package com.example.shopapp.database

import com.example.shopapp.model.CartItem
import com.example.shopapp.model.Product

fun CartEntity.toCartItem() = CartItem(
    product = Product(
        id = productId,
        name = name,
        price = price,
        imageUrl = imageUrl,
        description = description
    ),
    quantity = quantity
)

fun Product.toEntity(quantity: Int = 1) = CartEntity(
    productId = id,
    name = name,
    price = price,
    imageUrl = imageUrl,
    description = description,
    quantity = quantity
)