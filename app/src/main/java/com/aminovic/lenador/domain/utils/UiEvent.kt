package com.aminovic.lenador.domain.utils


//
// Created by Mohamed El Amine Nouri on 20/10/2022.
//

sealed class UiEvent {
    object Success : UiEvent()
    object NavigateUp : UiEvent()
}
