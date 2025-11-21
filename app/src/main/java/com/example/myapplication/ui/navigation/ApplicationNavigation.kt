package com.example.myapplication.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.screen.MainScreen
import com.example.myapplication.ui.screen.SecondScreen
import kotlinx.serialization.Serializable

@Composable
fun ApplicationNavHost(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = ApplicationNavigationPath.Home,
    ) {
        composable<ApplicationNavigationPath.Home> {
            MainScreen(
                onButtonClick = { navController.navigate(route = ApplicationNavigationPath.Second) }
            )
        }

        composable<ApplicationNavigationPath.Second> {
            SecondScreen(navigateBack = { navController.popBackStack() })
        }
    }
}


object ApplicationNavigationPath {
    @Serializable
    data object Home


    @Serializable
    data object Second
}
