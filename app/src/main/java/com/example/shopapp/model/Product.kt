package com.example.shopapp.model

data class Product(
    val id: Int,
    val name: String,
    val price: String,
    val imageRes: Int,
    val description: String = ""
)
