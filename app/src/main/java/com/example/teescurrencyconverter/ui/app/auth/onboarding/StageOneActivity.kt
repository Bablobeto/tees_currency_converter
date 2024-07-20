package com.example.teescurrencyconverter.ui.app.auth.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.teescurrencyconverter.ui.theme.TeesCurrencyConverterTheme
import com.example.teescurrencyconverter.FbViewModel
import com.example.teescurrencyconverter.R
import com.example.teescurrencyconverter.ui.app.navigations.Screen
import com.example.teescurrencyconverter.ui.app.navigations.Screen.Home.AUTHENTICATION_ROUTE
import com.example.teescurrencyconverter.ui.theme.Purple80
import com.google.firebase.auth.FirebaseAuth

@Composable
fun StageOneActivity(
    navController: NavController,
    vm: FbViewModel
){
    val auth = FirebaseAuth.getInstance()
//    // Room
//    val context = LocalContext.current
//    val mItemViewModel: RecordsViewModel = remember(context) {
//        ViewModelProvider(context as ComponentActivity)[RecordsViewModel::class.java]
//    }

    if (null == auth.currentUser && !vm.signedIn.value) {
        navController.navigate(AUTHENTICATION_ROUTE)
    }

    TeesCurrencyConverterTheme {
        Surface {

            Column {
                
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {

                        Image(
                            painter = painterResource(id = R.drawable.sun),
                            contentDescription = null,
                            modifier = Modifier
                                .height(200.dp)
                                .fillMaxWidth(),
                            alignment = Alignment.TopEnd
                        )

                        Column(
                            modifier = Modifier
                                .fillMaxWidth().fillMaxHeight()
                                .padding(40.dp),
                            verticalArrangement = Arrangement.Bottom
                        ) {

                            Column(
                                modifier = Modifier.weight(2f),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.weight),
                                    contentDescription = null,
                                    modifier = Modifier.fillMaxSize(),
                                    alignment = Alignment.BottomEnd
                                )
                            }

                            Column(
                                modifier = Modifier
                                    .padding(0.dp, 40.dp, 0.dp, 40.dp),
                                verticalArrangement = Arrangement.Bottom,
                            ) {
                                Text(
                                    text = "Tell us",
                                    fontWeight = FontWeight.Bold,
                                    style = androidx.compose.ui.text.TextStyle(
                                        fontSize = 30.sp
                                    ),
                                )

                                Row {
                                    Text(
                                        text = "About your",
                                        fontWeight = FontWeight.Bold,
                                        style = androidx.compose.ui.text.TextStyle(
                                            fontSize = 32.sp
                                        )
                                    )
                                    Text(
                                        text = " weight",
                                        color = Purple80,
                                        fontWeight = FontWeight.ExtraBold,
                                        style = androidx.compose.ui.text.TextStyle(
                                            fontSize = 32.sp
                                        )
                                    )
                                }

                            }

                            Column(
                                modifier = Modifier
                                    .padding(0.dp, 5.dp, 0.dp, 40.dp)
                            ) {

                                ElevatedButton(
                                    shape = RectangleShape,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(0.dp, 12.dp, 0.dp, 0.dp),
                                    colors = ButtonDefaults.buttonColors(Purple80),
                                    onClick = {
                                        navController.popBackStack()
                                        navController.navigate(Screen.StageTwo.route)
                                    }
                                ) {
                                    Text(
                                        text = "Continue",
                                        fontWeight = FontWeight.Bold
                                    )
                                }

                            }

                        }
                    }

            }

        }
    }
}

//@Composable
//@Preview(showBackground = true)
//fun StageOnePreview(){
//    StageOneActivity(rememberNavController())
//}