package com.example.travel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.travel.repo.BookingRepoImpl
import com.example.travel.repo.DashboardRepositoryImpl
import com.example.travel.ui.theme.TravelTheme
import com.example.travel.view.*
import com.example.travel.viewmodel.BookingViewModel
import com.example.travel.viewmodel.DashboardViewModel
import com.example.travel.viewmodel.PackageViewModel

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
    val packageViewModel: PackageViewModel = viewModel()

    val dashViewModel: DashboardViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return DashboardViewModel(DashboardRepositoryImpl()) as T
            }
        }
    )

    NavHost(navController = navController, startDestination = "splash") {

        composable("splash") {
            SplashScreenBody {
                navController.navigate("login") { popUpTo("splash") { inclusive = true } }
            }
        }

        composable("login") {
            LoginScreen(
                onLoginSuccess = { navController.navigate("dashboard") { popUpTo("login") { inclusive = true } } },
                onNavigateToRegister = { navController.navigate("register") },
                onNavigateToForgotPassword = { navController.navigate("forgot_password") }
            )
        }

        composable("register") {
            RegisterScreen(
                onRegisterSuccess = { navController.navigate("login") { popUpTo("register") { inclusive = true } } },
                onNavigateToLogin = { navController.popBackStack() }
            )
        }

        composable("forgot_password") {
            ForgotPasswordScreen(onBackToLogin = { navController.popBackStack() })
        }

        composable("dashboard") {
            DashboardScreenUI(
                viewModel = dashViewModel,
                onNavigate = { route -> navController.navigate(route) }
            )
        }

        // --- PROFILE SECTION ---//
        composable("profile") {
            ProfileScreen(
                onBack = { navController.popBackStack() },
                onLogout = {
                    navController.navigate("login") {
                        popUpTo("dashboard") { inclusive = true }
                    }
                },
                onNavigateToSecurity = {
                    navController.navigate("security")
                }
            )

        }





        // --- SECURITY SECTION---//
        composable("security") {
            SecurityScreen(
                onBack = { navController.popBackStack() },
                onAccountDeleted = {
                    navController.navigate("login") {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }

        composable("notification") {
            NotificationScreen(onBack = { navController.popBackStack() })
        }

        composable("mountain") { EverestDetailScreen(onBack = { navController.popBackStack() }, onViewPackages = { packageViewModel.loadPackage("mountain"); navController.navigate("package_detail") }) }
        composable("pokhara") { PokharaDetailScreen(onBack = { navController.popBackStack() }, onViewPackages = { packageViewModel.loadPackage("pokhara"); navController.navigate("package_detail") }) }
        composable("chitwan") { ChitwanDetailScreen(onBack = { navController.popBackStack() }, onViewPackages = { packageViewModel.loadPackage("chitwan"); navController.navigate("package_detail") }) }
        composable("pashupati") { PashupatiDetailScreen(onBack = { navController.popBackStack() }, onViewPackages = { packageViewModel.loadPackage("pashupati"); navController.navigate("package_detail") }) }

        composable("package_detail") {
            val pkg = packageViewModel.selectedPackage.value
            if (pkg != null) {
                PackageDetailScreen(pkg = pkg, onBack = { navController.popBackStack() }, onBookingClick = { name, price -> navController.navigate("booking/$name/$price") })
            }
        }

        composable(
            route = "booking/{destinationName}/{price}",
            arguments = listOf(navArgument("destinationName") { type = NavType.StringType }, navArgument("price") { type = NavType.StringType })
        ) { backStackEntry ->
            val destinationName = backStackEntry.arguments?.getString("destinationName") ?: ""
            val price = backStackEntry.arguments?.getString("price") ?: ""
            val bookingViewModel: BookingViewModel = viewModel(factory = object : ViewModelProvider.Factory { override fun <T : ViewModel> create(modelClass: Class<T>): T = BookingViewModel(BookingRepoImpl()) as T })
            BookingScreen(destinationName = destinationName, price = price, onBackClick = { navController.popBackStack() }, viewModel = bookingViewModel)
        }
    }
}