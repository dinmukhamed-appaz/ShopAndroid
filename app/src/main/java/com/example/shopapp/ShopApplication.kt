package com.example.shopapp

import android.app.Application
import com.example.shopapp.database.AppDatabase
import com.example.shopapp.repository.CartRepository

class ShopApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        CartRepository.init(AppDatabase.getInstance(this))
    }
}