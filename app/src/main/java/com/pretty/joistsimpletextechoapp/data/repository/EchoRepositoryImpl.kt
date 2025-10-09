package com.pretty.joistsimpletextechoapp.data.repository

import com.pretty.joistsimpletextechoapp.data.local.EchoDataSource
import com.pretty.joistsimpletextechoapp.domain.model.EchoResult

class EchoRepositoryImpl(
    private val dataSource: EchoDataSource
) : EchoRepository {

    override suspend fun validateAndEcho(input: String): EchoResult<String> {
        return if (dataSource.sendTextToServer(input)) {
            EchoResult.Success(input)
        } else {
            EchoResult.Error("The input string is invalid.")
        }
    }
}