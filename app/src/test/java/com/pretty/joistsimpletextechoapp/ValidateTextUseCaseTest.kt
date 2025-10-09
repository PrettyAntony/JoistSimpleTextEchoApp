package com.pretty.joistsimpletextechoapp

import com.pretty.joistsimpletextechoapp.data.repository.EchoRepository
import com.pretty.joistsimpletextechoapp.domain.model.EchoResult
import com.pretty.joistsimpletextechoapp.domain.usecase.ValidateTextUseCase
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Test

class ValidateTextUseCaseTest {

    private val successRepo = object : EchoRepository {
        override suspend fun validateAndEcho(input: String) = EchoResult.Success(input)
    }

    private val failRepo = object : EchoRepository {
        override suspend fun validateAndEcho(input: String) =
            EchoResult.Error("Server validation failed")
    }

    @Test
    fun `returns error for blank input`() = runTest {
        val useCase = ValidateTextUseCase(successRepo)
        val result = useCase("")
        assertTrue(result is EchoResult.Error)
        assertEquals("The input string cannot be empty.", (result as EchoResult.Error).message)
    }

    @Test
    fun `returns success when repository succeeds`() = runTest {
        val useCase = ValidateTextUseCase(successRepo)
        val result = useCase("Hello")
        assertTrue(result is EchoResult.Success)
        assertEquals("Hello", (result as EchoResult.Success).data)
    }

    @Test
    fun `returns error when repository fails`() = runTest {
        val useCase = ValidateTextUseCase(failRepo)
        val result = useCase("Hello")
        assertTrue(result is EchoResult.Error)
        assertEquals("Server validation failed", (result as EchoResult.Error).message)
    }
}