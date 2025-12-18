package com.example.travel.model

data class AuthResult(
    val isSuccess: Boolean,
    val message: String
)

data class UserModel(
    val uid: String = "",
    val fullName: String = "",
    val email: String = ""
)


