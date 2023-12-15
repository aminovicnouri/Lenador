package com.aminovic.lenador.domain.use_cases

import com.aminovic.lenador.domain.Constants.TAX_RATE
import com.aminovic.lenador.domain.repository.PosRepository

class CalculateTaxUseCase(
    private val repository: PosRepository,
) {
    fun calculateTax(
        basePrice: Double,
        quantity: Int,
        taxRate: Double = TAX_RATE,
    ): Double {
        return if (repository.getTaxInclusive()) {
            ((basePrice * quantity) / (1 + taxRate) * taxRate)
        } else {
            (basePrice * quantity) * taxRate
        }
    }

    fun calculateTotalPrice(
        basePrice: Double,
        quantity: Int,
        taxRate: Double = TAX_RATE,
    ): Double {
        return if (repository.getTaxInclusive()) {
            basePrice * quantity
        } else {
            (basePrice * quantity) + calculateTax(basePrice, quantity, taxRate)
        }
    }
}