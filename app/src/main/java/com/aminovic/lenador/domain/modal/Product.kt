package com.aminovic.lenador.domain.modal

data class Product(
    val id: Int? = null,
    val name: String,
    val barcode: String,
    val price: Double,
) {
    companion object {
        fun newProduct(): Product {
            return Product(
                name = "",
                price = 0.0,
                barcode = ""
            )
        }
    }
}
