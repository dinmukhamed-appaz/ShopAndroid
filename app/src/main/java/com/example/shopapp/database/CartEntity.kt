package com.example.shopapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
data class CartEntity(
    @PrimaryKey val productId: Int,
    val name: String,
    val price: String,
    val imageUrl: String,
    val description: String,
    val quantity: Int
)