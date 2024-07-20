package com.example.teescurrencyconverter.ui.app.navigations.navgraphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.teescurrencyconverter.FbViewModel
import com.example.teescurrencyconverter.room.viewmodel.RecordsViewModel
import com.example.teescurrencyconverter.ui.app.landings.LandingPageActivity
import com.example.teescurrencyconverter.ui.app.navigations.Screen
import com.example.teescurrencyconverter.ui.app.navigations.Screen.Home.LANDINGS_ROUTE

// Landing Nav Graph
fun NavGraphBuilder.landingsNavGraph(
    navController: NavHostController,
    vm: FbViewModel,
    recordsViewModel : RecordsViewModel
){
    navigation(
        startDestination = Screen.LandingPage.route,
        route = LANDINGS_ROUTE
    ) {
        composable(
            // Show landing page if no account exist
            route = Screen.LandingPage.route
        ) {
            LandingPageActivity(navController, vm, recordsViewModel)
        }
    }
}
