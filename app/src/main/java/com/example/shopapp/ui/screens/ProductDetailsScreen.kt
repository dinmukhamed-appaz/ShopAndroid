package com.example.shopapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.shopapp.ui.components.AddToCartButton
import com.example.shopapp.ui.components.BackToShopButton
import com.example.shopapp.ui.components.ProductImageSection
import com.example.shopapp.ui.components.ProductInfoSection
import com.example.shopapp.ui.viewmodel.ProductDetailsViewModel

@Composable
fun ProductDetailsScreen(
    viewModel: ProductDetailsViewModel,
    onBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        containerColor = Color.White,
        bottomBar = {
            AddToCartButton(onClick = viewModel::onAddToCart)
        }
    ) { innerPadding ->

        when {
            uiState.isLoading -> {
                androidx.compose.foundation.layout.Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            uiState.product != null -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    BackToShopButton(
                        onClick = onBackClick
                    )

                    ProductImageSection(
                        product = uiState.product!!
                    )

                    ProductInfoSection(
                        product = uiState.product!!
                    )
                }
            }

            else -> {
                androidx.compose.foundation.layout.Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = uiState.error ?: "Product not found"
                    )
                }
            }
        }
    }
}