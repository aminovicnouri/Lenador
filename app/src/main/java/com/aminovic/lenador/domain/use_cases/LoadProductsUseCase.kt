package com.aminovic.lenador.domain.use_cases

import com.aminovic.lenador.domain.modal.Product
import com.aminovic.lenador.domain.repository.PosRepository
import kotlinx.coroutines.flow.Flow

class LoadProductsUseCase(
    private val repository: PosRepository,
) {
    operator fun invoke(): Flow<List<Product>> {
        return repository.getProducts()
    }
}