package com.example.teescurrencyconverter.ui.app.auth

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.teescurrencyconverter.ui.theme.TeesCurrencyConverterTheme
import com.example.teescurrencyconverter.FbViewModel
import com.example.teescurrencyconverter.R
import com.example.teescurrencyconverter.ui.app.components.Logo
import com.example.teescurrencyconverter.ui.app.components.SignInWithGoogle
import com.example.teescurrencyconverter.ui.app.navigations.Screen
import com.example.teescurrencyconverter.ui.app.navigations.Screen.Home.ONBOARDING_ROUTE
import com.example.teescurrencyconverter.ui.theme.Purple80

@SuppressLint("InvalidColorHexValue")
@Composable
fun RegistrationActivity(
    navController: NavController,
    vm: FbViewModel
) {
    val formScrollState = rememberScrollState()

    // Form variables
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var age by remember { mutableIntStateOf(18) }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Define state variables for validation errors
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var passwordVisible by remember { mutableStateOf(false) }

    // Define a state to hold the response of the onClick event
    val registrationResponse by vm.signedIn

    // Display the registration response
    registrationResponse.let { response ->
        // Handle the registration response here
        if(response){
            Log.d("TAG Response", "registered in")
            navController.navigate(ONBOARDING_ROUTE)
        }
    }

    TeesCurrencyConverterTheme{
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {

            // Loader
            Column (
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                if (vm.inProgress.value) {
                    CircularProgressIndicator()
                }
            }

            Column (modifier = Modifier.background(Color.White)){

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
                        .padding(20.dp, 15.dp, 20.dp, 0.dp)
                        .verticalScroll(formScrollState) ,
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    // Text
                    Text(
                        text = "Sign up",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    Text(
                        text = "Join the community of ambitious students building their dreams around healthy weight",
                        textAlign = TextAlign.Center,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(40.dp))

                    // Forms
                    Column (
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {

                        OutlinedTextField(
                            value = firstName,
                            label = { Text("First name",
                                color = Color.Black) },
                            placeholder = { Text("Ebenezer") },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Next
                            ),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Filled.AccountCircle,
                                    contentDescription = "First name icon",
                                    tint = Purple80
                                )
                            },
                            modifier = Modifier.fillMaxWidth(),
                            onValueChange = { newText -> firstName = newText }
                        )

                        OutlinedTextField(
                            value = lastName,
                            label = { Text("Last name",
                                color = Color.Black) },
                            placeholder = { Text("Babalola") },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Next
                            ),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Filled.AccountCircle,
                                    contentDescription = "Lastname icon",
                                    tint = Purple80
                                )
                            },
                            modifier = Modifier.fillMaxWidth(),
                            onValueChange = { newText -> lastName = newText }
                        )

                        OutlinedTextField(
                            value = age.toString(),
                            label = { Text("Age",
                                color = Color.Black) },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Next
                            ),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Filled.Face,
                                    contentDescription = "Age icon",
                                    tint = Purple80
                                )
                            },
                            modifier = Modifier.fillMaxWidth(),
                            onValueChange = { newText -> age = newText.toIntOrNull() ?: 0}
                        )

                        OutlinedTextField(
                            value = email,
                            label = { Text("Email",
                                color = Color.Black) },
                            placeholder = { Text("@live.tees.ac.uk") },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Email,
                                imeAction = ImeAction.Next
                            ),
                            leadingIcon = {
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
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Filled.Lock,
                                    contentDescription = "Password icon",
                                    tint = Purple80
                                )
                            },
                            trailingIcon = {
                                Icon(
                                    painter = painterResource(id =
                                    if (passwordVisible) R.drawable.closed_eyes
                                    else R.drawable.eye_close_up),
                                    contentDescription = "Toggle visibility",
                                    modifier = Modifier
                                        .clickable { passwordVisible = !passwordVisible }
                                        .size(20.dp),
                                    tint = Color.Blue
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
                            shape = RectangleShape,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(0.dp, 15.dp),
                            colors = ButtonDefaults.buttonColors(Purple80),
                            onClick = {
                                if(emailError == null && passwordError == null)
                                {
                                    vm.register(email, password, firstName, lastName, age)
                                }
                            }
                        ) {
                            Text(
                                text = "Register",
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }

                    }
                    // End of Forms

                    Text(
                        text = "Already have an account? Sign in now",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable {
                            navController.navigate(route = Screen.SignIn.route)
                        },
                        color = Color.Black
                    )

                    // Sign up with Google
                    SignInWithGoogle(navController, vm)

                }

            }
        }

    }

    LaunchedEffect(Unit) {
        formScrollState.scrollTo(0)
    }
}

@Composable
@SuppressLint("ServiceCast")
fun isInternetAvailable(context: Context = LocalContext.current): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val network = connectivityManager.activeNetwork ?: return false
    val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

    return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
}
