package com.example.travel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.travel.ui.theme.TravelTheme
import com.example.travel.view.DashboardScreenUI
import com.example.travel.view.ForgotPasswordScreen
import com.example.travel.view.LoginScreen
import com.example.travel.view.RegisterScreen
import com.example.travel.view.SplashScreenBody

object Destinations {
    const val SPLASH = "splash"
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val FORGOT_PASSWORD = "forgot_password"
    const val DASHBOARD = "dashboard"

}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TravelTheme {
                TravelNavHost()
            }
        }
    }
}

@Composable
fun TravelNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Destinations.SPLASH
    ) {
        // --- 1. SPLASH SCREEN ---
        composable(Destinations.SPLASH) {
            SplashScreenBody(
                onTimeout = {
                    // Navigate to Login and remove splash from the back stack
                    navController.navigate(Destinations.LOGIN) {
                        popUpTo(Destinations.SPLASH) { inclusive = true }
                    }
                }
            )
        }

        // --- 2. LOGIN SCREEN ---
        composable(Destinations.LOGIN) {
            LoginScreen(
                onLoginSuccess = {
                    // Navigate to Dashboard and clear all previous auth screens
                    navController.navigate(Destinations.DASHBOARD) {
                        popUpTo(Destinations.LOGIN) { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate(Destinations.REGISTER)
                },
                onNavigateToForgotPassword = {
                    navController.navigate(Destinations.FORGOT_PASSWORD)
                },
                onGoogleSignIn = {
                    // TODO: Implement Google Sign-In logic here
                    println("Attempting Google Sign-In...")
                }
            )
        }

        // --- 3. REGISTER SCREEN ---
        composable(Destinations.REGISTER) {
            RegisterScreen(
                onRegisterSuccess = {
                    // Navigate to Dashboard and clear auth screens
                    navController.navigate(Destinations.DASHBOARD) {
                        popUpTo(Destinations.LOGIN) { inclusive = true }
                    }
                },
                onNavigateToLogin = {
                    // Pop back to the previous screen (LoginActivity)
                    navController.popBackStack()
                }
            )
        }

        // --- 4. FORGOT PASSWORD SCREEN ---
        composable(Destinations.FORGOT_PASSWORD) {
            ForgotPasswordScreen(
                onBackToLogin = {
                    // Pop back to the previous screen (LoginActivity)
                    navController.popBackStack()
                }
            )
        }

        // --- 5. DASHBOARD SCREEN ---
        composable(Destinations.DASHBOARD) {
            // You need to define this composable function elsewhere
            DashboardScreenUI(
                onNavigateToLogin = {
                    // When Logout is clicked, navigate back to Login and clear the dashboard
                    navController.navigate(Destinations.LOGIN) {
                        popUpTo(Destinations.DASHBOARD) { inclusive = true }
                    }
                }
            )
        }
    }
}
