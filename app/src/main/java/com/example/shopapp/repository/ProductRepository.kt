package com.example.shopapp.repository

import com.example.shopapp.R
import com.example.shopapp.model.Product

object ProductRepository {
    private val products = listOf(
        Product(1, "T-Shirt Tomorrow", "$12.00",  R.drawable.one),
        Product(2, "Hoodie STWD", "$15.00",  R.drawable.two),
        Product(3, "T-Shirt Tomorrow", "$12.00",  R.drawable.one),
        Product(4, "Hoodie STWD", "$15.00",  R.drawable.two),
    )

    fun getProducts(): List<Product> = products
    fun getProductById(id: Int): Product? = products.find { it.id == id }
}