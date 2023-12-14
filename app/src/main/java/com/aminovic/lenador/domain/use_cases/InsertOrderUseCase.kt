package com.aminovic.lenador.domain.use_cases

import com.aminovic.lenador.domain.modal.Product
import com.aminovic.lenador.domain.repository.PosRepository

class InsertOrderUseCase(
    private val repository: PosRepository
) {
    suspend operator fun invoke(product: Product) {
        repository.insertProduct(product = product)
    }
}