package com.pretty.joistsimpletextechoapp.data.local

import kotlinx.coroutines.delay

class EchoDataSource {
    suspend fun sendTextToServer(input: String): Boolean {
        delay(1000)
        return !input.any { it.isDigit() } //fails if input text has digits
    }
}