package com.aminovic.lenador.domain.repository

import com.aminovic.lenador.domain.modal.Order
import com.aminovic.lenador.domain.modal.Product
import kotlinx.coroutines.flow.Flow

interface PosRepository {
    suspend fun insertProduct(product: Product)

    fun getProducts(): Flow<List<Product>>

    suspend fun deleteProduct(product: Product)

    suspend fun clearProducts()

    suspend fun getProductById(id: Int): Product?

    suspend fun insertOrder(order: Order)
    fun getOrders(): Flow<List<Order>>
    suspend fun deleteOrder(order: Order)
    suspend fun clearOrders()
    suspend fun getOrderById(id: Int): Order?

    fun setTaxInclusive(taxInclusive: Boolean)

    fun getTaxInclusive(): Boolean
}