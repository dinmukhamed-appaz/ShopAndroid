package com.example.shopapp.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shopapp.ui.theme.extra
import com.example.shopapp.ui.theme.spacing

@Composable
fun QuantitySelector(
    quantity: Int,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        QuantityButton(
            icon = Icons.Default.Remove,
            description = "Decrease quantity",
            onClick = onDecrease
        )

        Text(
            text = quantity.toString(),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.m)
        )

        QuantityButton(
            icon = Icons.Default.Add,
            description = "Increase quantity",
            onClick = onIncrease
        )
    }
}

@Composable
private fun QuantityButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    description: String,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier.size(32.dp),
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = MaterialTheme.extra.iconButtonBackground,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    ) {
        Icon(
            imageVector = icon,
            contentDescription = description,
            modifier = Modifier.size(16.dp)
        )
    }
}

@Preview
@Composable
fun QuantitySelectorPreview() {
    QuantitySelector(
        quantity = 1,
        onIncrease = {},
        onDecrease = {}
    )
}