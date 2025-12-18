package com.example.travel.repo

import com.example.travel.model.AuthResult
import com.example.travel.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase

class AuthRepoImpl : AuthRepo {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val dbRef = FirebaseDatabase.getInstance().getReference("users")
    override fun register(
        userModel: UserModel,
        password: String,
        callback: (AuthResult) -> Unit
    ) {

        auth.createUserWithEmailAndPassword(userModel.email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val firebaseUser: FirebaseUser? = auth.currentUser
                    if (firebaseUser != null) {
                        val uid = firebaseUser.uid
                        val userToSave = userModel.copy(uid = uid)

                        dbRef.child(uid).setValue(userToSave.toMap())
                            .addOnCompleteListener { dbTask ->
                                if (dbTask.isSuccessful) {
                                    callback(AuthResult(true, "Registration successful!"))
                                } else {
                                    callback(
                                        AuthResult(
                                            false,
                                            "Registration success, but profile save failed: ${dbTask.exception?.message}"
                                        )
                                    )
                                }
                            }
                    }
                } else {
                    val errorMessage =
                        task.exception?.message ?: "An unknown registration error occurred."
                    callback(AuthResult(false, errorMessage))
                }
            }
    }

    override fun login(
        email: String,
        password: String,
        callback: (AuthResult) -> Unit
    ) {
        if (email.isEmpty() || password.isEmpty()) {
            callback(AuthResult(false, "Email and password cannot be empty."))
            return
        }

        if (email.isEmpty() || password.isEmpty()) {
            callback(AuthResult(false, "Please enter both email and password."))
            return
        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                callback(AuthResult(true, "Login successful!"))
            }
            .addOnFailureListener { exception ->
                val errorMessage = exception.message ?: "An unknown login error occurred."
                callback(AuthResult(false, errorMessage))
            }
    }

    override fun forgetPassword(email: String, callback: (AuthResult) -> Unit){
        if (email.isEmpty()) {
            callback(AuthResult(false, "Please enter your email address."))
            return
        }

        auth.sendPasswordResetEmail(email)
            .addOnSuccessListener {
                callback(AuthResult(true, "Password reset link sent to your email."))
            }
            .addOnFailureListener { exception ->
                val errorMessage = exception.message ?: "Failed to send reset email. Check your connection."
                callback(AuthResult(false, errorMessage))
            }
    }




    override fun isUserLoggedIn(): Boolean {
        return auth.currentUser != null
    }

    override fun logout() {
        auth.signOut()
    }

    private fun UserModel.toMap(): Map<String, Any?> {
        return mapOf(
            "uid" to this.uid,
            "fullName" to this.fullName,
            "email" to this.email
        )
    }
}



