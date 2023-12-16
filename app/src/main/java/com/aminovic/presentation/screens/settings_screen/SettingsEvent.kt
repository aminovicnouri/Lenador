package com.aminovic.presentation.screens.settings_screen

sealed class SettingsEvent {
    data class ToggleTaxInclusive(val checked: Boolean): SettingsEvent()
}
