package com.example.teescurrencyconverter.ui.app.landings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.teescurrencyconverter.FbViewModel
import com.example.teescurrencyconverter.R
import com.example.teescurrencyconverter.room.viewmodel.RecordsViewModel
import com.example.teescurrencyconverter.ui.app.components.Logo
import com.example.teescurrencyconverter.ui.app.navigations.Screen.Home.AUTHENTICATION_ROUTE
import com.example.teescurrencyconverter.ui.theme.Purple80
import com.example.teescurrencyconverter.ui.theme.TeesCurrencyConverterTheme

@Composable
fun LandingPageActivity(
    navController: NavController,
    vm : FbViewModel,
    recordsViewModel: RecordsViewModel
) {

    TeesCurrencyConverterTheme() {
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column (modifier = Modifier.background(Color.White)) {

                // Top Section
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Logo(90)
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.landing_page),
                        contentDescription = stringResource(id = R.string.success_activity_image),
                        modifier = Modifier.size(420.dp)
                    )

                    Column(
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        Text(
                            text = "Tees",
                            fontWeight = FontWeight.Bold,
                            fontSize = TextUnit(28f, TextUnitType.Sp),
                            modifier = Modifier
                                .padding(10.dp, 30.dp, 0.dp, 0.dp)
                        )

                        Text(
                            text = "Currency",
                            fontWeight = FontWeight.Bold,
                            fontSize = TextUnit(60f, TextUnitType.Sp),
                            modifier = Modifier
                                .padding(10.dp, 0.dp, 0.dp, 0.dp)
                        )

                        Text(
                            text = "Converter",
                            fontWeight = FontWeight.Bold,
                            fontSize = TextUnit(60f, TextUnitType.Sp),
                            modifier = Modifier
                                .padding(10.dp, 0.dp, 0.dp, 0.dp)
                        )
                    }

                    ElevatedButton(
                        onClick = {
                            navController.navigate(AUTHENTICATION_ROUTE) {
                                popUpTo(AUTHENTICATION_ROUTE) { inclusive = true }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(40.dp),
                        shape = RoundedCornerShape(12.dp), // Use a rounded corner shape for a softer look
                        colors = ButtonDefaults.buttonColors(containerColor = Purple80)
                    ) {
                        Text(
                            text = "Get Started",
                            fontWeight = FontWeight.Bold,
                            fontSize = TextUnit(18f, TextUnitType.Sp),
                            color = Color.White,
                            modifier = Modifier.padding(15.dp)
                        )
                    }

                }
            }
        }
    }
}