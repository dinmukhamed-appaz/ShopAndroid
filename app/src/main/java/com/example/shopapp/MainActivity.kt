
package com.example.shopapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.shopapp.repository.SettingsRepository
import com.example.shopapp.ui.navigation.ShopNavigation
import com.example.shopapp.ui.theme.ShopAppTheme
import com.example.shopapp.ui.viewmodel.SettingsViewModel
import com.example.shopapp.ui.viewmodel.SettingsViewModelFactory

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            val settingsRepository = remember {
                SettingsRepository(applicationContext)
            }

            val settingsViewModel: SettingsViewModel = viewModel(
                factory = SettingsViewModelFactory(settingsRepository)
            )

            val settings by settingsViewModel.settings.collectAsState()

            ShopAppTheme(
                darkTheme = settings.isDarkTheme
            ) {
                ShopNavigation()
            }
        }
    }
}
