package com.example.travel.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.travel.R
import com.example.travel.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(onBackToLogin: () -> Unit, viewModel: AuthViewModel = viewModel()) {
    var email by remember { mutableStateOf("") }
    val isLoading by viewModel.isLoading
    val authResult by viewModel.authResult
    val snackbarHostState = remember { SnackbarHostState() }

    // Custom Colors
    val PrimaryBlue = Color(0xFF4A90E2)
    val SecondaryBlue = Color(0xFF6AC5F7)
    val DarkTitleColor = Color(0xFF204161)
    val CardBgColor = Color.White.copy(alpha = 0.8f)
    val LinkColor = Color(0xFF4A90E2)

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
                    .padding(horizontal = 20.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(60.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = CardBgColor),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(horizontal = 30.dp, vertical = 25.dp), // Padding milayeko
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            "Forgot Password",
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold,
                            color = DarkTitleColor,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            "Enter your email to receive a password reset link.",
                            fontSize = 14.sp,
                            color = Color(0xFF566573),
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = "Travel Logo",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
                                .clip(RoundedCornerShape(12.dp))
                        )

                        Spacer(modifier = Modifier.height(30.dp))

                        OutlinedTextField(
                            value = email,
                            onValueChange = { email = it },
                            placeholder = { Text("Email Address") },
                            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                            shape = RoundedCornerShape(10.dp),
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = PrimaryBlue,
                                unfocusedBorderColor = Color.Black.copy(alpha = 0.5f)
                            )
                        )

                        Spacer(modifier = Modifier.height(25.dp))

                        Button(
                            onClick = {
                                viewModel.forgetPassword(email)
                            },
                            enabled = !isLoading,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = PrimaryBlue,
                                contentColor = Color.White
                            )
                        ) {
                            if (isLoading) {
                                CircularProgressIndicator(
                                    color = Color.White,
                                    modifier = Modifier.size(24.dp)
                                )
                            } else {
                                Text("Send Reset Link", fontSize = 16.sp, fontWeight = FontWeight.Medium)
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            text = buildAnnotatedString {
                                append("Remembered your password? ")
                                pushStyle(SpanStyle(color = LinkColor, fontWeight = FontWeight.Bold))
                                append("Back to Login")
                                pop()
                            },
                            modifier = Modifier.clickable { onBackToLogin() },
                            fontSize = 14.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    "© 2026 Travel Explorer",
                    fontSize = 12.sp,
                    color = Color.DarkGray.copy(alpha = 0.7f),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}