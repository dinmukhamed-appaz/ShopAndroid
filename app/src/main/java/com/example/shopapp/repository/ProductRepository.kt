package com.example.shopapp.repository

import com.example.shopapp.api.RetrofitClient
import com.example.shopapp.dto.ProductDto
import com.example.shopapp.model.Product

import java.util.Locale

object ProductRepository {

    private var cache: List<Product> = emptyList()

    suspend fun fetchProducts(): List<Product> {
        val result = RetrofitClient.api.getProducts().map { it.toProduct() }
        cache = result
        return result
    }

    fun getProductById(id: Int): Product? = cache.find { it.id == id }
}

private fun ProductDto.toProduct() = Product(
    id = id,
    name = title,
    price = String.format(Locale.US, "$%.2f", price),
    imageUrl = image,
    description = description
)
