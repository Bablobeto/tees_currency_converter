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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.teescurrencyconverter.FbViewModel
import com.example.teescurrencyconverter.R
import com.example.teescurrencyconverter.retrofit2.model.RetrofitViewModel
import com.example.teescurrencyconverter.room.entity.Records
import com.example.teescurrencyconverter.room.viewmodel.RecordsViewModel
import com.example.teescurrencyconverter.ui.app.navigations.Screen
import com.example.teescurrencyconverter.ui.app.navigations.Screen.Home.AUTHENTICATION_ROUTE
import com.example.teescurrencyconverter.ui.theme.Purple80
import com.example.teescurrencyconverter.ui.theme.TeesCurrencyConverterTheme
import com.google.common.math.IntMath.pow
import com.google.firebase.auth.FirebaseAuth
import kotlin.random.Random

@SuppressLint("CheckResult")
@Composable
fun HomeScreen(
    navController : NavController,
    vm : FbViewModel,
    recordsViewModel: RecordsViewModel
){
    vm.getCustomData()

    // State variable to track if the dialog is open
    var showDialog by remember { mutableStateOf(false) }
    val auth = FirebaseAuth.getInstance()
    val uid = auth.currentUser?.uid ?: ""

    var newWeight by remember { mutableIntStateOf(0) }
    var newHeight by remember { mutableIntStateOf(0) }
    var age by remember { mutableIntStateOf(vm.customData.age ?: 0) }

    Log.d("values photo", auth.currentUser?.photoUrl.toString())
    Log.d("values", vm.isLogout.value.toString())
    Log.d("values", auth.currentUser.toString())
    Log.d("values", auth.currentUser?.displayName.toString())

    if (null == auth.currentUser || vm.isLogout.value) {
        Log.d("TAG", "Logging out")
        navController.navigate(AUTHENTICATION_ROUTE)
    }

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
                            painter = painterResource(id = R.drawable.sun),
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
                                        imageVector = Icons.Filled.ExitToApp,
                                        contentDescription = "Log out user account",
                                        tint = Color.White
                                    )

                                    Text(
                                        text = "Log out",
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold
                                    )
                                }

                            }

                            Row(
                                modifier = Modifier.fillMaxSize(),
                                verticalAlignment = Alignment.Top,
                                horizontalArrangement = Arrangement.Start
                            ) {

                                Column(
                                    modifier = Modifier.weight(4f)
                                ) {
                                    Text(
                                        text = "Welcome,",
                                        color = Color.Black,
                                        fontWeight = FontWeight.Bold,
                                        style = TextStyle(fontSize = 24.sp)
                                    )

                                    Text(
                                        text = if(null != auth.currentUser)
                                            if(null != vm.customData.firstName) vm.customData.firstName
                                            else auth.currentUser!!.displayName.toString()
                                        else "Buddy",
                                        color = Color.Black,
                                        fontWeight = FontWeight.Bold,
                                        style = TextStyle(fontSize = 28.sp)
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
                                                .border(
                                                    width = 2.dp,
                                                    color = MaterialTheme.colorScheme.primary,
                                                    shape = CircleShape
                                                )
                                                .background(MaterialTheme.colorScheme.primary)
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
                                                .border(
                                                    width = 2.dp,
                                                    color = MaterialTheme.colorScheme.primary,
                                                    shape = CircleShape
                                                )
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
                ){
                    Column (
                        modifier = Modifier
                            .padding(start = 0.dp, 40.dp),
                        verticalArrangement = Arrangement.Top
                    ) {
                        Text(
                            text = "Health articles",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(80.dp, 0.dp),
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.height(15.dp))

                        ScrollableNewsRow()
                    }
                    Column (
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(4f)
                            .padding(16.dp, 30.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Tabular details" +
                                        "\nHealth information",
                                color = Color.Black,
                                fontWeight = FontWeight.Bold
                            )

                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = "Add new weight",
                                tint = Color.White,
                                modifier = Modifier
                                    .background(Purple80)
                                    .padding(5.dp)
                                    .clickable {
                                        showDialog = true
                                    }
                            )
                        }

                        Column (
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(0.dp, 30.dp)
                                .horizontalScroll(rememberScrollState()),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            // Table body
                            ThreeByThreeTable(recordsViewModel, uid)
                        }

                        if(showDialog){
                            AlertDialog(
                                onDismissRequest = { showDialog = false },
                                title = { Text(text = "Add record") },
                                text = {
                                    Column {

                                        OutlinedTextField(
                                            value = age.toString(),
                                            label = {
                                                Text(
                                                    text = "Age (Years)"
                                                )
                                            },
                                            singleLine = true,
                                            keyboardOptions = KeyboardOptions(
                                                keyboardType = KeyboardType.Number,
                                                imeAction = ImeAction.None
                                            ),
                                            modifier = Modifier.fillMaxWidth(),
                                            onValueChange = {newText ->
                                                val newAge = newText.toIntOrNull()
                                                if (newAge != null) {
                                                    age = newAge
                                                }
                                            }
                                        )

                                        OutlinedTextField(
                                            value = newWeight.toString(),
                                            label = {
                                                Text(
                                                    text = "Weight (kg)"
                                                )
                                            },
                                            singleLine = true,
                                            keyboardOptions = KeyboardOptions(
                                                keyboardType = KeyboardType.Number,
                                                imeAction = ImeAction.None
                                            ),
                                            modifier = Modifier.fillMaxWidth(),
                                            onValueChange = {newText ->
                                                val weight = newText.toIntOrNull()
                                                if (weight != null) {
                                                    newWeight = weight
                                                }
                                            }
                                        )

                                        OutlinedTextField(
                                            value = newHeight.toString(),
                                            label = {
                                                Text(
                                                    text = "Height (centimetres)"
                                                )
                                            },
                                            singleLine = true,
                                            keyboardOptions = KeyboardOptions(
                                                keyboardType = KeyboardType.Number,
                                                imeAction = ImeAction.None
                                            ),
                                            modifier = Modifier.fillMaxWidth(),
                                            onValueChange = {newText ->
                                                val height = newText.toIntOrNull()
                                                if (height != null) {
                                                    newHeight = height
                                                }
                                            }
                                        )
                                    }
                                },
                                confirmButton = {
                                    Button(
                                        onClick = {
                                            recordsViewModel.addRecord(
                                                Records(
                                                    Random.nextInt(),
                                                    auth.currentUser?.uid.toString(),
                                                    age,
                                                    newWeight,
                                                    newHeight
                                                )
                                            )
                                            showDialog = false
                                        },
                                        colors = ButtonDefaults.buttonColors(Purple80)
                                    ) {
                                        Text(text = "Confirm")
                                    }
                                },
                                dismissButton = {
                                    Button(
                                        onClick = { showDialog = false },
                                        colors = ButtonDefaults.buttonColors(Color.Red)
                                    ) {
                                        Text(text = "Dismiss")
                                    }
                                },
                                modifier = Modifier.width(300.dp)
                            )
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun HeaderTable() {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // # - id
            Box(
                modifier = Modifier
                    .width(40.dp)
                    .height(50.dp)
                    .padding(2.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "#",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            // age
            Box(
                modifier = Modifier
                    .width(40.dp)
                    .height(50.dp)
                    .padding(2.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "Age",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            // weight
            Box(
                modifier = Modifier
                    .width(70.dp)
                    .height(50.dp)
                    .padding(2.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "Weight",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            // height
            Box(
                modifier = Modifier
                    .width(100.dp)
                    .height(50.dp)
                    .padding(2.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "Height",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            // BMI
            Box(
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
                    .padding(2.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "BMI",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            // Inference
            Box(
                modifier = Modifier
                    .width(110.dp)
                    .height(50.dp)
                    .padding(2.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "Inference",
                    fontWeight = FontWeight.Bold,
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
        Image(
            painter = painterResource(R.drawable.empty),
            contentDescription = "Empty activity list",
            modifier = Modifier
                .size(150.dp)
                .fillMaxHeight()
        )
        Text(
            text = "No Activity",
            color = Color.Black
        )
    }
    else{
        // Table header
        HeaderTable()

        Column {
            var pos = 0
            recordsState.forEach { item ->
                pos++

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    // # - id
                    Box(
                        modifier = Modifier
                            .width(40.dp)
                            .height(50.dp)
                            .padding(2.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = pos.toString(),
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }

                    // age
                    Box(
                        modifier = Modifier
                            .width(40.dp)
                            .height(50.dp)
                            .padding(2.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = item.age.toString(),
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }

                    // weight
                    Box(
                        modifier = Modifier
                            .width(70.dp)
                            .height(50.dp)
                            .padding(2.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = item.weight.toString(),
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }

                    // height
                    Box(
                        modifier = Modifier
                            .width(100.dp)
                            .height(50.dp)
                            .padding(2.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = item.height.toString(),
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }

                    // bmi
                    Box(
                        modifier = Modifier
                            .width(50.dp)
                            .height(50.dp)
                            .padding(2.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = getBmi(item).toString(),
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }

                    // inference
                    Box(
                        modifier = Modifier
                            .width(110.dp)
                            .height(50.dp)
                            .padding(2.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = getInferenceUsingBmi(item).toString(),
                            fontWeight = FontWeight.Bold,
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

fun getBmi(item: Records): Any{
    return item.weight / pow(item.height, 2)
}

fun getInferenceUsingBmi(item: Records): Any{
    val bmi = item.weight / pow(item.height, 2)
    Log.d("TAG BMI", bmi.toString())
    if (bmi >= 30) {
        return "Obese"
    }

    return "Ok"
}