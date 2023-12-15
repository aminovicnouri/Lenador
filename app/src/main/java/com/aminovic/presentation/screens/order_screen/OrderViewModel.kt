package com.aminovic.presentation.screens.order_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
                _state.update { it.copy(products = products) }
            }
        }
    }

    fun onEvent(event: OrderEvent) {
        when (event) {
            is OrderEvent.ShowHideAddProductDialog -> {
                _state.update { it.copy(addProductDialogShown = event.show) }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}