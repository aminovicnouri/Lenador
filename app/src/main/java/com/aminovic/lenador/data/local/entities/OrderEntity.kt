package com.aminovic.lenador.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.aminovic.lenador.data.local.type_converters.OrderItemEntityConverters
import com.aminovic.lenador.domain.modal.OrderStatus

@Entity(tableName = ORDERS_TABLE_NAME)
@TypeConverters(OrderItemEntityConverters::class)
data class OrderEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int? = null,

    @ColumnInfo(name = "items")
    val items: List<OrderItemEntity> = emptyList(),

    @ColumnInfo(name = "status")
    val status: OrderStatus,

    @ColumnInfo(name = "total")
    val total: Double,

    @ColumnInfo(name = "time")
    val time: Long,

    @ColumnInfo(name = "subTotal")
    val subTotal: Double,

    @ColumnInfo(name = "tax")
    val tax: Double,

    @ColumnInfo(name = "discount")
    val discount: Double,

    @ColumnInfo(name = "quantity")
    val quantity: Int,
)

const val ORDERS_TABLE_NAME = "orders_table"