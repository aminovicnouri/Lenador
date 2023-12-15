package com.aminovic.lenador.domain.repository

import com.aminovic.lenador.domain.modal.Product
import kotlinx.coroutines.flow.Flow

interface PosRepository {
    suspend fun insertProduct(product: Product)

    fun getProducts(): Flow<List<Product>>

    suspend fun deleteProduct(product: Product)

    suspend fun clearProducts()

    suspend fun getProductById(id: Int): Product?

    fun setTaxInclusive(taxInclusive: Boolean)
    fun getTaxInclusive(): Boolean
}