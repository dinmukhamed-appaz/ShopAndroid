package com.example.shopapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.shopapp.ui.screens.CartScreen
import com.example.shopapp.ui.screens.MainMenuScreen
import com.example.shopapp.ui.screens.ProductDetailsScreen
import com.example.shopapp.ui.viewmodel.CartViewModel
import com.example.shopapp.ui.viewmodel.ProductDetailsViewModel


private object Routes {
    const val MAIN = "main"
    const val DETAILS = "product_details/{productId}"
    const val CART = "cart"

    fun details(productId: Int) = "product_details/$productId"
}

@Composable
fun ShopNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.MAIN
    ) {
        composable(Routes.MAIN) {
            MainMenuScreen(
                onProductClick = { product ->
                    navController.navigate(
                        Routes.details(product.id)
                    )
                },
                onCartClick = { navController.navigate(Routes.CART) }
            )
        }

        composable(Routes.CART) {
            val cartViewModel: CartViewModel = viewModel()

            CartScreen(
                viewModel = cartViewModel,
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(
            route = Routes.DETAILS,
            arguments = listOf(
                navArgument("productId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val productId = requireNotNull(
                backStackEntry.arguments?.getInt("productId")
            )

            val productViewModel: ProductDetailsViewModel = viewModel(
                key = "product_details_$productId",
                factory = object : ViewModelProvider.Factory {
                    @Suppress("UNCHECKED_CAST")
                    override fun <T : ViewModel> create(
                        modelClass: Class<T>
                    ): T {
                        return ProductDetailsViewModel(productId) as T
                    }
                }
            )

            ProductDetailsScreen(
                viewModel = productViewModel,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}