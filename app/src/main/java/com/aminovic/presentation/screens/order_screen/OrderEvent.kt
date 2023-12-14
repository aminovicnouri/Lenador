package com.aminovic.presentation.screens.order_screen

sealed class OrderEvent {
    data class ShowHideAddProductDialog(val show: Boolean) : OrderEvent()
}
