package com.aminovic.lenador.domain.modal

import java.time.LocalDateTime

data class  Order(
    val id: Int? = null,
    val items: List<OrderItem> = emptyList(),
    val total: Double = 0.0,
    val subTotal: Double = 0.0,
    val tax: Double = 0.0,
    val discount: Double = 0.0,
    val quantity: Int = 0,
    val time: LocalDateTime = LocalDateTime.now(),
)
