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
import com.example.pam.ui.menu.DetailPaymentScreen
import com.example.pam.ui.menu.MenuScreen
import com.example.pam.ui.menu.PaymentScreen
import com.example.pam.ui.menu.TampilScreenMenu

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
            AddScreen(navigateBack = {navController.popBackStack()},
                OnNextClick = {navController.navigate(Screen.Home.route)})
        }
        composable(route = DetailDestinationScreen.routeWithArgs,
            arguments = listOf(navArgument(DetailDestinationScreen.MakananId) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val makananId = backStackEntry.arguments?.getString(DetailDestinationScreen.MakananId)
            makananId?.let {
                DetailScreen(
                    navigateBack = { navController.popBackStack() },
                    navigateToEditItem = {
                        navController.navigate("${EditMakananScreen.route}/$makananId")
                        println("makananId: $makananId")
                    }
                )
            }
        }
        composable(DestinasiDataPel.route){
            DataPel(
                navigateBack = {navController.navigate("LoginPage")},
                navigateNext = {navController.navigate((TampilScreenMenu.route))}
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
        composable(
            TampilScreenMenu.route
        ) {
            MenuScreen(navigateToItemEntry = {
                navController.navigate(DestinasiEntry.route)
            },
                onDetailClick = { itemId ->
                    navController.navigate("${DetailDestinationScreen.route}/$itemId")
                    println("itemId: $itemId")
                },
                onPaymentClick = {
                    itemId -> navController.navigate("${DetailPaymentScreen.route}/$itemId")
                }
            )
        }
        composable(route = DetailPaymentScreen.routeWithArgs,
            arguments = listOf(navArgument(DetailPaymentScreen.MakananId) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val makananId = backStackEntry.arguments?.getString(DetailPaymentScreen.MakananId)
            makananId?.let {
                PaymentScreen(
                    navigateBack = { navController.popBackStack() },
                )
            }
        }
    }
}

