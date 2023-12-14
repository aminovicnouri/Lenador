package com.aminovic.presentation.screens.order_screen

import androidx.lifecycle.ViewModel
import com.aminovic.lenador.domain.use_cases.InsertOrderUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor() : ViewModel() {

    // Mutable state flow for holding the UI state of the screen.
    private val _state = MutableStateFlow(OrderScreenState())

    // Expose the UI state as a read-only StateFlow to observers.
    val state: StateFlow<OrderScreenState>
        get() = _state

    fun onEvent(event: OrderEvent) {
        when (event) {
            is OrderEvent.ShowHideAddProductDialog -> {
                _state.update { it.copy(addProductDialogShown = event.show) }
            }
        }
    }
}