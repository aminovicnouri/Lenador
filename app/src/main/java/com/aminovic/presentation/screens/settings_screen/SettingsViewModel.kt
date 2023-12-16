package com.aminovic.presentation.screens.settings_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aminovic.lenador.domain.repository.PosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val repository: PosRepository,
) : ViewModel() {

    // Mutable state flow for holding the UI state of the screen.
    private val _state = MutableStateFlow(SettingsState())

    // Expose the UI state as a read-only StateFlow to observers.
    val state: StateFlow<SettingsState>
        get() = _state


    init {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    taxInclusive = repository.getTaxInclusive()
                )
            }
        }
    }

    fun onEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.ToggleTaxInclusive -> {
                repository.setTaxInclusive(event.checked)
                _state.update {
                    it.copy(
                        taxInclusive = event.checked
                    )
                }
            }
        }
    }
}