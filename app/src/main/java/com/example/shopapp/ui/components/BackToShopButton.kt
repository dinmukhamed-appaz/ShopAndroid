package com.example.shopapp.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BackToShopButton(
    onClick: () -> Unit
) {
    TextButton(
        onClick = onClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("←")
            Text(
                text = "Back to shop",
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}