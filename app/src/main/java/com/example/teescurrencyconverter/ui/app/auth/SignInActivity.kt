package com.example.teescurrencyconverter.ui.app.auth

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.teescurrencyconverter.ui.theme.TeesCurrencyConverterTheme
import com.example.teescurrencyconverter.FbViewModel
import com.example.teescurrencyconverter.R
import com.example.teescurrencyconverter.ui.app.components.Logo
import com.example.teescurrencyconverter.ui.app.components.SignInWithGoogle
import com.example.teescurrencyconverter.ui.app.navigations.Screen
import com.example.teescurrencyconverter.ui.app.navigations.Screen.Home.ROOT_ROUTE
import com.example.teescurrencyconverter.ui.theme.Purple80

@Composable
fun SignInActivity(
    navController: NavController,
    vm: FbViewModel
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Define state variables for validation errors
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var passwordVisible by remember { mutableStateOf(false) }

    // Define a state to hold the response of the onClick event
    val signedInResponse by vm.signedIn

    // Display the registration response
    signedInResponse.let { response ->
        if(response){
            navController.navigate(ROOT_ROUTE)
        }
    }

    TeesCurrencyConverterTheme {
        Surface{

            Column (
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                if (vm.inProgress.value && !vm.signedIn.value) {
                    CircularProgressIndicator()
                }
            }

            Column(
                modifier = Modifier.background(Color.White)
            ){

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

                // Second section
                Column (
                    modifier = Modifier
                        .weight(2f)
                        .fillMaxWidth()
                        .padding(20.dp, 0.dp, 20.dp, 30.dp)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.signin),
                        contentDescription = stringResource(id = R.string.success_activity_image),
                        modifier = Modifier.size(380.dp)
                    )

                    // Text
                    Text(
                        text = "Sign in",
                        fontWeight = FontWeight.Bold,
                        fontSize = TextUnit(28f, TextUnitType.Sp),
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    Text(
                        text = "We are guaranteed platform to help you convert your currency to another one easily",
                        textAlign = TextAlign.Center,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(40.dp))

                    // Forms
                    Column (
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        OutlinedTextField(
                            value = email,
                            label = { Text("Email",
                                color = Color.Black) },
                            placeholder = { Text("@live.tees.ac.uk") },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Email,
                                imeAction = ImeAction.Next
                            ),
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Filled.Email,
                                    contentDescription = "Email icon",
                                    tint = Purple80
                                )
                            },
                            onValueChange = {
                                newText -> email = newText
                                // Perform email validation
                                emailError = if (email.contains('@')) null else "Invalid email address"
                            },
                            modifier = Modifier.fillMaxWidth(),
                            isError = emailError != null
                        )

                        OutlinedTextField(
                            value = password,
                            label = { Text("Password",
                                color = Color.Black) },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password,
                                imeAction = ImeAction.Next
                            ),
                            trailingIcon = {
                                Icon(
                                    painter = painterResource(id =
                                    if (passwordVisible) R.drawable.closed_eyes
                                    else R.drawable.eye_close_up),
                                    contentDescription = "Toggle visibility",
                                    modifier = Modifier
                                        .clickable { passwordVisible = !passwordVisible }
                                        .padding(0.dp, 0.dp, 50.dp, 0.dp)
                                        .size(20.dp),
                                    tint = Purple80
                                )
                                Icon(
                                    imageVector = Icons.Filled.Lock,
                                    contentDescription = "Password icon",
                                    tint = Purple80,
                                    modifier = Modifier
                                        .padding(20.dp, 0.dp, 0.dp, 0.dp)
                                )
                            },
                            onValueChange = {
                                newText -> password = newText
                                // Perform password validation
                                passwordError = if (password.length >= 6) null else "Password must be at least 6 characters"
                            },
                            modifier = Modifier.fillMaxWidth(),
                            isError = passwordError != null,
                            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation()
                        )

                        ElevatedButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(0.dp, 20.dp, 0.dp, 20.dp),
                            shape = RoundedCornerShape(12.dp), // Use a rounded corner shape for a softer look
                            colors = ButtonDefaults.buttonColors(containerColor = Purple80),
                            onClick = {
                                if(emailError == null && passwordError == null)
                                {
                                    vm.signIn(email, password)
                                    Log.d("SignInActivity - Signing in", "Email: $email, Password: $password")
                                }
                            }
                        ) {
                            Text(
                                text = "Sign in",
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                fontSize = TextUnit(18f, TextUnitType.Sp),
                                modifier = Modifier.padding(15.dp)
                            )
                        }

                    }
                    // End of Forms

                    Text(
                        text = "You do not have an account? Sign up now",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable {
                            navController.navigate(Screen.Registration.route)
                        },
                        color = Color.Black,
                    )

                }

            }

        }
    }

}
