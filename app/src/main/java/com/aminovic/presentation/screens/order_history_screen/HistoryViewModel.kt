package com.aminovic.presentation.screens.order_history_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aminovic.lenador.domain.repository.PosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val repository: PosRepository,
) : ViewModel() {

    // Mutable state flow for holding the UI state of the screen.
    private val _state = MutableStateFlow(HistoryState())

    // Expose the UI state as a read-only StateFlow to observers.
    val state: StateFlow<HistoryState>
        get() = _state

    private var job: Job? = null

    init {
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
            repository.getOrders().collect { orders ->
                _state.update {
                    it.copy(
                        orders = orders,
                        total = orders.sumOf { it1 -> it1.total },
                        quantity = orders.sumOf { it1 -> it1.quantity },
                        items = orders.map { order ->
                            order.items
                        }.flatten().groupBy { item ->
                            item.product.id
                        }.size
                    )
                }
            }
        }
    }

    fun onEvent(event: HistoryEvent) {
        when (event) {
            else -> Unit
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}