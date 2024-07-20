package com.example.teescurrencyconverter.ui.app.navigations.navgraphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.teescurrencyconverter.FbViewModel
import com.example.teescurrencyconverter.ui.app.auth.onboarding.StageOneActivity
import com.example.teescurrencyconverter.ui.app.auth.onboarding.StageTwoActivity
import com.example.teescurrencyconverter.ui.app.navigations.Screen
import com.example.teescurrencyconverter.ui.app.navigations.Screen.Home.ONBOARDING_ROUTE


// Onboarding Nav Graph
fun NavGraphBuilder.onboardingNavGraph(
    navController: NavHostController,
    vm: FbViewModel
){
    navigation(
        startDestination = Screen.StageOne.route,
        route = ONBOARDING_ROUTE
    ) {
        composable(
            route = Screen.StageOne.route
        ) {
            StageOneActivity(navController, vm)
        }
        composable(
            route = Screen.StageTwo.route
        ) {
            StageTwoActivity(navController, vm)
        }
    }
}
