package com.pretty.joistsimpletextechoapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pretty.joistsimpletextechoapp.domain.model.EchoResult
import com.pretty.joistsimpletextechoapp.domain.usecase.ValidateTextUseCase
import com.pretty.joistsimpletextechoapp.presentation.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EchoViewModel @Inject constructor(
    private val validateAndEchoTextUseCase: ValidateTextUseCase
) : ViewModel() {

    //state variable
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    //observing onTextChange, updating state variable
    fun onTextChange(newText: String) {
        _uiState.update { it.copy(inputText = newText, errorMessage = null) }
    }


    fun validateInputTextAndEcho() {
        val input = _uiState.value.inputText


        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null, echoedText = null) }

            //Handle result success/error state
            when (val result = validateAndEchoTextUseCase(input)) {
                is EchoResult.Success -> _uiState.update {
                    it.copy(
                        echoedText = result.data,
                        inputText = "",
                        isLoading = false,
                        errorMessage = null
                    )
                }

                is EchoResult.Error -> _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = result.message,
                    )
                }
            }
        }
    }
}