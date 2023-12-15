package com.aminovic.lenador.domain.modal

data class OrderItem(
    val product: Product,
    val quantity: Int,
    val tax: Double,
    val discount: Double,
    val total: Double,
)
