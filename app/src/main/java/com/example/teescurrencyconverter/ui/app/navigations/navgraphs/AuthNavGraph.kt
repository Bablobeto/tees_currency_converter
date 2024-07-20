package com.example.teescurrencyconverter.ui.app.navigations.navgraphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.teescurrencyconverter.FbViewModel
import com.example.teescurrencyconverter.ui.app.auth.RegistrationActivity
import com.example.teescurrencyconverter.ui.app.auth.SignInActivity
import com.example.teescurrencyconverter.ui.app.auth.SuccessActivity
import com.example.teescurrencyconverter.ui.app.navigations.Screen
import com.example.teescurrencyconverter.ui.app.navigations.Screen.Home.AUTHENTICATION_ROUTE

// Auth Nav Graph
fun NavGraphBuilder.authNavGraph(
    navController: NavHostController,
    vm: FbViewModel
){
    navigation(
        startDestination = Screen.SignIn.route,
        route = AUTHENTICATION_ROUTE
    ){
        composable(
            route = Screen.SignIn.route
        ){
            SignInActivity(navController, vm)
        }
        composable(
            route = Screen.Registration.route
        ){
            RegistrationActivity(navController, vm)
        }
        composable(
            route = Screen.Success.route
        ){
            SuccessActivity(navController, vm)
        }
    }
}
