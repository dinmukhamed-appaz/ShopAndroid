
package com.example.shopapp.repository

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.shopapp.model.ProductSortOrder
import com.example.shopapp.model.UserSettings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.userSettingsDataStore by preferencesDataStore(
    name = "user_settings"
)

class SettingsRepository(context: Context) {

    private val dataStore =
        context.applicationContext.userSettingsDataStore

    private object PreferencesKeys {
        val DARK_THEME = booleanPreferencesKey("dark_theme")
        val SORT_ORDER = stringPreferencesKey("sort_order")
    }

    fun getSettings(): Flow<UserSettings> {
        return dataStore.data.map { preferences ->

            val savedSortOrder = preferences[PreferencesKeys.SORT_ORDER]

            val sortOrder = try {
                savedSortOrder?.let {
                    ProductSortOrder.valueOf(it)
                } ?: ProductSortOrder.NAME
            } catch (e: IllegalArgumentException) {
                ProductSortOrder.NAME
            }

            UserSettings(
                isDarkTheme = preferences[PreferencesKeys.DARK_THEME] ?: false,
                sortOrder = sortOrder
            )
        }
    }

    suspend fun setDarkTheme(enabled: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.DARK_THEME] = enabled
        }
    }

    suspend fun setSortOrder(order: ProductSortOrder) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.SORT_ORDER] = order.name
        }
    }
}
