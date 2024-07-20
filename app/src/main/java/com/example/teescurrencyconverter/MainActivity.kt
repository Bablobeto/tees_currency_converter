package com.example.teescurrencyconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.teescurrencyconverter.room.viewmodel.RecordsViewModel
import com.example.teescurrencyconverter.ui.app.navigations.navgraphs.SetupAuthGraph
import com.example.teescurrencyconverter.ui.theme.TeesCurrencyConverterTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    // Implementation for navigation pages
    private lateinit var navController: NavHostController
    private lateinit var mItemViewModel: RecordsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen().setKeepOnScreenCondition() { false }

        mItemViewModel = ViewModelProvider(this)[RecordsViewModel::class.java]

        setContent {
            TeesCurrencyConverterTheme {
                navController = rememberNavController()

                SetupAuthGraph(navController)
            }
        }
    }
}
