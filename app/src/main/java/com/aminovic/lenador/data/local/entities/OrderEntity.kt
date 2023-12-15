package com.aminovic.lenador.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.aminovic.lenador.data.local.type_converters.OrderItemEntityConverters

@Entity(tableName = ORDERS_TABLE_NAME)
@TypeConverters(OrderItemEntityConverters::class)
data class OrderEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int? = null,

    @ColumnInfo(name = "items")
    val items: List<OrderItemEntity> = emptyList(),

    @ColumnInfo(name = "total")
    val total: Double,

    @ColumnInfo(name = "time")
    val time: Long,
)

const val ORDERS_TABLE_NAME = "orders_table"