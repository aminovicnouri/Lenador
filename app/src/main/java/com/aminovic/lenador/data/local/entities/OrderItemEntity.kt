package com.aminovic.lenador.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity


@Entity
data class OrderItemEntity(
    @ColumnInfo(name = "productId")
    val productId: Int,

    @ColumnInfo(name = "quantity")
    val quantity: String,

    @ColumnInfo(name = "tax")
    val tax: Double,

    @ColumnInfo(name = "discount")
    val discount: Double,

    @ColumnInfo(name = "total")
    val total: Double,
)