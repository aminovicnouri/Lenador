package com.aminovic.presentation.screens.order_screen

import com.aminovic.lenador.domain.modal.Order
import com.aminovic.lenador.domain.modal.Product

data class OrderScreenState(
    val addProductDialogShown: Boolean = false,
    val products: List<Product> = emptyList(),
    val order: Order = Order()
)
