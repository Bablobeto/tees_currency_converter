package com.example.teescurrencyconverter.ui.app.navigations

sealed class Screen (val route: String) {

    // Constants
    val LANDINGS_ROUTE = "landing"
    val ONBOARDING_ROUTE = "onboarding"
    val AUTHENTICATION_ROUTE = "authentication"
    val ROOT_ROUTE = "root"

    // Landings
    data object LandingPage : Screen(route = "LandingPageActivity")

    //Onboarding ... Remove scope
    data object StageOne : Screen(route = "StageOneActivity")
    data object StageTwo : Screen(route = "StageTwoActivity")

    // Authentication
    data object SignIn : Screen(route = "SignInActivity")
    data object Registration : Screen(route = "RegistrationActivity")
    data object Success : Screen(route = "SuccessActivity")

    // Main/Root
    data object Home : Screen(route = "HomeScreen")
    data object Profile : Screen(route = "ProfileScreen")

}