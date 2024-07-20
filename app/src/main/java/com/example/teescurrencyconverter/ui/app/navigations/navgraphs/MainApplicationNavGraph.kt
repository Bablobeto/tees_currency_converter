package com.example.teescurrencyconverter.ui.app.navigations.navgraphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.teescurrencyconverter.FbViewModel
import com.example.teescurrencyconverter.room.viewmodel.RecordsViewModel
import com.example.teescurrencyconverter.ui.app.navigations.Screen
import com.example.teescurrencyconverter.ui.app.navigations.Screen.Home.ROOT_ROUTE
//import com.example.teescurrencyconverter.ui.app.pages.HomeScreen
//import com.example.teescurrencyconverter.ui.app.pages.ProfileScreen

fun NavGraphBuilder.mainApplicationNavGraph(
    navController: NavHostController,
    vm: FbViewModel,
    recordsViewModel: RecordsViewModel
){
    navigation(
        startDestination = Screen.Home.route,
        route = ROOT_ROUTE
    ) {
//        composable(
//            route = Screen.Home.route
//        ) {
//            HomeScreen(navController, vm, recordsViewModel)
//        }
//        composable(
//            route = Screen.Profile.route
//        ) {
//            ProfileScreen(navController, vm)
//        }
    }
}
