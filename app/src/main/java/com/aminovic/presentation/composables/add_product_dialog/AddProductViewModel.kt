package com.aminovic.presentation.composables.add_product_dialog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aminovic.lenador.R
import com.aminovic.lenador.domain.use_cases.InsertOrderUseCase
import com.aminovic.lenador.domain.utils.UiEvent
import com.aminovic.lenador.domain.utils.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddProductViewModel @Inject constructor(
    private val insertOrderUseCase: InsertOrderUseCase,
) : ViewModel() {

    // Mutable state flow for holding the UI state of the screen.
    private val _state = MutableStateFlow(AddProductState())

    // Expose the UI state as a read-only StateFlow to observers.
    val state: StateFlow<AddProductState>
        get() = _state


    // Channel for sending and receiving popup-related UI events.
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: AddProductEvent) {
        when (event) {
            is AddProductEvent.UpdateBarcode -> {
                viewModelScope.launch {
                    _state.update { it.copy(product = it.product.copy(barcode = event.barcode)) }
                }
            }

            is AddProductEvent.UpdateName -> {
                viewModelScope.launch {
                    _state.update { it.copy(product = it.product.copy(name = event.name)) }
                }
            }

            is AddProductEvent.UpdatePrice -> {
                viewModelScope.launch {
                    _state.update { it.copy(price = event.price) }
                }
            }

            AddProductEvent.ClearInputs -> {
                _state.update { AddProductState() }
            }

            AddProductEvent.Save -> {
                saveProduct()
            }
        }
    }

    private fun saveProduct() {
        viewModelScope.launch {
            state.value.product.name.let { input ->
                if (input.length < 2) {
                    _state.update { state -> state.copy(errorMessage = UiText.StringResource(R.string.invalid_name)) }
                } else {
                    _state.update { state -> state.copy(errorMessage = null) }
                }
            }

            if (state.value.errorMessage == null) {
                state.value.price.let { input ->
                    try {
                        _state.update { it.copy(product = it.product.copy(price = input.toDouble()), errorMessage = null) }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        _state.update { state -> state.copy(errorMessage = UiText.StringResource(R.string.invalid_price)) }
                    }
                }
            }
            if (state.value.errorMessage == null) {
                state.value.product.barcode.let {
                    if (it.length < 2) {
                        _state.update { state -> state.copy(errorMessage = UiText.StringResource(R.string.invalid_barcode)) }
                    }  else {
                        _state.update { state -> state.copy(errorMessage = null) }
                    }
                }
            }
            if (state.value.errorMessage == null) {
                insertOrderUseCase(state.value.product)
                _state.update { AddProductState() }
                _uiEvent.send(UiEvent.Success)
            }
        }
    }
}