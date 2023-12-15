package com.aminovic.lenador.data.local.type_converters

import androidx.room.TypeConverter
import com.aminovic.lenador.data.local.entities.OrderItemEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class OrderItemEntityConverters {
    @TypeConverter
    fun fromOrderItemList(value: List<OrderItemEntity>?): String {
        val gson = Gson()
        return gson.toJson(value)
    }

    @TypeConverter
    fun toOrderItemList(value: String): List<OrderItemEntity>? {
        val listType = object : TypeToken<List<OrderItemEntity>>() {}.type
        val gson = Gson()
        return gson.fromJson(value, listType)
    }
}