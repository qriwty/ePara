package com.ebunnygroup.epara.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.ebunnygroup.epara.ui.screen.auth.LoginScreen
import com.ebunnygroup.epara.ui.screen.auth.RegistrationScreen
import com.ebunnygroup.epara.ui.screen.home.DashboardScreen
import com.ebunnygroup.epara.ui.screen.home.ProfileScreen
import com.ebunnygroup.epara.ui.screen.home.SettingsScreen


sealed class Screens(val route: String) {
    object Authentication : Screens("authentication") {
        object Login : Screens("login")
        object Registration : Screens("registration")
    }

    object Home : Screens("home") {
        object Dashboard : Screens("dashboard")
        object Profile : Screens("profile")
        object Settings : Screens("settings")
    }
}

fun NavGraphBuilder.authGraph(navController: NavController) {
    navigation(
        startDestination = Screens.Authentication.Login.route,
        route = Screens.Authentication.route
    ) {
        composable(Screens.Authentication.Login.route) {
            LoginScreen(
                onLoginClick = {
                    navController.navigate(Screens.Home.Dashboard.route)
                },
                onRegisterClick = {
                    navController.navigate(Screens.Authentication.Registration.route)
                }
            )
        }
        composable(Screens.Authentication.Registration.route) {
            RegistrationScreen(
                onRegisterClick = {
                    navController.navigate(Screens.Home.Dashboard.route)
                },
                onLoginClick = {
                    navController.navigate(Screens.Authentication.Login.route)
                }
            )
        }
    }
}

fun NavGraphBuilder.homeGraph(navController: NavController) {
    navigation(
        startDestination = Screens.Home.Dashboard.route,
        route = Screens.Home.route
    ) {
        composable(Screens.Home.Dashboard.route) {
            DashboardScreen("Dashboard", navController.previousBackStackEntry?.destination?.route) {
                navController.navigate(Screens.Home.Profile.route)
            }
        }
        composable(Screens.Home.Profile.route) {
            ProfileScreen("Profile", navController.previousBackStackEntry?.destination?.route) {
                navController.navigate(Screens.Home.Settings.route)
            }
        }
        composable(Screens.Home.Settings.route) {
            SettingsScreen("Settings", navController.previousBackStackEntry?.destination?.route) {
                navController.navigate(Screens.Home.Dashboard.route)
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.Authentication.route
    ) {
        authGraph(navController)
        homeGraph(navController)
    }
}
