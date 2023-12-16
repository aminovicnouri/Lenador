package com.aminovic.presentation.screens

sealed class Screens(
    val route: String,
) {
    object HomeScreen : Screens(route = "home_screen")
    object NewOrderScreen : Screens(route = "new_order_screen")
    object OrdersHistoryScreen : Screens(route = "orders_history_screen")
    object SettingsScreen : Screens(route = "settings_screen")
}
