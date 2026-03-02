package com.example.travel.viewmodel

import com.example.travel.model.AuthResult
import com.example.travel.repo.AuthRepo
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

class AuthViewModelTest {

    private lateinit var repo: AuthRepo
    private lateinit var viewModel: AuthViewModel

    @Before
    fun setup() {
        repo = mock()

        viewModel = AuthViewModel(repo)
    }

    @Test
    fun `register success updates state and calls onAuthSuccess`() {
        // Arrange
        val fullName = "John Doe"
        val email = "test@example.com"
        val password = "password123"
        val mockResult = AuthResult(isSuccess = true, message = "Success")

        var wasCallbackCalled = false


        doAnswer { invocation ->
            val callback = invocation.getArgument<(AuthResult) -> Unit>(2)
            callback(mockResult)
            null
        }.`when`(repo).register(any(), any(), any())

        // Act
        viewModel.register(fullName, email, password) {
            wasCallbackCalled = true
        }

        // Assert
        assertFalse(viewModel.isLoading.value)
        assertEquals(mockResult, viewModel.authResult.value)
        assertEquals(true, wasCallbackCalled)
    }

    @Test
    fun `login failure updates state and does not call onAuthSuccess`() {
        // Arrange
        val email = "wrong@example.com"
        val password = "wrongpassword"
        val mockResult = AuthResult(isSuccess = false, message = "Invalid Credentials")

        var wasCallbackCalled = false

        doAnswer { invocation ->
            val callback = invocation.getArgument<(AuthResult) -> Unit>(2)
            callback(mockResult)
            null
        }.`when`(repo).login(any(), any(), any())

        // Act
        viewModel.login(email, password) {
            wasCallbackCalled = true
        }

        // Assert
        assertFalse(viewModel.isLoading.value)
        assertEquals(mockResult, viewModel.authResult.value)
        assertEquals(false, wasCallbackCalled)
    }

    @Test
    fun `forgetPassword updates authResult correctly`() {
        // Arrange
        val email = "reset@example.com"
        val mockResult = AuthResult(isSuccess = true, message = "Reset link sent")

        doAnswer { invocation ->
            val callback = invocation.getArgument<(AuthResult) -> Unit>(1)
            callback(mockResult)
            null
        }.`when`(repo).forgetPassword(any(), any())

        // Act
        viewModel.forgetPassword(email)

        // Assert
        assertFalse(viewModel.isLoading.value)
        assertEquals(mockResult, viewModel.authResult.value)
    }

    @Test
    fun `clearResult sets authResult to null`() {
        // Arrange
        viewModel.authResult.value = AuthResult(true, "Some result")

        // Act
        viewModel.clearResult()

        // Assert
        assertNull(viewModel.authResult.value)
    }
}