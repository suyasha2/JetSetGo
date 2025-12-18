package com.example.travel.repo

import com.example.travel.model.AuthResult
import com.example.travel.model.UserModel

interface AuthRepo {
    fun register(userModel: UserModel, password: String, callback: (AuthResult) -> Unit)
    fun login(email: String, password: String, callback: (AuthResult) -> Unit)
    fun forgetPassword(email: String, callback: (AuthResult) -> Unit)
    fun isUserLoggedIn(): Boolean
    fun logout()
}