package com.example.travel.view

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecurityScreen(onBack: () -> Unit, onAccountDeleted: () -> Unit) {
    val user = FirebaseAuth.getInstance().currentUser
    val context = LocalContext.current
    val BgBlue = Color(0xFF4A90E2)

    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isSaving by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize().background(Brush.verticalGradient(listOf(BgBlue, Color.White)))) {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                TopAppBar(
                    title = { Text("Security", color = Color.White, fontWeight = FontWeight.Bold) },
                    navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, null, tint = Color.White) } },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
                )
            }
        ) { padding ->
            Column(modifier = Modifier.padding(padding).padding(24.dp).verticalScroll(rememberScrollState())) {
                Text("Change Password", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(20.dp))

                ProfileInputField("New Password", newPassword, isPassword = true) { newPassword = it }
                Spacer(modifier = Modifier.height(16.dp))
                ProfileInputField("Confirm New Password", confirmPassword, isPassword = true) { confirmPassword = it }

                Spacer(modifier = Modifier.height(40.dp))

                Button(
                    onClick = {
                        if (newPassword != confirmPassword) {
                            Toast.makeText(context, "Passwords mismatch!", Toast.LENGTH_SHORT).show()
                            return@Button
                        }
                        isSaving = true
                        user?.updatePassword(newPassword)?.addOnCompleteListener {
                            isSaving = false
                            if (it.isSuccessful) Toast.makeText(context, "Password Updated!", Toast.LENGTH_SHORT).show()
                            else Toast.makeText(context, "Error: Re-login required", Toast.LENGTH_LONG).show()
                        }
                    },
                    modifier = Modifier.fillMaxWidth().height(55.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = BgBlue),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    if (isSaving) CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
                    else Text("Update Password", fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(60.dp))
                TextButton(onClick = {
                    user?.delete()?.addOnCompleteListener { if (it.isSuccessful) onAccountDeleted() }
                }) {
                    Text("Delete Account Permanently", color = Color.Red, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

// Reuseable Input Field //
@Composable
fun ProfileInputField(label: String, value: String, isPassword: Boolean = false, onValueChange: (String) -> Unit) {
    Column {
        Text(label, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
        Spacer(modifier = Modifier.height(6.dp))
        TextField(
            value = value, onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            singleLine = true,
            visualTransformation = if (isPassword) PasswordVisualTransformation() else androidx.compose.ui.text.input.VisualTransformation.None,
            colors = TextFieldDefaults.colors(focusedContainerColor = Color.White, unfocusedContainerColor = Color.White, focusedIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent)
        )
    }
}