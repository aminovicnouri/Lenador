package com.aminovic.lenador.data.mappers

import com.aminovic.lenador.data.local.entities.ProductEntity
import com.aminovic.lenador.domain.modal.Product

fun ProductEntity.toProduct() = Product(id = id, name = name, barcode = barcode, price = price)
fun Product.toProductEntity() = ProductEntity(name = name, barcode = barcode, price = price)