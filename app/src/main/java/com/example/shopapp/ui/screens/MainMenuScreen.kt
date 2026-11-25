package com.example.shopapp.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.shopapp.R
import com.example.shopapp.model.Product
import com.example.shopapp.repository.ProductRepository
import com.example.shopapp.ui.theme.Paper
import com.example.shopapp.ui.theme.ShopAppTheme
import com.example.shopapp.ui.theme.extra
import com.example.shopapp.ui.theme.spacing
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@Composable
private fun MainMenuScreenPreview() {
    ShopAppTheme { MainMenuScreen() }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainMenuScreen(
    products: List<Product> = ProductRepository.getProducts(),
    onProductClick: (Product) -> Unit = {},
    onAddToCart: (Product) -> Unit = {},
    cartCount: Int = 0,
    onCartClick: () -> Unit = {}
) {
    val gridState = rememberLazyGridState()
    val scope = rememberCoroutineScope()


    var isSearching by remember { mutableStateOf(false) }


    var query by remember { mutableStateOf("") }


    val filteredProducts = products.filter { it.name.contains(query, ignoreCase = true) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = MaterialTheme.spacing.m),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Wearify", style = MaterialTheme.typography.titleLarge)

                        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                            IconButton(
                                onClick = {
                                    isSearching = !isSearching
                                    if (!isSearching) query = ""
                                },
                                colors = IconButtonDefaults.iconButtonColors(
                                    containerColor = MaterialTheme.extra.iconButtonBackground,
                                    contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.search),
                                    contentDescription = "Search",
                                    modifier = Modifier.size(17.dp)
                                )
                            }

                            IconButton(
                                onClick = onCartClick,
                                colors = IconButtonDefaults.iconButtonColors(
                                    containerColor = MaterialTheme.extra.iconButtonBackground,
                                    contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            ) {
                                BadgedBox(
                                    badge = {
                                        if (cartCount > 0) {
                                            Badge(
                                                containerColor = MaterialTheme.colorScheme.primary,
                                                contentColor = MaterialTheme.colorScheme.onPrimary
                                            ) { Text(cartCount.toString()) }
                                        }
                                    }
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.shopping_bag),
                                        contentDescription = "Cart",
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyVerticalGrid(
            state = gridState,
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(
                horizontal = MaterialTheme.spacing.l,
                vertical = MaterialTheme.spacing.s
            ),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.s),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.xl)
        ) {
            if (isSearching) {
                item(span = { GridItemSpan(maxLineSpan) }) {
                    OutlinedTextField(
                        value = query,
                        onValueChange = { query = it },
                        placeholder = { Text("Search products") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                if (filteredProducts.isEmpty()) {
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        Text("Nothing found", style = MaterialTheme.typography.bodyLarge)
                    }
                }
            } else {
                item(span = { GridItemSpan(maxLineSpan) }) { AddressCard("Astana, Kazakhstan") }
                item(span = { GridItemSpan(maxLineSpan) }) {
                    PromoBanner(
                        onShopNowClick = {
                            scope.launch { gridState.animateScrollToItem(2) }
                        }
                    )
                }
                item(span = { GridItemSpan(maxLineSpan) }) {
                    Text("Popular", style = MaterialTheme.typography.titleLarge)
                }
            }

            items(filteredProducts) { product ->
                ProductCard(
                    product = product,
                    onClick = { onProductClick(product) },
                    onAddClick = { onAddToCart(product) }
                )
            }
        }
    }
}

@Composable
fun AddressCard(address: String, modifier: Modifier = Modifier) {
    Surface(
        shape = MaterialTheme.shapes.extraLarge,
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "Location",
                modifier = Modifier
                    .size(40.dp)
                    .background(MaterialTheme.colorScheme.surfaceVariant, CircleShape)
                    .padding(10.dp)
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = MaterialTheme.spacing.m)
            ) {
                Text(
                    "Send to",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(address, style = MaterialTheme.typography.bodyLarge)
            }
            Button(onClick = {}) { Text("Change") }
        }
    }
}

@Composable
fun PromoBanner(
    onShopNowClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(180.dp)
            .clip(MaterialTheme.shapes.large)
            .background(
                Brush.horizontalGradient(
                    listOf(MaterialTheme.extra.bannerStart, MaterialTheme.extra.bannerEnd)
                )
            )
    ) {
        Box(
            modifier = Modifier
                .size(160.dp)
                .align(Alignment.CenterEnd)
                .offset(x = 40.dp)
                .background(Paper.copy(alpha = 0.08f), CircleShape)
        )

        Box(
            modifier = Modifier
                .size(70.dp)
                .align(Alignment.TopEnd)
                .offset(x = (-30).dp, y = 20.dp)
                .background(Paper.copy(alpha = 0.15f), CircleShape)
        )

        Column(
            modifier = Modifier
                .padding(MaterialTheme.spacing.l)
                .align(Alignment.CenterStart),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.s)
        ) {
            Text(
                "Don't miss out —",
                style = MaterialTheme.typography.titleMedium,
                color = Paper
            )
            Text(
                "Save up to 50% on your\nfavorite products.",
                style = MaterialTheme.typography.bodyMedium,
                color = Paper
            )
            Button(
                onClick = onShopNowClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Paper,
                    contentColor = MaterialTheme.colorScheme.onSurface
                )
            ) { Text("Shop Now") }
        }
    }
}

@Composable
fun ProductCard(
    product: Product,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    onAddClick: () -> Unit = {}
) {
    Column(modifier = modifier.fillMaxWidth()) {
        AsyncImage(
            model = product.imageUrl,
            contentDescription = product.name,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2f / 3f)
                .clip(MaterialTheme.shapes.extraSmall)
                .clickable(onClick = onClick)
                .background(
                    Brush.verticalGradient(
                        listOf(MaterialTheme.extra.imageTop, MaterialTheme.extra.imageBottom)
                    )
                ),
            contentScale = ContentScale.Crop
        )

        Spacer(Modifier.height(MaterialTheme.spacing.s))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.spacing.s),
            verticalAlignment = Alignment.Top
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = product.name.uppercase(),
                    style = MaterialTheme.typography.labelMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.height(MaterialTheme.spacing.xs))
                Text(
                    text = product.price,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            IconButton(
                onClick = onAddClick,
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add to cart",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}