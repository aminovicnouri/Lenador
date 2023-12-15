package com.aminovic.presentation.screens.order_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aminovic.lenador.domain.modal.OrderItem
import com.aminovic.lenador.domain.modal.Product
import com.aminovic.lenador.domain.use_cases.CalculateTaxUseCase
import com.aminovic.lenador.domain.use_cases.InsertProductUseCase
import com.aminovic.lenador.domain.use_cases.LoadProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val loadProductsUseCase: LoadProductsUseCase,
    private val insertProductUseCase: InsertProductUseCase,
    private val calculateTaxUseCase: CalculateTaxUseCase,
) : ViewModel() {

    // Mutable state flow for holding the UI state of the screen.
    private val _state = MutableStateFlow(OrderScreenState())

    // Expose the UI state as a read-only StateFlow to observers.
    val state: StateFlow<OrderScreenState>
        get() = _state

    private var job: Job? = null

    init {
        job?.cancel()
        job = viewModelScope.launch {
            loadProductsUseCase().collect { products ->
                if (products.isEmpty()) {
                    repeat((1..10).count()) {
                        insertProductUseCase(
                            Product(
                                name = "test $it",
                                price = 2.5 * it,
                                barcode = "barcode$it"
                            )
                        )
                    }
                }
                _state.update { it.copy(products = products) }
            }
        }
    }

    fun onEvent(event: OrderEvent) {
        when (event) {
            is OrderEvent.ShowHideAddProductDialog -> {
                _state.update { it.copy(addProductDialogShown = event.show) }
            }

            is OrderEvent.AddProductToOrder -> {
                addProductToOrder(event.product)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

    private fun addProductToOrder(product: Product) {
        viewModelScope.launch {
            if (state.value.order.items.find { it.product.id == product.id } != null) {
                val newQuantity = state.value.order.items.find { orderItem ->
                    orderItem.product.id == product.id
                }!!.quantity + 1
                val tax = calculateTaxUseCase.calculateTax(product.price, newQuantity)
                _state.update {
                    it.copy(
                        order = it.order.copy(
                            items = it.order.items.filter { orderItem ->
                                orderItem.product.id != product.id
                            } + OrderItem(
                                product,
                                newQuantity,
                                tax,
                                0.0,
                                calculateTaxUseCase.calculateTotalPrice(product.price, newQuantity)
                            )
                        )
                    )
                }
            } else {
                val tax = calculateTaxUseCase.calculateTax(product.price, 1)
                val total =   calculateTaxUseCase.calculateTotalPrice(product.price, 1)
                _state.update {
                    it.copy(
                        order = it.order.copy(
                            items = it.order.items + OrderItem(
                                product = product,
                                quantity = 1,
                                tax = tax,
                                discount = 0.0,
                                total = total
                            )
                        )
                    )
                }
            }
        }
    }
}