package com.pretty.joistsimpletextechoapp.presentation.model

data class UiState(
    val inputText: String = "",
    val echoedText: String? = null,
    val errorMessage: String? = null,
    val isLoading: Boolean = false
)