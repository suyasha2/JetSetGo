package com.example.travel.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.travel.R
import com.example.travel.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onNavigateToRegister: () -> Unit,
    onNavigateToForgotPassword: () -> Unit,
    viewModel: AuthViewModel = viewModel()
) {
    val PrimaryBlue = Color(0xFF4A90E2)
    val SecondaryBlue = Color(0xFF6AC5F7)
    val DarkTitleColor = Color(0xFF204161)
    val CardBgColor = Color.White.copy(alpha = 0.8f)
    val LinkColor = Color(0xFF4A90E2)

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    val isLoading by viewModel.isLoading
    val authResult by viewModel.authResult
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(authResult) {
        authResult?.let { result ->
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
                    .padding(horizontal = 25.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(100.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = CardBgColor),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                    shape = RoundedCornerShape(25.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(25.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Welcome Back!", fontSize = 30.sp, fontWeight = FontWeight.Bold, color = DarkTitleColor)

                        Spacer(modifier = Modifier.height(25.dp))

                        Image(
                            painter = painterResource(R.drawable.logo),
                            contentDescription = "Travel Logo",
                            modifier = Modifier
                                .fillMaxWidth().height(150.dp).clip(RoundedCornerShape(15.dp))
                        )

                        Spacer(modifier = Modifier.height(25.dp))

                        OutlinedTextField(
                            value = email,
                            onValueChange = { email = it },
                            label = { Text("Email") },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(10.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = PrimaryBlue,
                                unfocusedBorderColor = Color.Black.copy(alpha = 0.5f)
                            )
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        OutlinedTextField(
                            value = password,
                            onValueChange = { password = it },
                            label = { Text("Password") },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(10.dp),
                            singleLine = true,
                            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = PrimaryBlue,
                                unfocusedBorderColor = Color.Black.copy(alpha = 0.5f)
                            ),
                            trailingIcon = {
                                val iconRes = if (passwordVisible)
                                    R.drawable.outline_visibility_24
                                else
                                    R.drawable.outline_visibility_off_24

                                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                    Icon(
                                        painter = painterResource(id = iconRes),
                                        contentDescription = "Toggle Password Visibility",
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                            }
                        )

                        Box(
                            modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            Text(
                                text = "Forgot Password?",
                                modifier = Modifier.clickable { onNavigateToForgotPassword() },
                                color = LinkColor, fontSize = 14.sp, fontWeight = FontWeight.SemiBold
                            )
                        }

                        Spacer(modifier = Modifier.height(25.dp))

                        Button(
                            onClick = { viewModel.login(email, password) { onLoginSuccess() } },
                            enabled = !isLoading,
                            modifier = Modifier.fillMaxWidth().height(55.dp),
                            shape = RoundedCornerShape(14.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlue, contentColor = Color.White)
                        ) {
                            if (isLoading) {
                                CircularProgressIndicator(modifier = Modifier.size(24.dp), color = Color.White)
                            } else {
                                Text("Sign In", fontSize = 17.sp, fontWeight = FontWeight.Medium)
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            text = buildAnnotatedString {
                                append("New here? ")
                                pushStyle(SpanStyle(color = LinkColor, fontWeight = FontWeight.Bold))
                                append("Create Account")
                                pop()
                            },
                            modifier = Modifier.clickable { onNavigateToRegister() },
                            fontSize = 15.sp
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}