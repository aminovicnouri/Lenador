package com.aminovic.lenador.data.mappers

import com.aminovic.lenador.data.local.entities.OrderEntity
import com.aminovic.lenador.data.local.entities.OrderItemEntity
import com.aminovic.lenador.domain.modal.Order
import com.aminovic.lenador.domain.modal.OrderItem
import com.aminovic.lenador.domain.repository.PosRepository
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset

fun OrderItem.toOrderItemEntity() = OrderItemEntity(
    id = id,
    productId = product.id!!,
    quantity = quantity,
    tax = tax,
    total = total,
    discount = discount,
)

suspend fun OrderItemEntity.toOrderItem(repository: PosRepository) = OrderItem(
    id = id,
    product = repository.getProductById(productId)!!,
    quantity = quantity,
    tax = tax,
    total = total,
    discount = discount,
)

fun Order.toOrderEntity() = OrderEntity(
    id = id,
    items = items.map { it.toOrderItemEntity() },
    total = total,
    time = time.toEpochSecond(ZoneOffset.UTC),
    subTotal = subTotal,
    tax = total,
    discount = discount,
    quantity = quantity,
)

suspend fun OrderEntity.toOrder(repository: PosRepository) = Order(
    id = id,
    items = items.map { it.toOrderItem(repository) },
    total = total,
    time = LocalDateTime.ofInstant(
        Instant.ofEpochMilli(time),
        ZoneId.systemDefault()
    ),
    subTotal = subTotal,
    tax = total,
    discount = discount,
    quantity = quantity,
)