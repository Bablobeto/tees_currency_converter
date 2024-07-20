package com.example.teescurrencyconverter.ui.app.navigations.navgraphs

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.teescurrencyconverter.FbViewModel
import com.example.teescurrencyconverter.notification.NotificationMessage
import com.example.teescurrencyconverter.room.viewmodel.RecordsViewModel
import com.example.teescurrencyconverter.ui.app.navigations.Screen.Home.LANDINGS_ROUTE
import com.example.teescurrencyconverter.ui.app.navigations.Screen.Home.ROOT_ROUTE
import com.google.firebase.auth.FirebaseAuth

@Composable
fun SetupAuthGraph(
    navController: NavHostController,
    recordsViewModel: RecordsViewModel = viewModel()
) {
    // View Models
    val vm = hiltViewModel<FbViewModel>()
    val auth = FirebaseAuth.getInstance()
    NotificationMessage(vm = vm)

    var startWith = LANDINGS_ROUTE
    if (null != auth.currentUser) {
        startWith = ROOT_ROUTE
    }

    NavHost(
        navController = navController,
        startDestination = startWith,
    ) {

        // Landings graph
        landingsNavGraph(navController = navController, vm = vm, recordsViewModel)

        // Onboarding graph
        onboardingNavGraph(navController = navController, vm = vm)

        // Auth graph
        authNavGraph(navController = navController, vm = vm)

        // Main application graph
        mainApplicationNavGraph(navController = navController, vm = vm, recordsViewModel)

    }
}
