package com.example.shopapp.util

import java.util.Locale

fun Double.toPriceString(): String = String.format(Locale.US, "$%.2f", this)