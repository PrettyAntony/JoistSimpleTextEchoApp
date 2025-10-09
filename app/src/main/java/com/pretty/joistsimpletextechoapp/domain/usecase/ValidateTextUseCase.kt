package com.pretty.joistsimpletextechoapp.domain.usecase

import com.pretty.joistsimpletextechoapp.data.repository.EchoRepository
import com.pretty.joistsimpletextechoapp.domain.model.EchoResult

class ValidateTextUseCase(private val repository: EchoRepository) {

    suspend operator fun invoke(input: String): EchoResult<String> {
        if (input.isBlank()) {
            return EchoResult.Error("The input string cannot be empty.")
        }
        return repository.validateAndEcho(input)
    }
}