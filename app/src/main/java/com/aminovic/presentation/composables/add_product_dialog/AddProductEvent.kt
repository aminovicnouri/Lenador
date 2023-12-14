package com.aminovic.presentation.composables.add_product_dialog

sealed class AddProductEvent {
    data class UpdateName(val name: String) : AddProductEvent()
    data class UpdateBarcode(val barcode: String) : AddProductEvent()
    data class UpdatePrice(val price: String) : AddProductEvent()
    object ClearInputs : AddProductEvent()
    object Save : AddProductEvent()
}
