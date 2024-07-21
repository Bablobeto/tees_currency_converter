package com.example.teescurrencyconverter.ui.app.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.teescurrencyconverter.ui.app.components.Logo
import com.example.teescurrencyconverter.ui.theme.Purple80
import com.example.teescurrencyconverter.FbViewModel
import com.example.teescurrencyconverter.R
import com.example.teescurrencyconverter.ui.app.navigations.Screen
import com.example.teescurrencyconverter.ui.app.navigations.Screen.Home.AUTHENTICATION_ROUTE
import com.example.teescurrencyconverter.ui.theme.TeesCurrencyConverterTheme
import kotlinx.coroutines.delay

@Composable
fun SuccessActivity(
    navController: NavController,
    vm: FbViewModel
) {

    if (!vm.signedIn.value) {
        navController.navigate(Screen.Registration.route)
    }

    TeesCurrencyConverterTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primary)
        ) {
            Column {

                // Top Section
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Logo(100)
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.success_image),
                        contentDescription = stringResource(id = R.string.success_activity_image),
                        modifier = Modifier
                            .size(400.dp)
                            .clickable {
                                navController.navigate(Screen.Home.route)
                            }
                    )

                    Spacer(modifier = Modifier.height(25.dp))

                    Text(
                        text = "Welcome onboard!",
                        color = Purple80,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = TextUnit(38f, TextUnitType.Sp),
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    CountdownTimer(navController = navController)
                }
            }
        }
    }
}

@Composable
fun CountdownTimer(navController: NavController) {
    val count = remember { mutableIntStateOf(4) }

    LaunchedEffect(Unit) {
        while (count.intValue > 0) {
            delay(2000)
            count.intValue--
        }

        // Redirect to Home page screen
        navController.navigate(AUTHENTICATION_ROUTE)
    }

    Text(
        text = "Setting up account... Redirect in ${count.intValue}s"
    )
}

//@Composable
//@Preview(showBackground = true)
//fun SuccessActivityPreview() {
//    SuccessActivity(navController = rememberNavController())
//}
