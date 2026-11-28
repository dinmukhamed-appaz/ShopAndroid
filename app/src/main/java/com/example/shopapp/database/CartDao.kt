package com.example.shopapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Query("SELECT * FROM cart_items")
    fun getAll(): Flow<List<CartEntity>>

    @Query("SELECT * FROM cart_items WHERE productId = :productId")
    suspend fun getById(productId: Int): CartEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: CartEntity)

    @Update
    suspend fun update(item: CartEntity)

    @Query("DELETE FROM cart_items WHERE productId = :productId")
    suspend fun deleteById(productId: Int)

    @Query("DELETE FROM cart_items")
    suspend fun clear()
}