package com.ebunnygroup.epara.navigation

import SettingsScreen
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.ebunnygroup.epara.R
import com.ebunnygroup.epara.ui.screen.auth.LoginScreen
import com.ebunnygroup.epara.ui.screen.auth.RegistrationScreen
import com.ebunnygroup.epara.ui.screen.home.BottomNavigationScreen
import com.ebunnygroup.epara.ui.screen.home.DashboardScreen
import com.ebunnygroup.epara.ui.screen.home.ProfileScreen
//import com.ebunnygroup.epara.ui.screen.home.SettingsScreen


sealed class Screens(
    val route: String,
    val icon: ImageVector,
    val label: String
) {
    object Authentication : Screens("authentication", Icons.Filled.Lock, "Authentication") {
        object Login : Screens("login", Icons.Filled.Person, "Login")
        object Registration : Screens("registration", Icons.Filled.PersonAdd, "Registration")
    }

    object Home : Screens("home", Icons.Filled.Home, "Home") {
        object Dashboard : Screens("dashboard", Icons.Filled.Dashboard, "Dashboard")
        object Profile : Screens("profile", Icons.Filled.AccountBox, "Profile")
        object Settings : Screens("settings", Icons.Filled.Settings, "Settings")
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
                    navController.navigate(Screens.Home.Dashboard.route) {
                        popUpTo(Screens.Authentication.route) {
                            inclusive = true
                        }
                    }
                },
                onRegisterClick = {
                    navController.navigate(Screens.Authentication.Registration.route)
                }
            )
        }
        composable(Screens.Authentication.Registration.route) {
            RegistrationScreen(
                onRegisterClick = {
                    navController.navigate(Screens.Home.Dashboard.route) {
                        popUpTo(Screens.Authentication.route) {
                            inclusive = true
                        }
                    }
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
            ProfileScreen(
                screenName = "Profile",
                previousScreen = navController.previousBackStackEntry?.destination?.route,
                onNextScreenClick = {
                navController.navigate(Screens.Home.Settings.route)
            },
                profilePhoto = R.drawable.ic_launcher_foreground,
                studentName = "Student Full Name",
                studentGroup = "Group",
                studentInstitute = "Institute"
            )
        }
        composable(Screens.Home.Settings.route) {
            SettingsScreen(
                screenName = "Settings",
                previousScreen = navController.previousBackStackEntry?.destination?.route,
                onNextScreenClick = {
                    navController.navigate(Screens.Home.Dashboard.route)
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationScreen(navController = navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screens.Authentication.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            authGraph(navController)
            homeGraph(navController)
        }
    }

}

@Composable
@Preview
fun AppPreview() {
//    AppNavigation()
}
