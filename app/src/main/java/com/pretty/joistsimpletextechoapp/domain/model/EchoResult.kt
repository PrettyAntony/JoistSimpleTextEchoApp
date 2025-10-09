package com.pretty.joistsimpletextechoapp.domain.model

sealed class EchoResult<out T> {
    data class Success<out T>(val data: T) : EchoResult<T>()
    data class Error(val message: String) : EchoResult<Nothing>()
}