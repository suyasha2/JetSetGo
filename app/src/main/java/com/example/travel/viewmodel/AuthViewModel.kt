package com.example.travel.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.travel.model.AuthResult
import com.example.travel.model.UserModel
import com.example.travel.repo.AuthRepo
import com.example.travel.repo.AuthRepoImpl
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


class AuthViewModel(private val repo: AuthRepo = AuthRepoImpl()) : ViewModel() {
    val isLoading = mutableStateOf(false)
    val authResult = mutableStateOf<AuthResult?>(null)

    fun register(fullName: String, email: String, password: String, onAuthSuccess: () -> Unit) {
        isLoading.value = true
        authResult.value = null

        val userModel = UserModel(fullName = fullName, email = email)

        repo.register(userModel, password) { result ->
            isLoading.value = false
            authResult.value = result

            if (result.isSuccess) {
                onAuthSuccess()
            }
        }
    }

    fun login(email: String, password: String, onAuthSuccess: () -> Unit) {
        isLoading.value = true
        authResult.value = null

        repo.login(email, password) { result ->
            isLoading.value = false
            authResult.value = result

            if (result.isSuccess) {
                onAuthSuccess()
            }
        }
    }


    fun forgetPassword(email: String) {
        isLoading.value = true
        authResult.value = null

        repo.forgetPassword(email) { result ->
            isLoading.value = false
            authResult.value = result
        }
    }

    fun clearResult() {
        authResult.value = null
    }
}