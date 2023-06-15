package com.ebunnygroup.epara.ui.screen.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ebunnygroup.epara.navigation.Screens
import com.ebunnygroup.epara.ui.common.BottomNavigationBarComponent


@Composable
fun BottomNavigationScreen(navController: NavController) {
    val items = listOf(
        Screens.Home.Dashboard,
        Screens.Home.Profile,
        Screens.Home.Settings
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    if (currentRoute in items.map { it.route }) {
        BottomAppBar(
            modifier = Modifier.fillMaxWidth()
        ) {
            items.forEach { item ->
                BottomNavigationBarComponent(
                    navController = navController,
                    item = item
                )
            }
        }
    }
}
