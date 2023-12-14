package com.aminovic.presentation.composables.add_product_dialog

import com.aminovic.lenador.domain.modal.Product
import com.aminovic.lenador.domain.utils.UiText

data class AddProductState(
    val product: Product = Product.newProduct(),
    val price: String = "0.0",
    val errorMessage: UiText? = null
)
