package com.example.teescurrencyconverter.ui.app.pages

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.teescurrencyconverter.FbViewModel
import com.example.teescurrencyconverter.R
import com.example.teescurrencyconverter.retrofit2.model.RetrofitViewModel
import com.example.teescurrencyconverter.room.entity.History
import com.example.teescurrencyconverter.room.viewmodel.RecordsViewModel
import com.example.teescurrencyconverter.ui.app.navigations.Screen
import com.example.teescurrencyconverter.ui.app.navigations.Screen.Home.AUTHENTICATION_ROUTE
import com.example.teescurrencyconverter.ui.theme.Purple80
import com.example.teescurrencyconverter.ui.theme.TeesCurrencyConverterTheme
import com.google.common.math.IntMath.pow
import com.google.firebase.auth.FirebaseAuth
import java.util.Locale
import kotlin.random.Random

@SuppressLint("CheckResult")
@Composable
fun HomeScreen(
    navController : NavController,
    vm : FbViewModel,
    recordsViewModel: RecordsViewModel
){
    var selectedSourceCurrency by remember { mutableStateOf("USD") }
    var selectedTargetCurrency by remember { mutableStateOf("GBP") }
    var amount by remember { mutableStateOf("") }

    vm.getCustomData()

    // State variable to track if the dialog is open
    var showDialog by remember { mutableStateOf(false) }
    val auth = FirebaseAuth.getInstance()
    val uid = auth.currentUser?.uid ?: ""

    Log.d("values photo", auth.currentUser?.photoUrl.toString())
    Log.d("values", vm.isLogout.value.toString())
    Log.d("values", auth.currentUser.toString())
    Log.d("values", auth.currentUser?.displayName.toString())

    if (null == auth.currentUser) {
        Log.d("TAG", "Logging out")
        navController.navigate(AUTHENTICATION_ROUTE)
    }
//    if (null == auth.currentUser || vm.isLogout.value) {
//        Log.d("TAG", "Logging out")
//        navController.navigate(AUTHENTICATION_ROUTE)
//    }
    TeesCurrencyConverterTheme {
        Surface {

            Column (
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                if (vm.inProgress.value) {
                    CircularProgressIndicator()
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                verticalArrangement = Arrangement.Top
            ) {
                //Top
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1.1f)
                ) {
                    Box {
                        Image(
                            painter = painterResource(id = R.drawable.success_image),
                            contentDescription = null,
                            modifier = Modifier
                                .height(250.dp)
                                .fillMaxWidth(),
                            alignment = Alignment.TopEnd
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Column(
                                    modifier = Modifier.weight(2f)
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.logo),
                                        contentDescription = stringResource(id = R.string.google_logo),
                                        modifier = Modifier.size(90.dp)
                                    )
                                }

                                Column(
                                    horizontalAlignment = Alignment.End,
                                    modifier = Modifier
                                        .padding(10.dp)
                                        .clickable {
                                            navController.navigate(Screen.SignIn.route)
                                            vm.signOut()
                                        }
                                ) {

                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                                        contentDescription = "Log out user account",
                                        tint = MaterialTheme.colorScheme.primary
                                    )

                                    Text(
                                        text = "Log out",
                                        color = MaterialTheme.colorScheme.primary,
                                        fontWeight = FontWeight.ExtraBold,
                                        fontSize = 25.sp
                                    )
                                }

                            }

                            Row(
                                modifier = Modifier.fillMaxSize(),
                                verticalAlignment = Alignment.Bottom,
                                horizontalArrangement = Arrangement.Start
                            ) {

                                Column(
                                    modifier = Modifier.weight(4f)
                                ) {
                                    Text(
                                        text = "Hello,",
                                        color = Color.Black,
                                        fontWeight = FontWeight.Bold,
                                        style = TextStyle(fontSize = 28.sp)
                                    )

                                    Text(
                                        text = if(null != auth.currentUser)
                                            if(null != vm.customData.name) vm.customData.name.uppercase(
                                                Locale.ROOT
                                            )
                                            else auth.currentUser!!.displayName.toString()
                                                .uppercase(
                                                    Locale.ROOT
                                                )
                                        else "Buddy",
                                        color = Color.Black,
                                        fontWeight = FontWeight.Bold,
                                        style = TextStyle(fontSize = 34.sp)
                                    )
                                }

                                Column(
                                    horizontalAlignment = Alignment.End
                                ) {
                                    if(null != auth.currentUser?.photoUrl)
                                    {
                                        Image(
                                            painter = rememberImagePainter(auth.currentUser?.photoUrl.toString()),
                                            contentDescription = "Go to profile",
                                            modifier = Modifier
                                                .clip(CircleShape)
                                                .size(70.dp)
                                                .background(Color.White)
                                                .clickable {
                                                    navController.navigate(Screen.Profile.route)
                                                }
                                        )
                                    }
                                    else{
                                        Image(
                                            painter = painterResource(id = R.drawable.baseline_person_24),
                                            contentDescription = "Go to profile",
                                            modifier = Modifier
                                                .clip(CircleShape)
                                                .size(70.dp)
                                                .background(MaterialTheme.colorScheme.primary)
                                                .clickable {
                                                    navController.navigate(Screen.Profile.route)
                                                }
                                        )
                                    }
                                }

                            }
                        }
                    }
                }






                //Bottom
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(3f)
                        .verticalScroll(rememberScrollState())
                ) {

                    Column (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 0.dp, 40.dp),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.converter),
                            contentDescription = stringResource(id = R.string.success_activity_image),
                            modifier = Modifier.size(200.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(15.dp)
                        ) {
                            CurrencyDropdown(
                                "Source currency",
                                selectedCurrency = selectedSourceCurrency,
                                onCurrencySelected = { newCurrency ->
                                    selectedSourceCurrency = newCurrency
                                }
                            )
                        }


                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(15.dp)
                        ) {
                            CurrencyDropdown(
                                "Target currency",
                                selectedCurrency = selectedTargetCurrency,
                                onCurrencySelected = { newCurrency ->
                                    selectedTargetCurrency = newCurrency
                                }
                            )
                        }
                    }


                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(15.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            OutlinedTextField(
                                value = amount,
                                label = { Text("Amount in $selectedSourceCurrency") },
                                placeholder = { Text("1") },
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number,
                                    imeAction = ImeAction.Done
                                ),
                                trailingIcon = {
                                    Icon(
                                        imageVector = Icons.Filled.Create,
                                        contentDescription = "Create icon",
                                        tint = Purple80
                                    )
                                },
                                onValueChange = { newText ->
                                    amount = newText
                                },
                                keyboardActions = KeyboardActions(
                                    onDone = {
                                        val amountToSave = amount.toDoubleOrNull()
                                        Log.d("TAG", amountToSave.toString())

                                        // Now you can use 'amountToSave' to trigger API calls or other actions
                                        amountToSave?.let { amountDouble ->
                                            val conversionRate = 1.1234// Consider fetching this dynamically
                                            val convertedAmount = amountDouble * conversionRate

                                            val historyRecord = History(
                                                Random.nextInt(),
                                                auth.currentUser?.uid.toString(),
                                                selectedSourceCurrency,
                                                selectedTargetCurrency,
                                                conversionRate,
                                                amountDouble,
                                                convertedAmount
                                            )

                                            recordsViewModel.addRecord(historyRecord)
                                        }
                                    }
                                ),
                                modifier = Modifier.fillMaxWidth()
                            )

                            Spacer(modifier = Modifier.height(5.dp))

                            Image(
                                painter = painterResource(id = R.drawable.arrow_down),
                                contentDescription = stringResource(id = R.string.success_activity_image),
                                modifier = Modifier
                                    .size(70.dp)
                            )

                            // Output
                            Text(
                                text = "1000000",
                                fontWeight = FontWeight.Bold,
                                fontSize = 40.sp,
                                color = Color.Black
                            )
                            Text(
                                text = selectedTargetCurrency,
                                fontWeight = FontWeight.Bold,
                                fontSize = 31.sp,
                                color = Color.Black
                            )

                            Spacer(modifier = Modifier.height(15.dp))

                            Button(
                                onClick = { showDialog = true }
                            ) {
                                Text(
                                    text = "See conversion history",
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    fontSize = TextUnit(18f, TextUnitType.Sp),
                                    modifier = Modifier.padding(15.dp)
                                )
                            }

                        }


                        // Modal
                        if(showDialog){
                            AlertDialog(
                                onDismissRequest = { showDialog = false },
                                title = {
                                    Text(text = "Conversion history", fontWeight = FontWeight.Bold)
                                },
                                shape = RoundedCornerShape(12.dp), // Use a rounded corner shape for a softer look
                                text = {
                                    ThreeByThreeTable(recordsViewModel, uid)
                                },
                                confirmButton = {},
                                dismissButton = {
                                    Button(
                                        onClick = { showDialog = false },
                                    ) {
                                        Text(text = "Dismiss")
                                    }
                                },
                                properties = androidx.compose.ui.window.DialogProperties(
                                    dismissOnBackPress = false,
                                    dismissOnClickOutside = false,
                                    usePlatformDefaultWidth = true,
                                    decorFitsSystemWindows = false,
                                ),
                                containerColor = Color.White,
                            )
                        }

                    }







                }







            }
        }
    }
}

@Composable
fun CurrencyDropdown(
    label: String,
    selectedCurrency: String,
    onCurrencySelected: (String) -> Unit,
    currencies: List<String> = listOf("NGN", "USD", "EUR", "JPY", "GBP", "AUD", "CAD", "CHF", "CNY", "SEK", "NZD"), // Replace with your actual currency list
) {
    var expanded by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = selectedCurrency,
        onValueChange = { }, // Prevent direct text input
        readOnly = true,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(label) },
        trailingIcon = {
            IconButton(onClick = { expanded = !expanded }) {
                Icon(
                    imageVector = if (expanded) Icons.Filled.ArrowDropDown else Icons.Filled.ArrowDropDown,
                    contentDescription = if (expanded) "Collapse" else "Expand"
                )
            }
        }
    )

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        currencies.forEach { currency ->
            DropdownMenuItem(
                onClick = {
                    onCurrencySelected(currency)
                    expanded = false
                },
                text = { Text(currency) }
            )
        }
    }
}

@Composable
fun HeaderTable() {
    Column{
        Row(
            modifier = Modifier
                .fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            // Currency
            Box(
                modifier = Modifier
                    .width(70.dp)
                    .height(50.dp)
                    .padding(2.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "Currency",
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = Color.Black
                )
            }

            // Rate
            Box(
                modifier = Modifier
                    .width(70.dp)
                    .height(50.dp)
                    .padding(2.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "Rate",
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = Color.Black
                )
            }

            // amount
            Box(
                modifier = Modifier
                    .width(70.dp)
                    .height(50.dp)
                    .padding(2.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "Amount",
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = Color.Black
                )
            }

            // Result
            Box(
                modifier = Modifier
                    .width(70.dp)
                    .height(50.dp)
                    .padding(2.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "Result",
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
fun ThreeByThreeTable(
    recordsViewModel: RecordsViewModel,
    uid: String
) {

    // Display list of items
    val recordsState by recordsViewModel.getAllRecords(uid)
        .collectAsState(initial = emptyList())

    val recordsSize = remember(recordsState) {
        derivedStateOf {
            recordsState.size
        }
    }.value

    if(recordsState.isEmpty() || recordsSize < 1){
        Column (
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Image(
                painter = painterResource(R.drawable.empty),
                contentDescription = "Empty history list",
                modifier = Modifier
                    .size(150.dp)
                    .fillMaxHeight()
            )
            Text(
                text = "No conversion history",
                color = Color.Black
            )
        }
    }
    else{

        Column (
            modifier = Modifier
                .height(300.dp)
                .verticalScroll(rememberScrollState())
        ){
            // Table header
            HeaderTable()

            var pos = 0
            recordsState.forEach { item ->
                pos++

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
//                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    // # - Currency
                    Box(
                        modifier = Modifier
                            .width(70.dp)
                            .height(30.dp)
                            .padding(2.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = item.source + "-" + item.target,
                            fontWeight = FontWeight.Bold,
                            fontSize = 8.sp,
                            color = Color.Black
                        )
                    }

                    // Rate
                    Box(
                        modifier = Modifier
                            .width(70.dp)
                            .height(30.dp)
                            .padding(2.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = item.rate.toString(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 8.sp,
                            color = Color.Black
                        )
                    }

                    // amount
                    Box(
                        modifier = Modifier
                            .width(70.dp)
                            .height(30.dp)
                            .padding(2.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = item.amount.toString(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 8.sp,
                            color = Color.Black
                        )
                    }

                    // Result
                    Box(
                        modifier = Modifier
                            .width(70.dp)
                            .height(30.dp)
                            .padding(2.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = item.result.toString(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 8.sp,
                            color = Color.Black
                        )
                    }

                }
            }
        }
    }
}

@Composable
fun ScrollableNewsRow(viewModel: RetrofitViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val data by viewModel.harryPotterData.observeAsState()
    val news = data?.articles

    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState()),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = Icons.Filled.KeyboardArrowLeft,
            contentDescription = "Scroll articles to the left",
            modifier = Modifier
                .size(80.dp)
                .padding(0.dp, 0.dp, 40.dp, 0.dp)
                .then(Modifier.offset((3).dp, 0.dp)),
            tint = Purple80
        )

        val context = LocalContext.current
        news?.forEach{

            ElevatedCard (
                modifier = Modifier
                    .height(130.dp)
                    .width(180.dp)
                    .padding(0.dp, 0.dp, 10.dp)
                    .clickable {
                        val uri = Uri.parse(it.url)
                        val intent = Intent(Intent.ACTION_VIEW, uri)

                        context.startActivity(intent)
                    }
            ){

                Text(
                    text = it.title.take(26),
                    modifier = Modifier.padding(10.dp)
                )

                Text(
                    text = it.source.name,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(10.dp)
                )
            }

        }
    }
}

//fun getBmi(item: History): Any{
//    return item.weight / pow(item.height, 2)
//}
//
//fun getInferenceUsingBmi(item: History): Any{
//    val bmi = item.weight / pow(item.height, 2)
//    Log.d("TAG BMI", bmi.toString())
//    if (bmi >= 30) {
//        return "Obese"
//    }
//
//    return "Ok"
//}