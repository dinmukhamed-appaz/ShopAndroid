package com.example.shopapp.repository

import com.example.shopapp.R
import com.example.shopapp.model.Product

object ProductRepository {

    private val products = listOf(
        Product(
            1,
            "T-Shirt Tomorrow",
            "$12.00",
            R.drawable.one,
            "A comfortable everyday T-shirt with a clean and modern design."
        ),
        Product(
            2,
            "Hoodie STWD",
            "$15.00",
            R.drawable.two,
            "A relaxed hoodie designed for everyday comfort and casual style."
        ),
        Product(
            3,
            "T-Shirt Tomorrow",
            "$12.00",
            R.drawable.one,
            "A comfortable everyday T-shirt with a clean and modern design."
        ),
        Product(
            4,
            "Hoodie STWD",
            "$15.00",
            R.drawable.two,
            "A relaxed hoodie designed for everyday comfort and casual style."
        ),
        Product(
            5,
            "T-Shirt Tomorrow",
            "$12.00",
            R.drawable.one,
            "A comfortable everyday T-shirt with a clean and modern design."
        ),
        Product(
            6,
            "Hoodie STWD",
            "$15.00",
            R.drawable.two,
            "A relaxed hoodie designed for everyday comfort and casual style."
        ),
        Product(
            7,
            "T-Shirt Tomorrow",
            "$12.00",
            R.drawable.one,
            "A comfortable everyday T-shirt with a clean and modern design."
        ),
        Product(
            8,
            "Hoodie STWD",
            "$15.00",
            R.drawable.two,
            "A relaxed hoodie designed for everyday comfort and casual style."
        ),
        Product(
            9,
            "T-Shirt Tomorrow",
            "$12.00",
            R.drawable.one,
            "A comfortable everyday T-shirt with a clean and modern design."
        ),
        Product(
            10,
            "Hoodie STWD",
            "$15.00",
            R.drawable.two,
            "A relaxed hoodie designed for everyday comfort and casual style."
        ),
        Product(
            11,
            "T-Shirt Tomorrow",
            "$12.00",
            R.drawable.one,
            "A comfortable everyday T-shirt with a clean and modern design."
        ),
        Product(
            12,
            "Hoodie STWD",
            "$15.00",
            R.drawable.two,
            "A relaxed hoodie designed for everyday comfort and casual style."
        )
    )

    fun getProducts(): List<Product> = products

    fun getProductById(id: Int): Product? {
        return products.find { it.id == id }
    }
}