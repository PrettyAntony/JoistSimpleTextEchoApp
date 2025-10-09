package com.pretty.joistsimpletextechoapp.data.repository

import com.pretty.joistsimpletextechoapp.domain.model.EchoResult

interface EchoRepository {
    suspend fun validateAndEcho(input: String): EchoResult<String>
}