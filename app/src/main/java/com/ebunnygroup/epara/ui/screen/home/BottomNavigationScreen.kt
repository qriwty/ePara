package com.ebunnygroup.epara.ui.screen.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                items.forEach { item ->
                    BottomNavigationBarComponent(
                        onItemClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        item = item
                    )
                }
            }

        }
    }
}

//@Composable
//fun BottomNavigationScreenTest(navController: NavController = rememberNavController()) {
//    val items = listOf(
//        Screens.Home.Dashboard,
//        Screens.Home.Profile,
//        Screens.Home.Settings
//    )
//
//    BottomAppBar(
//        modifier = Modifier.fillMaxWidth()
//    ) {
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceEvenly
//        ) {
//            items.forEach { item ->
//                BottomNavigationBarComponent(
//                    onItemClick = {
//                        navController.navigate(item.route) {
//                            popUpTo(navController.graph.findStartDestination().id) {
//                                saveState = true
//                            }
//                            launchSingleTop = true
//                            restoreState = true
//                        }
//                    },
//                    item = item
//                )
//            }
//        }
//
//    }
//}
//
//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//@Preview
//fun previewNav() {
//    val navController = rememberNavController()
//    Scaffold(
//        bottomBar = {
//            BottomNavigationScreenTest(navController = navController)
//        }
//    ) {}
//}

