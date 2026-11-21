# Wearify (ShopAndroid)

A clothing shop Android app built with **Kotlin** and **Jetpack Compose**.
Users can browse products, open product details, and manage a shopping cart.

## Group Members

| Name             | GitHub                                                     |
|------------------|------------------------------------------------------------|
| Dimukhamed Appaz | [@dinmukhamed-appaz](https://github.com/dinmukhamed-appaz) |
| Nursultan Satbek | [@username](https://github.com/Nurssult)                   |
| Erdos Kuanyshbek | [@username](https://github.com/Erdos06)                    |

## Main Features

- Product catalog shown as a 2-column grid
- Promo banner with a "Shop Now" button that scrolls to the products
- Product details screen (image, name, price, description)
- Add to cart from the catalog or from the details screen
- Cart screen: increase, decrease, and remove items
- Cart badge on the main screen showing the total item count
- Total price and checkout (clears the cart)
- Empty cart state and "back to shop" button
- Custom theme (colors, shapes, spacing, Geist font)

## Architecture (MVVM)

```
app/src/main/java/com/example/shopapp/
├── model/          Product, CartItem
├── repository/     ProductRepository, CartRepository
├── ui/
│   ├── viewmodel/  CartViewModel, ProductDetailsViewModel
│   ├── screens/    MainMenuScreen, ProductDetailsScreen
│   ├── components/ Reusable composables (CartItemRow, QuantitySelector, ...)
│   ├── navigation/ NavGraph
│   └── theme/      Color, Type, Shape, Spacing, Theme
└── util/           PriceFormat
```

- **Model**: data classes that describe the app data.
- **Repository**: stores and changes data (products list, cart items).
- **ViewModel**: holds UI state as `StateFlow` and handles user actions.
- **View**: Compose screens that observe the state and update automatically.

## Navigation

Implemented with **Navigation Compose**.

| Route | Screen |
|-------|--------|
| `main` | Main menu (product catalog) |
| `product_details/{productId}` | Product details (receives the product ID as an argument) |
| `cart` | Shopping cart |

Back navigation works with the back button on each screen and the system back gesture.

## Tech Stack

- Kotlin
- Jetpack Compose + Material 3
- Navigation Compose
- ViewModel + StateFlow
- Min SDK 24

## How to Run

1. Clone the repository:
   ```
   git clone https://github.com/dinmukhamed-appaz/ShopAndroid.git
   ```
2. Open the project in Android Studio.
3. Wait for the Gradle sync to finish.
4. Run the `app` configuration on an emulator or a device.
