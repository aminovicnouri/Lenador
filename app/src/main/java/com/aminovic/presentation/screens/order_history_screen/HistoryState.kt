package com.aminovic.presentation.screens.order_history_screen

import com.aminovic.lenador.domain.modal.Order

data class HistoryState(
    val orders: List<Order> = emptyList(),
    val total: Double = 0.0,
    val quantity: Int = 0,
    val items: Int = 0
)
