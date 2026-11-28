package com.example.shopapp.repository

import com.example.shopapp.database.AppDatabase
import com.example.shopapp.database.CartDao
import com.example.shopapp.database.toCartItem
import com.example.shopapp.database.toEntity
import com.example.shopapp.model.CartItem
import com.example.shopapp.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

object CartRepository {

    private lateinit var dao: CartDao

    fun init(database: AppDatabase) {
        dao = database.cartDao()
    }

    val items: Flow<List<CartItem>>
        get() = dao.getAll().map { list -> list.map { it.toCartItem() } }

    suspend fun add(product: Product) {
        val existing = dao.getById(product.id)
        if (existing != null) {
            dao.update(existing.copy(quantity = existing.quantity + 1))
        } else {
            dao.insert(product.toEntity())
        }
    }

    suspend fun increase(productId: Int) {
        val item = dao.getById(productId) ?: return
        dao.update(item.copy(quantity = item.quantity + 1))
    }

    suspend fun decrease(productId: Int) {
        val item = dao.getById(productId) ?: return
        if (item.quantity > 1) {
            dao.update(item.copy(quantity = item.quantity - 1))
        } else {
            dao.deleteById(productId)
        }
    }
}