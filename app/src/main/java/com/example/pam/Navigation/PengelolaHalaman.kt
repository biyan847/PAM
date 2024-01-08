package com.example.pam.Navigation

import LoginPage
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.pam.ui.AnimatedSplashScreen
import com.example.pam.ui.HalamaHome
import com.example.pam.ui.add.AddScreen
import com.example.pam.ui.add.DestinasiEntry

@Composable
fun PengelolaHalaman(navController: NavHostController = rememberNavController()) {

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
        modifier = Modifier
    ) {
        composable(route = Screen.Splash.route) {
            AnimatedSplashScreen(navController = navController)
        }
        composable(route = Screen.Halaman.route) {
            HalamaHome(oneNextButtonClicked = {})
        }
        composable("LoginPage") {
            LoginPage(navController)
        }
        composable(route = Screen.Home.route) {
            Box(modifier = Modifier.fillMaxSize())
        }
        composable(DestinasiEntry.route) {
            AddScreen(navigateBack = {
                navController.popBackStack()
            })
        }
    }
}