package com.example.travel.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.travel.viewmodel.AuthViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.travel.R
import com.example.travel.model.AuthResult

@Composable
fun RegisterScreen(
    onRegisterSuccess: () -> Unit,
    onNavigateToLogin: () -> Unit,
    viewModel: AuthViewModel = viewModel()
) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    val PrimaryBlue = Color(0xFF4A90E2)
    val SecondaryBlue = Color(0xFF6AC5F7)
    val DarkTitleColor = Color(0xFF204161)
    val CardBgColor = Color.White.copy(alpha = 0.8f)
    val LinkColor = Color(0xFF4A90E2)
    val AccentColor = Color(0xFFF7E8E8)

    val isLoading by viewModel.isLoading
    val authResult by viewModel.authResult

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(authResult) {
        authResult?.let { result ->

            val snackbarColor = if (result.isSuccess) PrimaryBlue else Color(0xFFE53935)

            snackbarHostState.showSnackbar(
                message = result.message,
                actionLabel = "DISMISS",
                withDismissAction = true,
                duration = SnackbarDuration.Short
            )
            viewModel.clearResult()
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        listOf(PrimaryBlue, SecondaryBlue, Color(0xFFFFFFFF))
                    )
                )
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(40.dp))
                Spacer(modifier = Modifier.height(20.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = CardBgColor),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            "Create Account",
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold,
                            color = DarkTitleColor,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Image(
                            painter = painterResource(R.drawable.logo),
                            contentDescription = "Travel",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(AccentColor)
                        )

                        Spacer(modifier = Modifier.height(20.dp))
                        OutlinedTextField(value = fullName, onValueChange = { fullName = it }, label = { Text("Full Name") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(10.dp))
                        Spacer(modifier = Modifier.height(10.dp))
                        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(10.dp))
                        Spacer(modifier = Modifier.height(10.dp))
                        OutlinedTextField(value = password, onValueChange = { password = it }, label = { Text("Password") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(10.dp))
                        Spacer(modifier = Modifier.height(10.dp))
                        OutlinedTextField(value = confirmPassword, onValueChange = { confirmPassword = it }, label = { Text("Confirm Password") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(10.dp))

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = {
                                if (password.isNotEmpty() && password == confirmPassword) {
                                    viewModel.register(fullName, email, password) {
                                        onRegisterSuccess()
                                    }
                                } else if (password != confirmPassword) {
                                    viewModel.authResult.value = AuthResult(false, "Passwords do not match!")
                                } else {
                                    viewModel.authResult.value = AuthResult(false, "Please fill in all fields.")
                                }
                            },
                            enabled = !isLoading,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue, contentColor = Color.White)
                        ) {
                            if (isLoading) {
                                CircularProgressIndicator(color = Color.White)
                            } else {
                                Text("Create Account", fontSize = 16.sp, fontWeight = FontWeight.Medium)
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = buildAnnotatedString {
                                append("Already have an account? ")
                                pushStyle(SpanStyle(color = LinkColor, fontWeight = FontWeight.Bold))
                                append("Login")
                                pop()
                            },
                            modifier = Modifier.clickable { onNavigateToLogin() },
                            fontSize = 14.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    "© 2024 Travel Explorer",
                    fontSize = 12.sp,
                    color = Color.DarkGray.copy(alpha = 0.7f),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}