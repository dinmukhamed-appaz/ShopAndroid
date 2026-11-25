package com.example.shopapp.repository

import com.example.shopapp.R
import com.example.shopapp.model.Product

object ProductRepository {

    private var cache: List<Product> = emptyList()

    suspend fun fetchProducts(): List<Product> {
        val result = RetrofitClient.api.getProducts().map { it.toProduct() }
    }

    fun getProducts(): List<Product> = products

    fun getProductById(id: Int): Product? {
        return products.find { it.id == id }
    }
}