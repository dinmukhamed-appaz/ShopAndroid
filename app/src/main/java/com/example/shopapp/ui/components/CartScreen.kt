package com.example.shopapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.shopapp.ui.components.CartItemRow
import com.example.shopapp.ui.components.CartSummary
import com.example.shopapp.ui.components.EmptyCart
import com.example.shopapp.ui.theme.extra
import com.example.shopapp.ui.theme.spacing
import com.example.shopapp.ui.viewmodel.CartViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    viewModel: CartViewModel,
    onBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cart", style = MaterialTheme.typography.titleLarge) },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick,
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = MaterialTheme.extra.iconButtonBackground,
                            contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        bottomBar = {
            if (!uiState.isEmpty) {
                CartSummary(
                    totalPrice = uiState.totalPrice,
                    totalCount = uiState.totalCount,
                    onCheckoutClick = viewModel::onCheckout
                )
            }
        }
    ) { innerPadding ->
        if (uiState.isEmpty) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                EmptyCart(onContinueShopping = onBackClick)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentPadding = PaddingValues(
                    horizontal = MaterialTheme.spacing.l,
                    vertical = MaterialTheme.spacing.s
                ),
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.xl)
            ) {
                items(uiState.items, key = { it.product.id }) { item ->
                    CartItemRow(
                        item = item,
                        onIncrease = { viewModel.onIncrease(item.product.id) },
                        onDecrease = { viewModel.onDecrease(item.product.id) },
                        onRemove = { viewModel.onRemove(item.product.id) }
                    )
                }
            }
        }
    }
}