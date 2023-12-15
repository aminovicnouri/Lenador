package com.aminovic.lenador.domain.use_cases

import com.aminovic.lenador.domain.modal.Product
import com.aminovic.lenador.domain.repository.PosRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class InsertProductUseCase(
    private val repository: PosRepository,
) {
    suspend operator fun invoke(product: Product) {
        withContext(Dispatchers.IO) {
            repository.insertProduct(product = product)
        }
    }
}