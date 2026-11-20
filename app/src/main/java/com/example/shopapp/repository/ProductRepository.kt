package com.example.shopapp.repository

import com.example.shopapp.R
import com.example.shopapp.model.Product

object ProductRepository {
    private val products = listOf(
        Product(1, "T-Shirt Tomorrow", "$12.00",  R.drawable.one),
        Product(2, "Hoodie STWD", "$15.00",  R.drawable.two),
        Product(3, "T-Shirt Tomorrow", "$12.00",  R.drawable.one),
        Product(4, "Hoodie STWD", "$15.00",  R.drawable.two),
        Product(5, "T-Shirt Tomorrow", "$12.00",  R.drawable.one),
        Product(6, "Hoodie STWD", "$15.00",  R.drawable.two),
        Product(7, "T-Shirt Tomorrow", "$12.00",  R.drawable.one),
        Product(8, "Hoodie STWD", "$15.00",  R.drawable.two),
        Product(9, "T-Shirt Tomorrow", "$12.00",  R.drawable.one),
        Product(10, "Hoodie STWD", "$15.00",  R.drawable.two),
        Product(11, "T-Shirt Tomorrow", "$12.00",  R.drawable.one),
        Product(12, "Hoodie STWD", "$15.00",  R.drawable.two),
    )

    fun getProducts(): List<Product> = products
    fun getProductById(id: Int): Product? = products.find { it.id == id }
}