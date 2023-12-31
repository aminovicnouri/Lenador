package com.aminovic.presentation.screens.order_screen

import com.aminovic.lenador.domain.modal.Product

sealed class OrderEvent {
    data class ShowHideAddProductDialog(val show: Boolean) : OrderEvent()
    data class AddProductToOrder(val product: Product) : OrderEvent()
    data class DeleteProductFromOrder(val productId: Int) : OrderEvent()
    data class SaveOrder(val paid: Boolean = false) : OrderEvent()
}
