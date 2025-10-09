package com.pretty.joistsimpletextechoapp

import com.pretty.joistsimpletextechoapp.data.repository.EchoRepository
import com.pretty.joistsimpletextechoapp.domain.model.EchoResult
import com.pretty.joistsimpletextechoapp.domain.usecase.ValidateTextUseCase
import com.pretty.joistsimpletextechoapp.presentation.viewmodel.EchoViewModel
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class EchoViewModelTest {

    private lateinit var viewModel: EchoViewModel

    private lateinit var useCaseSuccess: ValidateTextUseCase
    private lateinit var useCaseFail: ValidateTextUseCase

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    @Before
    fun setup() {

        Dispatchers.setMain(testDispatcher) // set main to test dispatcher

        useCaseSuccess = ValidateTextUseCase(
            object : EchoRepository {
                override suspend fun validateAndEcho(input: String) =
                    EchoResult.Success(input)
            }
        )

        useCaseFail = ValidateTextUseCase(
            object : EchoRepository {
                override suspend fun validateAndEcho(input: String) =
                    EchoResult.Error("The input string is invalid.")
            }
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main after test
    }

    @Test
    fun `blank input shows error and clears echoedText`() = runTest {
        viewModel = EchoViewModel(useCaseSuccess)
        viewModel.onTextChange("")
        viewModel.validateInputTextAndEcho()

        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertEquals("The input string cannot be empty.", state.errorMessage)
        assertNull(state.echoedText)
    }

    @Test
    fun `successful input updates echoedText`() = runTest {
        viewModel = EchoViewModel(useCaseSuccess)
        viewModel.onTextChange("Hello")
        viewModel.validateInputTextAndEcho()

        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertEquals("Hello", state.echoedText)
        assertNull(state.errorMessage)
        assertEquals("", state.inputText)
    }

    @Test
    fun `failed input clears echoedText and shows error`() = runTest {
        viewModel = EchoViewModel(useCaseFail)
        viewModel.onTextChange("Hello")
        viewModel.validateInputTextAndEcho()

        advanceUntilIdle()

        val state = viewModel.uiState.value
        assertNull(state.echoedText)
        assertEquals("The input string is invalid.", state.errorMessage)
    }
}