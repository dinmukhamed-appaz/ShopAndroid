
package com.example.shopapp.model

enum class ProductSortOrder {
    NAME,
    PRICE_ASCENDING,
    PRICE_DESCENDING
}

data class UserSettings(
    val isDarkTheme: Boolean = false,
    val sortOrder: ProductSortOrder = ProductSortOrder.NAME
)
