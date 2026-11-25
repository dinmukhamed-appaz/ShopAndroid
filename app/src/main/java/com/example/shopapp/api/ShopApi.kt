package com.example.shopapp.api


import com.example.shopapp.dto.ProductDto
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ShopApi {
    @GET("products")
    suspend fun getProducts(): List<ProductDto>
}

object RetrofitClient {
    val api: ShopApi = Retrofit.Builder()
        .baseUrl("https://api.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ShopApi::class.java)
}