package com.example.pam.Navigation

import LoginPage
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pam.ui.AnimatedSplashScreen
import com.example.pam.ui.DataPelanggan.DataPel
import com.example.pam.ui.DataPelanggan.DestinasiDataPel
import com.example.pam.ui.add.AddScreen
import com.example.pam.ui.add.DestinasiEntry
import com.example.pam.ui.detail.DetailDestinationScreen
import com.example.pam.ui.detail.DetailScreen
import com.example.pam.ui.edit.EditMakanan
import com.example.pam.ui.edit.EditMakananScreen

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
        composable("LoginPage") {
            LoginPage(navController)
        }
        composable(route = Screen.Home.route) {
            Box(modifier = Modifier.fillMaxSize())
        }
        composable(DestinasiEntry.route) {
            AddScreen(navigateBack = {
                navController.navigate(Screen.Home.route)})
        }
        composable(DetailDestinationScreen.route){
            DetailScreen(navigateToEditItem = {}, navigateBack = { /*TODO*/ })
        }
        composable(DestinasiDataPel.route){
            DataPel(
                navigateBack = {navController.navigate("LoginPage")},
                navigateNext = {navController.navigate((DetailDestinationScreen.route))}
            )
        }
        composable(
            route = EditMakananScreen.routeWithArgs,
            arguments = listOf(navArgument(EditMakananScreen.makananId){
                type = NavType.StringType
            })
        ){backStackEntry ->
            val makananId = backStackEntry.arguments?.getString(EditMakananScreen.makananId)
            makananId?.let {
                EditMakanan(
                    navigateBack = { navController.popBackStack()},
                    onNavigateUp = {navController.navigateUp() })
            }
        }
    }
}

