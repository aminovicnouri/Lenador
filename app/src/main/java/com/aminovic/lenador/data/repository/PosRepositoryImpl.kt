package com.aminovic.lenador.data.repository

import com.aminovic.lenador.data.local.PosDao
import com.aminovic.lenador.data.mappers.toProduct
import com.aminovic.lenador.data.mappers.toProductEntity
import com.aminovic.lenador.domain.modal.Product
import com.aminovic.lenador.domain.repository.PosRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PosRepositoryImpl(
    private val dao: PosDao,
) : PosRepository {
    override suspend fun insertProduct(product: Product) {
        dao.insertProduct(product.toProductEntity())
    }

    override fun getProducts(): Flow<List<Product>> {
        return dao.getProducts().map { it.map { productEntity -> productEntity.toProduct() } }
    }

    override suspend fun deleteProduct(product: Product) {
        dao.deleteProduct(product.toProductEntity())
    }

    override suspend fun clearProducts() {
        dao.clearProducts()
    }

    override suspend fun getProductById(id: Int): Product? {
        return dao.getProductById(id)?.toProduct()
    }
}