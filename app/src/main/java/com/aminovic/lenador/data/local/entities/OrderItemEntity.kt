package com.aminovic.lenador.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class OrderItemEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int? = null,

    @ColumnInfo(name = "productId")
    val productId: Int,

    @ColumnInfo(name = "quantity")
    val quantity: Int,

    @ColumnInfo(name = "tax")
    val tax: Double,

    @ColumnInfo(name = "discount")
    val discount: Double,

    @ColumnInfo(name = "total")
    val total: Double,
)